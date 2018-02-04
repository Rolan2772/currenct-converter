import axios from "axios";

const BACK_END_URL = 'http://localhost:8081';
const FRONT_END_URL = 'http://localhost:8080';

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
  }
};


