<template>
  <div id="blogContainer">
    <div id="scroll-bar-wrap">
      <div id="leftContainer">
        <router-view name="left"></router-view>
      </div>
      <div id="rightContainer" style="padding-left: 400px;margin-right: -17px;overflow: auto;height: 850px">
        <router-view name="right"></router-view>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "blogContainer"
  }
</script>

<style scoped>
  #blogContainer {
    position: relative;
  }

  #scroll-bar-wrap {
    overflow: hidden;
    height: 850px;
  }
</style>
