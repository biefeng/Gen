<template>
  <div id="bottom"></div>
</template>

<script>
  export default {
    name: "bottom"
  }
</script>

<style scoped>
  #bottom {
    position: absolute;
    bottom: 0px;
    width: 100%;
    height: 30px;
    text-align: center;
    background: #ccc;
    z-index: 2;
    overflow: hidden;
  }
</style>
