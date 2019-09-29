'use strict'

import camelCase from "lodash/camelCase";


import adminBlogList from "@/components/admin/view/adminBlogList"
import adminContainer from "@/components/admin/view/route/adminContainer"

let adminComponents = require.context("@/components/admin/view/route/", true, /\w+\.vue$/);

let routes = []

adminComponents.keys().forEach(fileName => {
  let component = adminComponents(fileName);
  const componentName =
    camelCase(
      // 剥去文件名开头的 `./` 和结尾的扩展名
      fileName.replace(/^\.\/(.*)\.\w+$/, '$1')
    )

  /*routes.push({
    path: "/" + componentName,
    name: componentName,
    component: component.default
  })*/
})

routes.push(
  {
    path: "/adminContainer",
    component: adminContainer,
    children: [
      {
        path: "",
        component: adminBlogList
      }
    ]
  }
)

export default routes
