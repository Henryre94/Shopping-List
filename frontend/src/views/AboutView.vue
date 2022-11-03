<template>
  <div class="about">
    <h1>{{ demoText }}</h1>
      <button type="button" @click="loadDemoText">Load demo text</button>
  </div>
</template>

<script>
    export default {
        computed: {
            demoText() {
                return this.$store.state.demoText || 'Load demo text'
            }
        },
        methods: {
            async loadDemoText() {
                await this.$store.dispatch('loadDemoText')
            }
        }
    }
</script>