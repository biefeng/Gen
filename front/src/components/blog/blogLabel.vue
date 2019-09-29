<template>
  <div id="leftDiv">
    <div class="scroll-view">
      <div id="blogLabel">
        <ul id="blogLabel-ul">
          <li class="list-li"><a><h3>Python</h3></a></li>
          <li class="list-li"><a><h3>Java</h3></a></li>
          <li class="list-li"><a><h3>后端</h3></a></li>
          <li class="list-li"><a><h3>前端</h3></a></li>
          <li class="list-li"><a><h3>JavaScript></h3></a></li>
        </ul>
      </div>
    </div>
    <div class="clearfloat"></div>
  </div>
</template>

<script>
  export default {
    name: "blogLabel"
  }
</script>

<style scoped>
  .scroll-view {
    height: 75vh;
    margin-right: -40px;
    overflow: auto;
  }

  #leftDiv {
    overflow: hidden;
    position: fixed;
    background-color: #cccccc;
    width: 300px;
    margin-right: 30px;
    margin-left: 70px;
    z-index: 1;
    min-height: 850px;
    -webkit-box-shadow: #9a9a9a 2px 2px 10px;
    box-shadow: #9a9a9a 2px 2px 10px;
    -moz-box-shadow: #9a9a9a 2px 2px 10px;
    border-radius: 10px;
  }

  #blogLabel {
    position: relative;
    /*min-height: 800px;*/
  }

  #blogLabel-ul {
    margin: 0px;
    padding-left: 0px;
    width: 300px;
  }

  div.clearfloat {
    clear: both;
    height: 0;
    font-size: 1px;
    line-height: 0px;
  }
</style>
