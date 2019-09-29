<template>
  <div id="blogList">
    <div id="blogPanel" style="height: 800px;">
      <div style="width: 75vw;margin-left: 0px;position: relative;min-height: 820px;background-color: white; ">
        <div id="blogListPanel" style="float: left;width: 75vw;">
          <ul id="blog-list-ul">
            <li class="blog-list-li" v-for="blogItem in blogs">
              <div class="list_con">
                <div class="title">
                  <router-link :to="{name:'blogDetail',params:{blogId:blogItem.guid}}"><h2>{{blogItem.title}}</h2>
                  </router-link>
                </div>
                <div class="summary oneline">1. 吴恩达老师的机器学习和深度学习课程笔记打印版 原文链接： 吴恩达老师的机器学习和深度学习课程笔记打印版</div>
                <dl class="list_userbar">
                  <dd class="tag">人工智能</dd>
                  <dd class="tag">{{blogItem.CREATE_TIME}}</dd>
                  <div class="interactive floatR">
                    <dd class="common_num">
                      <a>
                        <span>评论数: </span>
                        <span>10</span>
                      </a>
                    </dd>
                  </div>
                </dl>
              </div>
            </li>
          </ul>
        </div>
        <div id="scroll-bar"
             style="position: absolute; width: 0.3vw;height: 100%; float: left; background-color: white; z-index: 2;margin-left: 75vw">
          <div id="scroll-bar-1" :style="{transform: 'translateY('+this.scrollDis+'px)'}"></div>
        </div>
        <div class="clearfloat"></div>
      </div>
    </div>

    <div class="clearfloat"></div>
    <div style="position: absolute;bottom: 0px;width: 75.3vw;">
      <el-pagination
        layout="prev, pager, next,total"
        :total="this.totalRows"
        :pager-count="11" :page-size="this.defaultPageSize" @current-change="getBlogList">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: "blogList",
    data() {
      return {
        scrollDis: '',
        input: '',
        blogs: [],
        totalRows: 0,
        defaultPageSize: 15
      }
    },
    mounted() {
      this.getBlogList()
    },
    created() {

    },
    updated() {
      this.caculateScrollDis()
    },
    methods: {
      caculateScrollDis() {
        let w = document.getElementById("rightContainer");
        let height = w.clientHeight;
        let barBox = document.getElementById("scroll-bar");
        let barBoxHeight = barBox.clientHeight;
        let barHeight = document.getElementById("scroll-bar-1").clientHeight;
        w.onscroll = (e) => {
          let moved = w.scrollTop;
          //console.log("barBoxHeight: " + blogListPanelHeight + ", moved: " + moved + ", height: " + height)
          this.scrollDis = (moved / (barBoxHeight - height)) * (barBoxHeight - barHeight)
        }
      },
      getBlogList(curPage) {
        let w = document.getElementById("rightContainer");
        w.scrollTo(0, 0)

        this.scrollDis = 0
        if (!curPage) {
          curPage = 1;
        }
        this.$http.get("/service-1/blog/list?" + "curPage=" + curPage + "&pageSize=" + this.defaultPageSize).then(res => {
          this.blogs = res.data.data
          console.log(1)
          console.log(this.blogs)
          this.totalRows = res.data.totalRows
        });
      }
    }
  }
</script>

<style scoped>

  #blogList {
    height: 800px;
    width: 100%;

  }

  #blog-list-ul li {
    list-style: none;
    text-align: left;
    height: 100px;
    padding: 18px;
    font-family: PingFang SC, Hiragino Sans GB, Arial, Microsoft YaHei, Verdana, Roboto, Noto, Helvetica Neue, sans-serif !important;
    border-bottom: 1px solid #f4f4f4;
  }

  .list_con {

    padding: 18px 24px;
    width: 80%;
  }

  .list_con h2 {
    margin: 0px 0px 4px 0px;
  }

  div.title {
    font-size: 18px;
    font-weight: bold;
    font-family: "Microsoft YaHei UI";
  }

  div.summary {
    line-height: 24px;
    margin-bottom: 4px;
    font-size: 14px;
  }

  dl.list_userbar {
    padding: 0px;
    margin: 0px;
    color: #999;
  }

  dl.list_userbar dd {
    float: left;
    line-height: 24px;
  }

  div.floatR {
    float: right;
  }

  div.interactive {
    line-height: 24px;
  }

  div.clearfloat {
    clear: both;
    height: 0;
    font-size: 1px;
    line-height: 0px;
  }

  .el-pagination {
    white-space: normal;
    background-color: white;
  }

  .el-input {
    width: 80%;
    height: 3px;
  }

  #blogPanel {
    width: 100%;
    min-height: 850px;
    position: relative;
    float: left;
  }

  #scroll-bar-1 {
    height: 300px;
    background-color: red;
    -webkit-border-radius: 20px;
    position: relative
  }

  #blog-list-ul li {
    list-style: none;
    text-align: left;
    height: 100px;
    padding: 18px;
    font-family: PingFang SC, Hiragino Sans GB, Arial, Microsoft YaHei, Verdana, Roboto, Noto, Helvetica Neue, sans-serif !important;
    border-bottom: 1px solid #f4f4f4;
  }

  .title a {
    text-decoration: none;
    color: dimgrey;
  }
</style>
