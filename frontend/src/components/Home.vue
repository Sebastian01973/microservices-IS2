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
    <p class="m-5" style="text-align: justify;">CowShield, se destaca como un producto excepcional gracias a su enfoque integral y fácil de usar. Diseñada específicamente para ganaderos, ofrece un seguimiento completo de las actividades relacionadas con el ganado. Desde registros de salud hasta seguimiento de peso y ubicación.</p>
    <div class="container" data-aos="fade-up">
      <div class="row gy-4">
        <div class="col-lg-6 position-relative about-img"
             style="background-image: url('https://source.unsplash.com/PCf58A5427A') ;" data-aos="fade-up"
             data-aos-delay="150">
        </div>
        <div class="col-lg-5 d-flex align-items-end" data-aos="fade-up" data-aos-delay="300">
          <div class="content ps-0 ps-lg-4">
            <p style="text-align: justify;">
              Nuestra interfaz intuitiva y las características clave de CowShield están diseñadas para maximizar la productividad 
              y el bienestar del ganado. Con el módulo de control de vacunas, los usuarios pueden mantener un seguimiento exhaustivo 
              de las vacunas administradas a su ganado, garantizando una gestión sanitaria efectiva y segura.
            </p>
            <p>
              CowShield es la solución líder en gestión de ganado, gracias a su sistema de control de vacunas, su módulo de aforador 
              y su completo panel de administración. Con CowShield, llevar un control efectivo de tu ganado nunca ha sido tan sencillo y confiable.
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