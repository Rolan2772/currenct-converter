import axios from "axios";

if (process.env.NODE_ENV === 'development') {
  axios.defaults.baseURL = 'http://localhost:8081';
  axios.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8080';
}

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

export const auth = {

  login: function (credentials) {
    return axios.post('login', 'userName=' + credentials.login, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    });
  },

  signUp: function (credentials) {
    return axios.post('users/signup', credentials)
  },

  getUser: function () {
    return axios.get('users/current')
  }
};


