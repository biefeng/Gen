<template>
  <div style="padding: 10px;width: 1500px;margin-left: 150px">
    <el-container style="height: 860px; ">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-menu-item index="1">
            <v-icon style="height: 18px;padding-right: 5px" name="users"></v-icon><span>用户管理</span>
          </el-menu-item>
          <el-menu-item index="2">
            <v-icon style="height: 18px;padding-right: 5px" name="book"></v-icon><span>博客管理</span>
          </el-menu-item>
          <el-menu-item index="3">
            <v-icon style="height: 18px;padding-right: 5px" name="music"></v-icon><span>音乐管理</span>
          </el-menu-item>
          <el-menu-item index="4">
            <v-icon style="height: 18px;padding-right: 5px" name="video"></v-icon><span>视频管理</span>
          </el-menu-item>
          <el-menu-item index="5">
            <v-icon style="height: 18px;padding-right: 5px" name="message-square"></v-icon><span>评论管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <div style="margin-top: 30px">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>活动管理</el-breadcrumb-item>
              <el-breadcrumb-item>活动列表</el-breadcrumb-item>
              <el-breadcrumb-item>活动详情</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <!--<el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>-->
        </el-header>

        <el-main>
          <div style="height: 100%;background-color: #7bc3a2;text-align: center ">
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<script>
  export default {
    name: "adminBlogList",
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item)
      }
    }
  }
</script>

<style scoped>
  .el-header {
    background-color: #ffffff;
    color: #333;
    line-height: 60px;
    margin-left: 20px;
  }

  .el-main {
    padding: 0px;
    margin-left: 20px;
  }

  .el-aside {
    color: #333;

  }
</style>
