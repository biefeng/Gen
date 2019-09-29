<template>
  <div style="padding-top: 10px">
    <div style="width: 80%;margin:  auto;height: 580px;background-color: white">
      <el-table :data="tableData" :border="true" :stripe="true">
        <el-table-column prop="title" header-align="center" label="标题" width="300">
        </el-table-column>
        <el-table-column prop="author" header-align="center" label="简介" width="300"></el-table-column>
        <el-table-column prop="author" header-align="center" label="创建时间" width="150"></el-table-column>
        <el-table-column prop="author" header-align="center" label="作者" width="122"></el-table-column>
        <el-table-column prop="address" header-align="center" label="操做" width="150">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style="bottom: 0px;width: 80%;margin: auto;background-color: white">
      <el-pagination
        layout="prev, pager, next,total"
        :total="this.totalRows"
        :current-page="this.currentPage"
        :pager-count="11" :page-size="this.defaultPageSize" @current-change="fetchList">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: "adminBlogList",
    data() {
      return {
        tableData: [],
        totalRows: 0,
        currentPage:1,
        defaultPageSize: 10
      }
    },
    created() {
      this.fetchList(1)
    },
    methods: {
      fetchList(curPage) {
        this.currentPage=curPage
        this.$http.get("/service-1/blog/list?" + "curPage=" + curPage + "&pageSize=" + this.defaultPageSize).then(res => {
          this.tableData = res.data.data
          console.log(1)
          console.log(this.blogs)
          this.totalRows = res.data.totalRows
        });
      },
      handleEdit(index, row) {
        this.$router.push({
          name: 'blogEdit',
          params:{
            row:row
          }
        })
      },
      handleDelete(index, row) {
        this.$http.delete("/service-1/blog/"+row.guid).then(res=>{
          this.$message.success("删除成功")
          this.currentPage=1
          this.fetchList(this.currentPage)
        }).catch(e=>{
          this.$message.error(e)
        })
      }
    }
  }
</script>

<style scoped>

</style>
