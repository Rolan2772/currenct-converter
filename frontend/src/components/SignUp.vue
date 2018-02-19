<template>
  <div>
    <b-container>
      <b-form>
        <b-row align-h="center">
          <b-col sm="12" md="6" lg="4">
            <h2>Sign Up</h2>
          </b-col>
        </b-row>
        <b-row align-h="center">
          <b-col sm="12" md="6" lg="4">
            <b-form-group label="Email" label-for="s-email-input">
              <b-form-input id="s-email-input"
                            type="text"
                            v-model.trim="form.email">
              </b-form-input>
            </b-form-group>
            <b-form-group label="Password (8 or more characters required)" label-for="s-password">
              <b-form-input id="s-password"
                            type="password"
                            v-model.trim="form.password">
              </b-form-input>
            </b-form-group>
            <b-form-group label="Confirm Password" label-for="s-confirm-pass">
              <b-form-input id="s-confirm-pass"
                            type="password"
                            v-model.trim="form.confirmPassword">
              </b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row align-h="center">
          <b-col sm="12" md="6" lg="4">
            <b-btn variant="info" @click="signUp()">Sign Up</b-btn>
            <b-btn variant="info" href="/#/login">Return to Login</b-btn>
          </b-col>
        </b-row>
      </b-form>
    </b-container>
    <b-container align-h="center">
      <!--<b-alert variant="danger" dismissabe show>{{errorMessage}}</b-alert>-->
    </b-container>
  </div>
</template>

<script>
  import {auth} from '../api/httpResources'

  export default {
    name: 'SignUp',
    data() {
      return {
        form: {
          email: '',
          password: '',
          confirmPassword: ''
        },
        errorMessage: 'test'
      }
    },
    methods: {
      signUp() {
        auth.signUp({
          userName: this.form.email
        }).then(response => {
          this.errorMessage = '';
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
