<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item active">
            <a v-on:click="home" class="nav-link" href="#">Inicio</a>
          </li>
          <li class="nav-item">
            <a v-on:click="bovino" class="nav-link" href="#">Crear Bovino</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    home: function () {
      this.$router.push({name: "homeUser"})
    },
    bovino: function () {
      this.$router.push({name: "bovino"})
    },
    logOut: function(){
      localStorage.clear();
      alert("Sesion Cerrada");
      localStorage.setItem("isAuth",false);
      this.$router.push({name: "root"})
    },
  }
}
</script>

<style scoped>

</style>