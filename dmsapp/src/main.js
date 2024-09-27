import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from './plugins/axios'


const app = createApp(App)

app.use(router)
app.use(axios, {
  baseUrl: 'http://localhost:8082',
})
app.mount('#app')
