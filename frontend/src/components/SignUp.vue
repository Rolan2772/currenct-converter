<template>
  <div>
    <b-container>
      <b-form>
        <b-row>
          <b-col></b-col>
          <b-col>
            <b-form-group id="signUpGroup" label="Login" label-for="login">
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
            <b-form-group id="passwordGroup" label="Repeat Password" label-for="password">
              <b-form-input id="passwordRepeat"
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
            <b-btn variant="info" @click="signUp()">Sign Up</b-btn>
          </b-col>
          <b-col></b-col>
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
          login: '',
          password: ''
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
