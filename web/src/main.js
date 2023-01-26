import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './styles/element-variables.scss'

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'
import './icons' // icon
import './permission' // permission control
import './utils/error-log' // error log
import Pagination from '@/components/Pagination'

import Tinymce from '@/components/Tinymce/index.vue'

Vue.component('tinymce', Tinymce)
Vue.component('Pagination', Pagination)
Vue.use(permission)

import { parseTime, resetForm, addDateRange, selectDictLabel, selectItemsLabel } from '@/utils/costum'
import * as filters from './filters' // global filters

// 全局方法挂载
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectItemsLabel = selectItemsLabel

Vue.prototype.msgSuccess = function(msg) {
  this.$notify({
    title: '成功',
    message: msg,
    type: 'success'
  })
  // this.$message({ showClose: true, message: msg, type: 'success' })
}

Vue.prototype.msgError = function(msg) {
  this.$notify({
    title: '错误',
    message: msg,
    type: 'error'
  })
  // this.$message({ showClose: true, message: msg, type: 'error' })
}

Vue.prototype.msgInfo = function(msg) {
  this.$notify({
    title: '信息',
    message: msg,
    type: 'info'
  })
  // this.$message.info(msg)
}
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
if (process.env.NODE_ENV === 'production') {
  const { mockXHR } = require('../mock')
  mockXHR()
}

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
  // locale: enLang // 如果使用中文，无需设置，请删除
})

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
