import axios from "axios";

if (process.env.NODE_ENV === 'development') {
  axios.defaults.baseURL = 'http://localhost:8081';
  axios.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8080';
}

axios.interceptors.response.use((response) => response,
  (error) => {
    if (error.response.status === 401) {
      router.replace("/login")
    }
    return Promise.reject(error)
  })

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


