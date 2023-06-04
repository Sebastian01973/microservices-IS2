import {createRouter, createWebHistory} from "vue-router";
import LogIn from "@/components/LogIn.vue";
import SignUp from "@/components/SignUp.vue";
import Home from "@/components/Home.vue";
import Bovino from "@/components/Bovino.vue";
import Vaccine from "@/components/Vaccine.vue";

const routes = [
    {path: '/', name: 'root', component: LogIn},
    {path: '/user/logIn', name: 'logIn', component: LogIn},
    {path: '/user/signUp', name: 'signUp', component: SignUp},
    {path: '/user/home', name: 'homeUser', component: Home},
    {path: '/user/bovino', name: 'bovino', component: Bovino},
    {path: '/admin/vaccine/', name: 'vaccine', component: Vaccine},
]

const router = createRouter({
    history: createWebHistory(), routes,
})

export default router