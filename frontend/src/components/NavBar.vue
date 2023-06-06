<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
              aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a v-on:click="home" class="nav-link" href="#">Inicio</a>
          </li>
          <li class="nav-item">
            <a v-on:click="bovino" class="nav-link" href="#">Bovino</a>
          </li>
          <li class="nav-item">
            <a v-on:click="vaccine" class="nav-link" href="#">Vacuna</a>
          </li>
        </ul>
        <form class="d-flex">
<!--          <li v-on:click="logOut" class="nav-item">-->
            <a class="nav-link a-nav-class" href="#">Cerrar Sesion</a>
<!--          </li>/-->
        </form>
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
    vaccine: function () {
      this.$router.push({name: "vaccine"})
    },
    logOut: function () {
      localStorage.clear();
      alert("Sesion Cerrada");
      localStorage.setItem("isAuth", false);
      this.$router.push({name: "root"})
    },
  }
}
</script>

<style scoped>
.a-nav-class{
  color: #fff;
  margin-right: 20px;
}
</style>