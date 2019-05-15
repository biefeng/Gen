<template>
  <div id="index">
    <router-view></router-view>
  </div>
</template>

<script>
  import cookieUtil from "./../util/cookieUtil";

  export default {
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    methods: {
      login() {
        this.$http.get("http://localhost:9999/comm/login?username=admin&password=272232").then(response => {
          console.log(response)
          let str;
          if (response.data) {
            str = response.data
            Object.keys(str).map(item => {
              cookieUtil.setItem(item, response.data[item])
            })
          }
          console.log(Object.keys(str))
        })
      },
      name() {
        this.$http.get("service-1/test/name").then(response => {
          console.log(response)
        })
      }
    }, created() {
      this.login()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
