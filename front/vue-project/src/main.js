import "./assets/main.css";

import { createApp } from "vue";
import { createPinia } from "pinia";

// --- PrimeVue --
import PrimeVue from "primevue/config";
import Aura from "@primeuix/themes/aura";

import "primeicons/primeicons.css";

import App from "./App.vue";
import router from "./router";

const app = createApp(App);

// -- PrimeVue --
app.use(PrimeVue);

app.use(createPinia());
app.use(router);
app.mount("#app");
