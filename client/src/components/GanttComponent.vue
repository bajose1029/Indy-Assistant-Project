<template>
  <div></div>
</template>

<script>
export default {

}
</script>

<style>

</style>






<!-- 
<template>
    <div ref="ganttContainer"></div>
  </template>

<script>
import {gantt} from 'dhtmlx-gantt';

export default {
  props: {
    tasks: {
      type: Object,
      default () {
        return {data: [], links: []}
      }
    }
  },
 
  mounted: function () {
    gantt.config.date_format = "%Y-%m-%d";
    gantt.init(this.$refs.ganttContainer);

    if(this.$props.tasks.data.length > 0){
        gantt.parse(this.$props.tasks);
    } else {
        console.warn('No tasks data to parse')
    }
    
  }
}
</script>

<style>
    @import "~dhtmlx-gantt/codebase/dhtmlxgantt.css";
</style> -->