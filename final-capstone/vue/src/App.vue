<template>
  <div id="app">
    <div id="nav">
      <router-link class="link" v-bind:to="{ name: 'home' }">Home</router-link
      >&nbsp;|&nbsp;
      <router-link
        class="link"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
      &nbsp;|&nbsp;
      <router-link class="link" v-bind:to="{ name: 'searchRecipes' }"
        >Search Recipes</router-link
      >
    </div>
    <div class="loading" v-if="isLoading">
      <img src="@/assets/fry_pan.gif" />
    </div>
    <router-view />
  </div>
</template>
<script>
export default {
  data() {
    return {
      isLoading: true,
    };
  },
  created() {
    this.isLoading = false;
  },
};
</script>

<style>
html {
  background: url(https://images.pexels.com/photos/3850888/pexels-photo-3850888.jpeg?cs=srgb&dl=pexels-ready-made-3850888.jpg&fm=jpg)
    center;
  background-size: cover;
  font-family: monospace;
  text-align: center;
  height: 100%;
}
button {
  display: inline-block;
  padding: 0.3em 1.2em;
  margin: 0 0.3em 0.3em 0;
  border-radius: 2em;
  box-sizing: border-box;
  text-decoration: none;
  font-family: monospace;
  font-size: 14pt;
  font-weight: 300;
  color: #ffffff;
  background-color: #94c973;
  text-align: center;
  transition: all 0.2s;
  width: 300px;
  height: 50px;
  cursor: pointer;
}

button:hover {
  background-color: #e76f51;
}

h1 {
  color: #e76f51;
  background-color: #264653;
  font-size: 36pt;
  border-radius: 25px;
  width: 75%;
  display: inline-block;
}

#nav {
  font-size: 17pt;
  background-color: rgba(255, 253, 253, 0.7);
  border-radius: 25px;
}

.link {
  text-decoration: none;
  color: #264653;
}

table {
  background-color: #94c973;
  border-radius: 25px;
  margin: 15px 0px 0px 0px;
  padding: 15px;
  margin-left: auto;
  margin-right: auto;
  font-size: 16pt;
}

label {
  font-size: 14pt;
  background-color: rgba(255, 253, 253, 0.5);
  border-radius: 25px;
  text-align: center;
  /* padding-left: 10px; */
  padding: 2px;
}

form {
  background: #94c973;
  margin: 4em auto;
  width: 90%;
  max-width: 1080px;
  text-align: center;
  padding: 15px;
  border-radius: 25px;
}

input[type="password"],
input[type="text"],
input[type="number"] {
  font-size: 14px;
  font-family: monospace;
  display: inline-block;
  padding: 8px;
  margin: 0 auto;
}

router-link {
  cursor: pointer;
}
</style>


