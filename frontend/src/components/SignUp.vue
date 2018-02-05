<template>
  <div>
    <b-container>
      <b-form>
        <b-row>
          <b-col>
            <h2>Sign Up</h2>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-form-group label="Email" label-for="s-email-input">
              <b-form-input id="s-email-input"
                            type="text"
                            placeholder="email"
                            v-model.trim="form.email">
              </b-form-input>
            </b-form-group>
            <b-form-group label="Password (8 or more characters required)" label-for="s-password">
              <b-form-input id="s-password"
                            type="password"
                            placeholder="password"
                            v-model.trim="form.password">
              </b-form-input>
            </b-form-group>
            <b-form-group label="Confirm Password" label-for="s-confirm-pass">
              <b-form-input id="s-confirm-pass"
                            type="password"
                            placeholder="confirm password"
                            v-model.trim="form.confirmPassword">
              </b-form-input>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
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
  import {auth} from './httpResources'

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
          userName: this.form.login
        }).then(response => {
          this.errorMessage = '';
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
