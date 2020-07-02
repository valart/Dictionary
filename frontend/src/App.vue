<template>
  <div>
    <md-toolbar class="md-primary" md-elevation="1">
      <h3 class="md-title" style="flex: 1">Telia Dictonary</h3>
      <md-button v-on:click="firstTab=true">Translate</md-button>
      <md-button v-on:click="firstTab=false">Add</md-button>
    </md-toolbar>

    <Translator v-show="firstTab"/>
    <Adding v-show="!firstTab"/>
  </div>
</template>

<script>

  import Translator from "./components/translator/Translator";
  import Adding from "./components/adding/Adding";

export default {
  name: 'App',
  components: {
    Translator,
    Adding
  },
  data: () => ({
    firstTab: true
  })
}
</script>

<style>
  #app{
    padding-top: 5px;
  }
</style>
