<template>
  <div id="navigation">
    <div style="  ">
      <ul>
        <li v-for="item in items">
          <router-link @click.native="changeColor(item.name)" v-bind:class="{active:item.name == actived }"
                       v-bind:to="item.path">{{item.name}}
          </router-link>
        </li>
      </ul>
      <div class="clearfloat"></div>
    </div>
    <div @mouseenter="popList" @mouseleave="hideList"
         style=" float:right;width: 150px;position: relative;right: 10px ;z-index: 15; ">
      <div
        style="width: 65px;height: 65px; border-radius:65px;-moz-border-radius:65px;-webkit-border-radius: 65px;overflow: hidden;margin: 0px auto">
        <img src="../../assets/emoj.jpg" width="65px" height="65px"
             style="z-index: 0;position: relative"/>
      </div>
      <div id="menu" :style="{display:this.menuDisplayed}">

        <!--<a v-for="menu in menus">
          <p @mouseover="changePBackcolor(menu.name)" @mouseout="revertPBackColor"
             :class="{hover:hovered==menu.name}">{{menu.name}}</p></a>-->

        <div v-for="menu in menus">
          <router-link :to="menu.path"><p @mouseover="changePBackcolor(menu.name)" @mouseout="revertPBackColor"
                                          :class="{hover:hovered==menu.name}">{{menu.name}}</p></router-link>
        </div>
      </div>
    </div>
    <div style="float: right;position: relative;margin-right: 40px;">
      <el-button type="primary" icon="el-icon-search"></el-button>
    </div>
    <div style="width: 300px ;float: right;margin-right: 0px;">
      <el-input v-model="input" placeholder="请输入内容"></el-input>
    </div>


  </div>

</template>

<script>
  export default {
    name: "navigation",
    data() {
      return {
        items: [
          {name: '首页', path: '/'},
          {name: '博客', path: '/blog'},
          {name: '音乐', path: '/music'},
          {name: '电影', path: '/movie'},
          {name: '关于', path: '/about'}
        ],
        actived: '',
        input: '',
        menuDisplayed: 'none',
        pBackColor: '#9cfffc',
        hovered: "",
        menus: [
          {name: "写博客", path: "/blog/edit"},
          {name: "博客管理", path: ""},
          {name: "消息中心", path: ""}
        ]

      }
    },
    methods: {
      changeColor(name) {
        this.actived = name
      },
      popList() {
        this.menuDisplayed = ''
      },
      hideList() {
        this.menuDisplayed = 'none'
      },
      changePBackcolor(menu) {
        this.hovered = menu
      },
      revertPBackColor() {
        this.pBackColor = '#9cfffc'
      }
    }
  }
</script>

<style scoped>
  #navigation {
    background-color: rgba(128, 128, 128, 0.5);

    top: 10px;
    position: fixed;
    height: 70px;
    z-index: 10;
    line-height: 70px;
    width: 100vw;
  }

  #menu {
    z-index: 20;

    position: relative;
    text-align: center;
    padding: auto;
    background-color: rgba(255, 45, 45, 0.23);
    width: 150px;

  }

  #menu a p {
    height: 25px;
    margin: 0px;
    line-height: 25px;
    margin: auto;
    padding: 0px 6px;
    cursor: pointer;
  }

  ul li {
    list-style: none;
    float: left;
    height: 70px;
    line-height: 70px;
    margin: 0px 0px 0px 50px;

  }

  ul {
    position: relative;
    margin: 0px;
    float: left;
  }

  ul li a {
    text-decoration: none;
    color: #ff9492;
  }

  .active {
    color: aqua;
  }

  .el-input {
    float: left;
    display: block;
  }

  .el-button {
    background-color: rgba(255, 45, 45, 0);
    border-color: rgba(255, 49, 49, 0);
  }

  input.el-input__inner {
    width: 70%;
  }

  .hover {
    background-color: #442fff;
  }

  a {
    text-decoration: none;
    color: #151722;
    font-weight: bold;
  }


</style>
