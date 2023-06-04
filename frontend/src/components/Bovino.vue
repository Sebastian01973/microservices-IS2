<script setup>

import NavBar from "@/components/NavBar.vue";
</script>

<template>
  <NavBar></NavBar>

  <div class="container">
    <div class="row">
      <div class="col-sm-10">
        <h1>Bovino</h1>
        <hr/>
        <br/><br/>
        <alert v-if="showMessage" :message="message"></alert>
        <button
            class="btn btn-success btn-sm"
            type="button"
            @click="toggleAddBookModal">
          Agregar Bovino
        </button>
        <br/><br/>
        <div class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th scope="col">Numero Marca</th>
              <th scope="col">Tipo Marca</th>
              <th scope="col">Genero</th>
              <th scope="col">Raza</th>
              <th scope="col">Fecha Nacimiento</th>
              <th>Acciones</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="(bovine, index) in bovines" :key="index">
              <td>{{ bovine.numberMark }}</td>
              <td>{{ bovine.typeMark }}</td>
              <td>{{ bovine.gender }}</td>
              <td>{{ bovine.breed }}</td>
              <td>{{ bovine.birthDate }}</td>
              <!--            <td>-->
              <!--              <span v-if="book.read">Yes</span>-->
              <!--              <span v-else>No</span>-->
              <!--            </td>-->
              <td>
                <div class="btn-group" role="group">
                  <button
                      class="btn btn-primary btn-sm"
                      type="button"
                      @click="toggleEditBookModal(book)">
                    Update
                  </button>
                  <button
                      class="btn btn-danger btn-sm"
                      type="button"
                      @click="handleDeleteBook(book)">
                    Delete
                  </button>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <!-- add new book modal -->
  <div
      ref="addBookModal"
      :class="{ show: activeAddBookModal, 'd-block': activeAddBookModal }"
      class="modal fade"
      role="dialog"
      tabindex="-1">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Add a new book</h5>
          <button
              aria-label="Close"
              class="close"
              data-dismiss="modal"
              type="button"
              @click="toggleAddBookModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label class="form-label" for="addBookTitle">Title:</label>
              <input
                  id="addBookTitle"
                  v-model="addBookForm.title"
                  class="form-control"
                  placeholder="Enter title"
                  type="text"/>
            </div>
            <div class="mb-3">
              <label class="form-label" for="addBookAuthor">Author:</label>
              <input
                  id="addBookAuthor"
                  v-model="addBookForm.author"
                  class="form-control"
                  placeholder="Enter author"
                  type="text"/>
            </div>
            <div class="mb-3 form-check">
              <input
                  id="addBookRead"
                  v-model="addBookForm.read"
                  class="form-check-input"
                  type="checkbox"/>
              <label class="form-check-label" for="addBookRead">Read?</label>
            </div>
            <div class="btn-group" role="group">
              <button
                  class="btn btn-primary btn-sm"
                  type="button"
                  @click="handleAddSubmit">
                Submit
              </button>
              <button
                  class="btn btn-danger btn-sm"
                  type="button"
                  @click="handleAddReset">
                Reset
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div v-if="activeAddBookModal" class="modal-backdrop fade show"></div>
  <!-- edit book modal -->
  <div
      ref="editBookModal"
      :class="{ show: activeEditBookModal, 'd-block': activeEditBookModal }"
      class="modal fade"
      role="dialog"
      tabindex="-1">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Update</h5>
          <button
              aria-label="Close"
              class="close"
              data-dismiss="modal"
              type="button"
              @click="toggleEditBookModal">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label class="form-label" for="editBookTitle">Title:</label>
              <input
                  id="editBookTitle"
                  v-model="editBookForm.title"
                  class="form-control"
                  placeholder="Enter title"
                  type="text">
            </div>
            <div class="mb-3">
              <label class="form-label" for="editBookAuthor">Author:</label>
              <input
                  id="editBookAuthor"
                  v-model="editBookForm.author"
                  class="form-control"
                  placeholder="Enter author"
                  type="text">
            </div>
            <div class="mb-3 form-check">
              <input
                  id="editBookRead"
                  v-model="editBookForm.read"
                  class="form-check-input"
                  type="checkbox">
              <label class="form-check-label" for="editBookRead">Read?</label>
            </div>
            <div class="btn-group" role="group">
              <button
                  class="btn btn-primary btn-sm"
                  type="button"
                  @click="handleEditSubmit">
                Submit
              </button>
              <button
                  class="btn btn-danger btn-sm"
                  type="button"
                  @click="handleEditCancel">
                Cancel
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div v-if="activeEditBookModal" class="modal-backdrop fade show"></div>

</template>
<script>
import axios from "axios";
import Alert from './Alert.vue';

export default {
  data() {
    return {
      activeAddBookModal: false,
      addBookForm: {
        id: "",
        title: "",
        author: "",
        read: [],
      },
      activeEditBookModal: false,
      editBookForm: {
        id: '',
        title: '',
        author: '',
        read: [],
      },
      bovines: [],
      message: "",
      showMessage: false,
    };
  },
  components: {
    alert: Alert,
  },
  methods: {
    addBook(payload) {
      const path = "http://localhost:5000/bovine";
      axios
          .post(path, payload)
          .then(() => {
            this.getBooks();
            this.message = "Bovine added";
            this.showMessage = true;
          })
          .catch((error) => {
            console.log(error);
            this.getBooks();
          });
    },
    getBooks() {
      const path = "http://localhost:5000/bovine";
      axios
          .get(path)
          .then((res) => {
            // this.books = res.data.books;
            // console.log(res.data);
            // convertir data en bovine
            this.bovines = res.data;
          })
          .catch((error) => {
            console.error(error);
          });
    },
  },
  created() {
    this.getBooks();
  }
};
</script>
<style scoped>

</style>