<script setup>

import NavBar from "@/components/NavBar.vue";
</script>

<template>
  <NavBar></NavBar>

  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <h1>Bovinos Registrados</h1>
        <hr/>
        <div class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th scope="col">Número Marca</th>
              <th scope="col">Tipo Marca</th>
              <th scope="col">Tipo Bovino</th>
              <th scope="col">Raza</th>
              <th scope="col">Fecha Adquisición</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(bovine, index) in bovines" :key="index">
              <td>{{ bovine.number_mark }}</td>
              <td>{{ bovine.type_mark }}</td>
              <td>{{ bovine.bovine_type }}</td>
              <td>{{ bovine.breed }}</td>
              <td>{{ bovine.purchase_date }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      bovines: [],
    };
  },
  methods: {
    getBovines() {
      const path = "https://microservices-is2-production-0e1c.up.railway.app/bovine";
      const config = {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token_access")}`,
        },
      }
      axios.get(path, config).then((response) => {
        this.bovines = response.data;
        this.bovines.forEach((bovine) => {
          bovine.purchase_date = new Date(bovine.purchase_date).toLocaleDateString();
        });
      }).catch((error) => {
        console.log(error);
      });
    },
  },
  created() {
    this.getBovines();
  }
};
</script>
<style scoped>
h1 {
  text-align: center;
  margin-top: 15px;
}
</style>