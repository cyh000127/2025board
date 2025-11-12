// src/router/index.js

import { createRouter, createWebHistory } from 'vue-router'

// --- Board 관련 뷰 임포트 ---
// '../views/Board/' 경로에서 가져옵니다.
import BoardView from '../views/Board/BoardView.vue'
import BoardDetailView from '../views/Board/BoardDetailView.vue'
import BoardWriteView from '../views/Board/BoardWriteView.vue'
import BoardEditView from '../views/Board/BoardEditView.vue'

// --- User 관련 뷰 임포트 ---
// '../views/User/' 경로에서 가져옵니다.
import LoginView from '../views/User/LoginView.vue'
import RegisterView from '../views/User/RegisterView.vue'

const routes = [
  { path: '/', redirect: '/board' },

  // Board 라우트
  { path: '/board', name: 'BoardView', component: BoardView },
  { path: '/board/:no', name: 'BoardDetailView', component: BoardDetailView, props: true },
  { path: '/board/write', name: 'BoardWriteView', component: BoardWriteView },
  { path: '/board/edit/:no', name: 'BoardEditView', component: BoardEditView, props: true },

  // User 라우트
  { path: '/login', name: 'LoginView', component: LoginView },
  { path: '/register', name: 'RegisterView', component: RegisterView }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router