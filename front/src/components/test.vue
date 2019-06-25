<template>
  <div ref="mychart" style="width: 800px;height: 600px;background-color: white;margin-top: 200px;margin-left: 300px"></div>
</template>

<script>
  import echarts from 'echarts'
  export default {
    name: "test",
    data() {
      return {}
    },
    created() {

    },mounted(){
      this.initCharts()
    },
    methods: {
      initCharts() {
        let option = {
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
          }]
        }
        let init = echarts.init(this.$refs.mychart);
        init.setOption(option)
      }


    }
  }
</script>

<style scoped>

</style>
