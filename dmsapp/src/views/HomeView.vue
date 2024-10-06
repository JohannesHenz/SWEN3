<script setup>
import axios from 'axios';
import { ref } from 'vue'

  const apiresponse = ref('');
  const selectedFile = ref('');
  const savedFileResponse = ref(null);
  const savedFileError = ref(null);
  axios.get("http://localhost:8081")
  .then((res) => {
    apiresponse.value = res && res.data && res.data.message;
  });

  const changeFile = (event) => {
    selectedFile.value = event.target.files && event.target.files[0];
    console.debug("selectedFile: ", selectedFile);
  }

  const saveFile = () => {
    console.debug("selectedFile: ", selectedFile.value);
  
    axios.post("http://localhost:8081/file", {
      fileName: selectedFile.value.name,
      fileSize: selectedFile.value.size
    })
    .then((res) => {
      savedFileResponse.value = res && res.data;
    })
    .catch((err) => {
      savedFileError.value = err;
    })
  }
</script>

<template>
  <main>
    <h1>Work in progress.....</h1> 
    <span>REST server response:</span> <strong style="font-weight: 600; font-style: italic;">{{ apiresponse }}</strong>
    <br><br>
    <h1>Please upload a file</h1>
    <form>
      <input type="file" :modelValue="selectedFile" @change="changeFile($event)" />
      <button type="button" @click="saveFile">Save</button>
    </form>
    <br>
    <p v-if="savedFileResponse">File saved successfully: {{ savedFileResponse }}</p>
    <p v-if="savedFileError">Save file Error: {{ savedFileError }}</p>
  </main>
</template>

<style scoped>

</style>
