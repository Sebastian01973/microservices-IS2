<script setup>

import NavBar from "@/components/NavBar.vue";
</script>

<template>
  <NavBar></NavBar>
  <div class="container">
    <div class="row">
      <div class="col-sm-12">
        <h1>Vacunas Registradas</h1>
        <hr/>
        <div class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th scope="col">Nombre</th>
              <th scope="col">Laboratorio</th>
              <th scope="col">Lote</th>
              <th scope="col">Cantidad</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(vaccine, index) in vaccines" :key="index">
              <td>{{ vaccine.name }}</td>
              <td>{{ vaccine.laboratory }}</td>
              <td>{{ vaccine.batch }}</td>
              <td>{{ vaccine.amount_dose }}</td>
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
        vaccines: [],
      };
    },
    methods: {
      getVaccines() {
        const path = "https://microservices-is2-production-0e1c.up.railway.app/vaccine";
        const config = {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token_access")}`,
          },
        }
        axios.get(path, config).then((response) => {
          this.vaccines = response.data;
        }).catch((error) => {
          console.log(error);
        });
      },
    },
    created() {
      this.getVaccines();
    }
  };
</script>

<style scoped>
  h1 {
    text-align: center;
    margin-top: 15px;
  }
</style>