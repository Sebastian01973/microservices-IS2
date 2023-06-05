<template>
  <div class="container">
    <div class="col-md-8 offset-md-2 mt-4">
      <h1 class="text-center mb-4">Registrar usuario</h1>
      <form v-on:submit.prevent="processSignUp">
        <div class="row">
          <div class="col-md-6">
            <div class="form-group mb-4">
              <label for="InputNombre">Nombre</label>
              <input
                  type="text"
                  v-model="user.nombre"
                  class="form-control"
                  id="nombre"
                  placeholder="Nombre"
                  required="required"
              />
            </div>  

            <div class="form-group mb-4">
              <label for="InputApellido">Apellido</label>
              <input
                  type="text"
                  v-model="user.apellido"
                  class="form-control"
                  id="apellido"
                  placeholder="Apellido"
                  required="required"
              />
            </div>

            <div class="form-group mb-4">
              <label for="InputPassword">Contraseña</label>
              <input
                  type="password"
                  v-model="user.password"
                  class="form-control"
                  id="password"
                  placeholder="Contraseña"
                  required="required"
              />
            </div>

            <div class="form-group mb-4">
              <label for="exampleInputName">Numero de documento</label>
              <input
                  type="text"
                  v-model="user.numero_documento"
                  class="form-control"
                  id="numeroDocumento"
                  placeholder="Numero de documento"
                  required="required"
              />
            </div>
          </div>

          <div class="col-md-6">
            <div class="form-group mb-4">
              <label for="InputUsername" required="required">Nombre de usuario</label>
              <input
                  for="InputUsername"
                  class="form-control"
                  type="text"
                  v-model="user.username"
                  id="username"
                  placeholder="Nombre de usuario"
                  required="required"
              />
            </div>
           
            <div class="form-group mb-4">
              <label for="InputEmail1" required="required">Correo Electronico</label>
              <input
                  type="email"
                  v-model="user.email"
                  class="form-control"
                  id="email"
                  aria-describedby="emailHelp"
                  placeholder="Email"
                  required="required"
              />
            </div>

            <!--        Selecionar tipo de documento  obligaotrio-->
            <div class="form-group mb-4">
              <label for="InputTipoDocumento">Tipo de documento</label>
              <select
                  class="form-control"
                  id="tipoDocumento"
                  v-model="user.tipo_documento"
                  required="required"
              >
                <option selected value="CC">Cedula de ciudadania</option>
                <option value="TI">Tarjeta de identidad</option>
                <option value="CE">Cedula de extranjeria</option>
                <option value="RC">Registro civil</option>
                <option value="PA">Pasaporte</option>
              </select>
            </div>

           <div class="form-group mb-4">
              <label for="InputDireccion">Direccion</label>
              <input
                  type="text"
                  v-model="user.direccion"
                  class="form-control"
                  id="direccion"
                  placeholder="Direccion"
                  required="required"
              />
           </div>
          </div>
          <div class="form-group mb-4">
              <label for="InputTelefono">Telefono</label>
              <input
                  type="text"
                  v-model="user.telefono"
                  class="form-control"
                  id="telefono"
                  placeholder="Telefono"
                  required="required"
              />
            </div>
        </div>
        <hr/>
        <div class="success" v-if="isSuccess">
          We received your submission, thank you!
        </div>

        <div class="col-md-6 offset-md-3 mt-5 text-center">
          <button type="submit" class="btn btn-primary btn-lg btn-block">Registrar</button>
        </div>
      </form>
    </div>
  </div>

</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";

export default {
  name: "SignUp",
  data: function () {
    return {
      user: {
        username: "",
        password: "",
        nombre: "",
        apellido: "",
        email: "",
        tipo_documento: "CC",
        numero_documento: "",
        direccion: "",
        telefono: "",
      }
    }
  },

  methods: {
    processSignUp: function () {
      axios.post(
          'https://axios.com/user/',
          this.user,
          {headers: {}}
      ).then((result) => {
        let dataSignUp = {
          username: this.user.username,
          token_access: result.data.access,
          token_refresh: result.data.refresh,
        }
        this.$emit('completedSignUp', dataSignUp);
      }).catch((error) => {
        alert("Error: Fallo en el registro" + error.message);
      });
    }
  }
}


</script>

<style scoped>
h3 {
  margin: 40px 0 0;
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