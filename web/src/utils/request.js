import axios from 'axios'
import { MessageBox, Message, Notification } from 'element-ui'
import store from '@/store'
import { getToken, setToken } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 50000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    if (store.getters.token) {
      config.headers['authorization'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    const token = response.headers['authorization']
    if (token) {
      setToken(token)
    }
    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 2000) {
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 4010 || res.code === 4011 || res.code === 4012 || res.code === 4012 || res.code === 4012) {
        // to re-login
        MessageBox.confirm('当前会话已过期，请重新登录', '提示', {
          confirmButtonText: '重新登录',
          cancelButtonText: '关闭',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      } else if (response.config.responseType == 'blob'){//文件下载
        return response
      } else {
        Notification({
          title: '成功',
          message: res.message,
          type: 'error',
          duration: 5 * 1000
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err ' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
