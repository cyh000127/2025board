<template>
  <div class="max-w-md mx-auto p-8 bg-white shadow-lg rounded-lg">
    <h2 class="text-2xl font-bold mb-6 text-center">로그인</h2>
    <form @submit.prevent="login">
      <input v-model="username" placeholder="아이디" class="input" />
      <input v-model="password" type="password" placeholder="비밀번호" class="input" />
      <button type="submit" class="btn">로그인</button>
    </form>
    <p class="mt-4 text-center">
      계정이 없나요?
      <router-link to="/register" class="text-blue-500">회원가입</router-link>
    </p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { loginUser } from '../api/authApi'

const router = useRouter()
const username = ref('')
const password = ref('')

const login = async () => {
  try {
    await loginUser({ username: username.value, password: password.value })
    router.push('/board')
  } catch (err) {
    alert('로그인 실패: ' + err.message)
  }
}
</script>

<style scoped>
.input {
  width: 100%;
  padding: 0.75rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 0.5rem;
}
.btn {
  width: 100%;
  padding: 0.75rem;
  background-color: #3b82f6;
  color: white;
  font-weight: bold;
  border-radius: 0.5rem;
  transition: background 0.3s;
}
.btn:hover {
  background-color: #2563eb;
}
</style>
