import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import  './assets/css/style.css';
import 'dayjs/locale/zh-cn';

 


createApp(App).use(store).use(router).use(ElementPlus ).mount('#app')
