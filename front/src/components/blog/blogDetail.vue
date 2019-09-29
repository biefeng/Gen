<template>
  <div style="height: 100vh">
    <div id="blog_detail">


      <div id="blogContentDiv">
        <div style="font-size: 32px;font-weight: bolder;color: #a9a9a9">{{blogContent.TITLE}}</div>
        <div v-html="this.blogContent.content_html" style="float: left;width: 100%"></div>

      </div>
      <div id="scroll-bar"
           style="position: absolute; width: 0.8vw;height: 850px; float: left; background-color: white; z-index: 2;margin-left: 70vw;">
        <div id="scroll-bar-1" :style="{transform: 'translateY('+this.scrollDis+'px)'}"></div>
      </div>
      <div class="clearfloat"></div>
    </div>

    <div class="clearfloat"></div>
  </div>
</template>

<script>
  export default {
    name: "detail",
    data() {
      return {
        blogId: '',
        blogContent: {
          title:''
        },
        scrollDis: ''
      }
    },
    created() {
      this.blogId = this.$route.params.blogId
      console.log(this.blogId)
      this.getBlogDetail()

    },
    mounted() {

    },
    updated() {
      this.caculateScrollDis()
    },
    methods: {
      getBlogDetail() {
        this.$http.get("/service-1/blog/" + this.blogId).then(res => {
          this.blogContent = res.data.data
          console.log(this.blogContent)
        })
      },
      caculateScrollDis() {
        let w = document.getElementById("rightContainer");
        let height = w.clientHeight;
        let barBox = document.getElementById("blogContentDiv");
        let barBoxHeight = barBox.clientHeight;
        let barHeight = document.getElementById("scroll-bar-1").clientHeight;
        w.onscroll = (e) => {
          let moved = w.scrollTop;
          console.log("barBoxHeight: " + barBoxHeight + " moved: " + moved + ", height: " + height)
          this.scrollDis = (moved / (barBoxHeight - height)) * (height - barHeight)
        }
      }
    }
  }
</script>

<style scoped>
  #blog_detail {
    background-color: white;
    text-align: left;
    width: 1445.75px;
    float: left;
  }

  div.clearfloat {
    clear: both;
    height: 0;
    font-size: 1px;
    line-height: 0px;
  }

  #blogContentDiv {
    min-height: 850px;
    width: 1000px;
    margin-left: 1px;
    overflow: auto;
    background-color: transparent;
    padding: 3em;
    /*border: 1px solid dimgrey;*/
    float: left;
    box-shadow: 0px 0px 1px #000
  }

  #scroll-bar-1 {
    height: 150px;
    background-color: #cccccc;
    position: relative;
    -webkit-border-radius: 20px;
  }

</style>
