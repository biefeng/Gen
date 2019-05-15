import axios from 'axios'
import cookieUtil from './cookieUtil'
import {
  Message
} from 'element-ui'

axios.defaults.withCredentials = true
const fetchUtil = axios.create({
  baseURL: 'http://localhost:9999/'

})

const qs = require('qs')

fetchUtil.interceptors.request.use(function (config) {
  /*if(config.method=='post'){
    config.data = qs.stringify(config.data)
  }*/
  //config.headers['Authorization']=cookieUtil.getItem('Authorization')
  config.headers.common['X-Authorization']='Bearer '+cookieUtil.getItem('X-Authorization')
  return config;
}, function (error) {

  return Promise.reject(error);
});
fetchUtil.interceptors.response.use(function (response) {
  let unAuthorized = response.data.unAuthorized;
  let unAuthenticated = response.data.unAuthenticated;
  if (unAuthenticated && unAuthenticated === 'true') {
    return null;
  } else if (unAuthorized && unAuthorized === 'true') {
  } else {
    return response;
  }

}, function (error) {
  Message({
    message: error.message,
    type: 'error'
  })
  return Promise.reject(error);
});

export default fetchUtil
