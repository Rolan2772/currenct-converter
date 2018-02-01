<template>
  <div class="hello">
    <div>
      <b-form>
        <b-form-group id="loginFormGroup" label="Your Name:" label-for="login.name">
          <b-form-input id="login.name"
                        type="text"
                        v-model="form.name"
                        required
                        placeholder="Enter name">
          </b-form-input>
        </b-form-group>
      </b-form>
    </div>
    <b-btn variant="info">Login</b-btn>
    <b-btn variant="info" @click="signUp(); showResponse=true">Sign Up</b-btn>
  </div>
</template>

<script>
  import {auth} from './http-common'

  export default {
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App',
        form: {
          name: ''
        }
      }

    },
    methods: {
      signUp() {
        auth.post('users/signup', {
          userName: this.form.name
        })
          .then(response => {
            this.$router.push('/converter');
          })
          .catch(e => {
            console.log(JSON.stringify(e.message));
          })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
