<template>
  <div id="blog">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: "blog",
  }
</script>

<style scoped>
  #blog {
    position: relative;
    top: 90px;
    width: 100vw;
    height: 90vh;
  }

</style>
