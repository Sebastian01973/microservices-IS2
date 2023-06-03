import {createRouter, createWebHistory} from "vue-router";
import LogIn from "@/components/LogIn.vue";

const routes = [
    {path: '/', name: 'root', component: LogIn},
    // {path: '/user/logIn', name: 'logIn', component: LogIn},
    // {path: '/user/signUp', name: 'signUp', component: SignUp},
    // {path: '/user/home', name: 'homeUser', component: Home},
]

const router = createRouter({
    history: createWebHistory(), routes,
})

export default router