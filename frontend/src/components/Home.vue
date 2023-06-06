<script setup>
import NavBar from "@/components/NavBar.vue";
</script>

<template>
  <NavBar></NavBar>
  <body>
  <!--  <div class="container">-->
  <!--    <h2>¿Por qué es nuestro mejor producto?<br>Cow Shield</h2>-->
  <!--  </div>-->

  <!-- ======= About Section ======= -->
  <section id="about" class="about">
    <h3 class="text-center">Cow Shield</h3>
    <p class="text-center"> Nuestra aplicación de control de bovinos es un
      excelente
      producto porque ofrece un seguimiento integral y fácil de usar para la gestión del ganado. Proporciona
      funciones como registros de salud, seguimiento de peso y ubicación, y estadísticas detalladas. Nuestra
      interfaz intuitiva y las características clave ayudan a los ganaderos a optimizar la productividad y el
      bienestar del ganado.</p>
    <div class="container" data-aos="fade-up">
      <div class="row gy-4">
        <div class="col-lg-6 position-relative about-img"
             style="background-image: url('https://source.unsplash.com/PCf58A5427A') ;" data-aos="fade-up"
             data-aos-delay="150">
        </div>
        <div class="col-lg-5 d-flex align-items-end" data-aos="fade-up" data-aos-delay="300">
          <div class="content ps-0 ps-lg-5">
            <p class="fst-italic">
              Con el control de vacunas puede llevar un mejor control de las vacunas que se le han aplicado a su
              ganado.
            </p>
            <ul>
              <li><i class="bi bi-check2-all"></i> Aforador registra todo el ganado.</li>
              <li><i class="bi bi-check2-all"></i> Administrador cuenta con dashboard para la gestion.</li>
              <li><i class="bi bi-check2-all"></i> Puedes agregar vacunas y ver el historial de vacunacion.
              </li>
            </ul>
            <p>
              CowShield es la mejor aplicación para la gestión de ganado, ya que cuenta con un sistema de control
              de vacunas, aforador y administrador. Con CowShield puedes llevar un mejor control de tu ganado.
            </p>

            <div class="position-relative mt-1">
              <img src="https://source.unsplash.com/81mllqC4JiU" class="img-fluid" alt="">
              <a href="https://www.youtube.com/watch?v=LXb3EKWsInQ" class="glightbox play-btn"></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <div>
    <hr>
  </div>
  <!-- End About Section -->
  </body>
</template>
<script>
import "bootstrap/dist/css/bootstrap.min.css";
</script>


<style scoped>

h3 {
  text-align: center;
  font-size: 40px;
  color: #000000;
  margin-top: 10px;
}

p {
  text-align: justify-all;
  font-size: 20px;
  color: #000000;
  padding-left: 30px;
  padding-right: 30px;
//padding: 50px;
}
</style>