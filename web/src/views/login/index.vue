<template>
  <div class="login-background">
    <div class="login-container">
      <el-row :gutter="20">
        <el-col :span="12">
          <div class="grid-content bg-purple">
            <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left" @keyup.enter.native="handleLogin">
              <div class="title-container">
                <h3 class="title">devops-platform</h3>
              </div>
              <div class="login-border"></div>
              <div class="desc">
                <h4 class="title">一站式全效能平台</h4>
              </div>
              <el-form-item prop="username">
                <span class="svg-container">
                  <svg-icon icon-class="user" />
                </span>
                <el-input ref="username" v-model="loginForm.username" class="username-input" placeholder="Username" name="username" type="text" tabindex="1" autocomplete="on" />
              </el-form-item>
              <el-tooltip v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
                <el-form-item prop="password">
                  <span class="svg-container">
                    <svg-icon icon-class="password" />
                  </span>
                  <el-input
                    :key="passwordType"
                    ref="password"
                    v-model="loginForm.password"
                    :type="passwordType"
                    placeholder="Password"
                    name="password"
                    tabindex="2"
                    autocomplete="on"
                    @keyup.native="checkCapslock"
                    @blur="capsTooltip = false"
                    @keyup.enter.native="handleLogin"
                  />
                  <span class="show-pwd" @click="showPwd">
                    <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
                  </span>
                </el-form-item>
              </el-tooltip>
              <el-form-item prop="code">
                <el-row :gutter="20">
                  <el-col :span="2">
                    <span class="svg-container">
                      <svg-icon icon-class="password" />
                    </span>
                  </el-col>
                  <el-col :span="10">
                    <el-input v-model="loginForm.captcha" placeholder="验证码" />
                  </el-col>
                  <el-col :span="10" class="login-captcha">
                    <img :src="picPath" alt="验证码" @click="getCaptcha()">
                  </el-col>
                </el-row>
              </el-form-item>
              <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;background-color: #46A6FF" @click.native.prevent="handleLogin">登录</el-button>
            </el-form>
            <el-dialog title="Or connect with" :visible.sync="showDialog">
              Can not be simulated on local, so please combine you own business simulation! ! !
              <br>
              <br>
              <br>
              <social-sign />
            </el-dialog>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content bg-purple">
            <div style="max-width: 100%;max-height: 100%;width: 500px;height: 500px;background-color:#1890ff;">
              <img style="width: 100%;height:100%;" src="/img/login.png">
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>

import { validUsername } from '@/utils/validate'
import { genUUID } from '@/utils/index'
import SocialSign from './components/SocialSignin'
import { captcha } from '@/api/sys/user'

export default {
  name: 'Login',
  components: { SocialSign },
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的账号'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码至少5位数'))
      } else {
        callback()
      }
    }
    const validateCode = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error('请输入正确的验证码'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: '',
        uuid: '',
        captcha: '',
        captchaId: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
        captcha: [{ required: true, trigger: 'blur', validator: validateCode }]
      },
      passwordType: 'password',
      picPath: '',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    this.getCaptcha()
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  destroyed() {
  },
  methods: {
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
              this.loading = false
            })
            .catch(() => {
              this.getCaptcha()
              this.loginForm.captcha = ''
              this.loading = false
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 获取验证码
    getCaptcha() {
      this.loginForm.uuid = genUUID()
      this.loginForm.captcha = ''
      captcha({}).then(response => {
        if (response.code === 2000) {
          this.picPath = response.data.picPath
          this.loginForm.captchaId = response.data.captchaId
        } else {
          this.msgError(response.msg)
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    }
  }
}
</script>
<style>
  .login-background {
    background-color: #f5f5f5;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .login-container {
    min-width: 640px;
    width: 960px;
    height: 480px;
    background-color: #fff;
    box-shadow: 0 5px 5px -3px rgb(0 0 0 / 20%), 0 8px 10px 1px rgb(0 0 0 / 14%), 0 3px 14px 2px rgb(0 0 0 / 12%);
  }
</style>
<style lang="scss">
  $bg: #ffffff;
  $light_gray:#fff;
  $cursor: #3b3838;
  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 12px 12px 15px;
        //color: $light_gray;
        height: 47px;
        caret-color: $cursor;
        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      //background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
    .el-form-item__error {
      color: #ff4949;
      font-size: 12px;
      line-height: 1;
      padding-top: 4px;
      position: absolute;
      top: 100%;
      left: 0;
      padding-left: 45px;
    }
  }
</style>
<style lang="scss" scoped>
  $bg: #ffffff;
  $dark_gray: #889aa4;
  $light_gray: #575050;
  .login-container {
    background-color: $bg;
    overflow: hidden;
    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 60px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }
    .tips {
      font-size: 14px;
      color: #363535;
      margin-bottom: 10px;
      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }
    .title-container {
      position: relative;
      .title {
        font-size: 36px;
        color: #515151;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }
    .login-border {
      height: 2px;
      margin: 10px auto 20px;
      position: relative;
      width: 80px;
      background: #46A6FF;
    }
    .desc {
      position: relative;
      .title {
        font-size: 16px;
        color: #999;
        margin: 20px auto 20px auto;
        text-align: center;
        font-weight: normal;
      }
    }
    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
    .username-input{
      color: red !important;
      border-bottom: #2ac06d 2px;
    }
    .login-captcha {
       overflow: hidden;
       > img {
         width: 100%;
         cursor: pointer;
       }
     }
  }
</style>
