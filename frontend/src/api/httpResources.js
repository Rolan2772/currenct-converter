import axios from "axios";
import router from "../router/index"

const BACK_END_URL = 'http://localhost:8081';
const FRONT_END_URL = 'http://localhost:8080';

// axios.interceptors.response.use((response) => response,
//   (error) => {
//     // check if unauthorized error returned
//     if (error.response.status === 401) {
//       // console.log('Unauthorized access')
//       router.replace("/login")
//     }
//     return Promise.reject(error)
//   })

// axios.interceptors.response.use(
//   (response) => {
//     return response
//   },
//   (error) => {
//     const originalRequest = error.config
//     // token expired
//     if (error.response.status === 401 && error.response.data.error == "token_expired") {
//       originalRequest._retry = true
//       store.dispatch('refreshToken').then((response) => {
//         // console.log(response)
//         let token = response.data.token
//         let headerAuth = 'Bearer ' + response.data.token
//         store.dispatch('saveToken', token)
//         axios.defaults.headers['Authorization'] = headerAuth
//         originalRequest.headers['Authorization'] = headerAuth
//         return axios(originalRequest)
//       }).catch((error) => {
//         store.dispatch('logout').then(() => {
//           router.push({ name: 'login' })
//         }).catch(() => {
//           router.push({ name: 'login' })
//         })
//       })
//     }
//     return Promise.reject(error)
//   }
// )

let BASE_REQUEST = axios.create({
  baseURL: BACK_END_URL,
  headers: {
    'Access-Control-Allow-Origin': FRONT_END_URL
  }
});

export const auth = {

  login: function (credentials) {
    return BASE_REQUEST.post('login', 'userName=' + credentials.login, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    });
  },

  signUp: function (credentials) {
    return BASE_REQUEST.post('users/signup', credentials)
  },

  getUser: function () {
    return BASE_REQUEST.get('users/current')
  }
};


