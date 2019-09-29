<template>
  <div id="app">
    <div id="backGround"
         :style="{backgroundImage:'url('+backGroundImage+')',backgroundAttachment:'fixed',backgroundSize:'100%',position:'absolute',width:'100vw' ,height:'100vh',zIndex:'-1'}"></div>
    <Navigation></Navigation>
    <div id="indexPanel">
      <router-view></router-view>
    </div>
    <Bottom></Bottom>
  </div>

</template>

<script>

  import Bottom from "./components/global/bottom";
  export default {
    name: 'App',
    components: {Bottom},
    data() {
      return {
        backGroundImage: ''
      }
    },
    methods: {
      getImageUrl() {
        this.$http.get('http://localhost:7538/test/getImageByBing?idx=1').then(res => {
          console.log(res)
          this.backGroundImage = res.data
        })
      }
    }, created() {
      this.getImageUrl()
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    position: fixed;
    height: 100vh;
  }

  body {
    margin: 0px;
  }

  #indexPanel {
    z-index: 0;
    position: fixed;
    width: 1850px;
    top: 90px;
  }
</style>
