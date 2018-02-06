<template xmlns="http://www.w3.org/1999/html">
  <div>
    <b-container>
      <b-form>
        <b-row>
          <b-col>
            <h2>Login</h2>
          </b-col>
        </b-row>
          <b-row>
            <b-col>
              <b-form-group label="Email" label-for="l-email-input">
                <b-form-input id="l-email-input"
                              type="text"
                              placeholder="email"
                              v-model.trim="form.email">
                </b-form-input>
              </b-form-group>
              <b-form-group label="Password" label-for="l-password">
                <b-form-input id="l-password"
                              type="password"
                              placeholder="password"
                              v-model.trim="form.password">
                </b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-btn variant="info" @click="login()">Login</b-btn>
              <b-btn variant="info" router-link to="/signup">Sign Up</b-btn>
            </b-col>
          </b-row>
      </b-form>
    </b-container>
    <b-container align-h="center">
      <!--<b-alert variant="danger" dismissible show>{{errorMessage}}</b-alert>-->
    </b-container>
  </div>
</template>

<script>
  import {auth} from '../api/httpResources'

  export default {
    name: 'Login',
    data() {
      return {
        form: {
          email: '',
          password: ''
        },
        errorMessage: ''
      }

    },
    methods: {
      login() {
        auth.login({
          login: this.form.email
        }).then(response => {
          this.$store.commit('login', {name: this.form.email});
          this.$router.push('/');
        }).catch(e => {
          this.errorMessage = e.response && e.response.data && e.response.data.message || e.message;
        })
      }
    }
  }
</script>

<style>
</style>
