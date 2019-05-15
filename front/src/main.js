// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axois from './util/axios'
import upperFirst from 'lodash/upperFirst'
import camelCase from 'lodash/camelCase'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import 'mavon-editor/dist/css/index.css'
import 'highlight.js/styles/atom-one-dark.css'

import {
  Message
} from 'element-ui'


Vue.config.productionTip = false
Vue.prototype.$http = axois
/* eslint-disable no-new */


//自动加载并注册components/global下的组件

const requireComponent = require.context('./components/global', true, /\w+\.(vue|js)$/)
requireComponent.keys().forEach(fileName => {
  const componentConfig = requireComponent(fileName)
  const componentName = upperFirst(
    camelCase(
      // 剥去文件名开头的 `./` 和结尾的扩展名
      fileName.replace(/^\.\/(.*)\.\w+$/, '$1')
    )
  )
  Vue.component(
    componentName,
    componentConfig.default || componentConfig
  )
})
Vue.use(ElementUI)
const vm = new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
}).$mount("#app")


console.log(vm.$options.components)


