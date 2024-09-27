<script setup>
import axios from 'axios';
import { ref } from 'vue'

  const apiresponse = ref('');
  axios.get("http://localhost:8081")
  .then((res) => {
    apiresponse.value = res && res.data && res.data.message;
  })
</script>

<template>
  <main>
    <h1>Coming Soon.....</h1> 
    <span>REST server response:</span> <strong style="font-weight: 600; font-style: italic;">{{ apiresponse }}</strong>
  </main>
</template>

<style scoped>

</style>
