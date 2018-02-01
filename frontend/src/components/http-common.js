import axios from 'axios'
import config from './config.js'

export const auth = axios.create({
  baseURL: config.backendUrl,
  headers: {
    'Access-Control-Allow-Origin': config.frontendUrl
  }
})
