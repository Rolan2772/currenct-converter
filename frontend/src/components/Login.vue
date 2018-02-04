<template xmlns="http://www.w3.org/1999/html">
  <div>
    <b-container>
      <b-form>
        <b-row>
          <b-col></b-col>
          <b-col>
            <b-form-group id="loginGroup" label="Login" label-for="login">
              <b-form-input id="login"
                            type="text"
                            v-model.trim="form.login">
              </b-form-input>
            </b-form-group>
            <b-form-group id="passwordGroup" label="Password" label-for="password">
              <b-form-input id="password"
                            type="password"
                            v-model.trim="form.password">
              </b-form-input>
            </b-form-group>
          </b-col>
          <b-col></b-col>
        </b-row>
        <b-row>
          <b-col></b-col>
          <b-col>
            <b-btn variant="info" @click="login()">Login</b-btn>
            <b-btn variant="info" @click="$router.push('/signup')">Sign Up</b-btn>
          </b-col>
          <b-col></b-col>
        </b-row>
      </b-form>
    </b-container>
    <b-container align-h="center">
      <!--<b-alert variant="danger" dismissible show>{{errorMessage}}</b-alert>-->
    </b-container>
  </div>
</template>

<script>
  import {auth} from './httpResources'

  export default {
    name: 'Login',
    data() {
      return {
        form: {
          login: '',
          password: ''
        },
        errorMessage: ''
      }

    },
    methods: {
      login() {
        auth.login({
            login: this.form.login
        }).then(response => {
          this.$router.push('/converter');
        }).catch(e => {
          this.errorMessage = e.response && e.response.data && e.response.data.message || e.message;
        })
      }
    }
  }
</script>

<style>
</style>
