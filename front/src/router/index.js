import Vue from 'vue'
import Router from 'vue-router'

import indexPage from '@/components/index'
import test from '@/components/test'
import blog from '@/components/blog/blog'
import blogLabel from '@/components/blog/blogLabel'
import blogContainer from '@/components/blog/blogContainer'
import blogList from '@/components/blog/blogList'
import blogDetail from '@/components/blog/blogDetail'
import music from '@/components/music'
import mavonEditor from '@/components/blog/mavonEditor'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/blog',
      component: blog,
      children: [
        {
          path: "", component: blogContainer, children: [
            {
              path: "",
              components: {
                left: blogLabel,
                right: blogList
              }
            }
          ]
        },
        {
          path: "",
          component: blogContainer,
          children: [
            {
              path: "detail",
              name: 'blogDetail',
              components: {
                left: blogLabel,
                right: blogDetail
              }
            }
          ]
        },
        {
          path: "edit",
          component: mavonEditor
        }
      ],


    },
    {
      path: '/music',
      component: music
    },
    /*{
      path: "/blog/edit",
      component: mavonEditor
    },*/
    {
      path: "/blog/detail",

    },
    {
      path: '/blog/label',
      component: blogLabel
    }, {
      path: '/index',
      component: indexPage
    }, {
      path: '/test',
      component: test
    }

  ]
})
