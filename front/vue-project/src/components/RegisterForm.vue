<template>
  <div class="max-w-md mx-auto p-8 bg-white shadow-lg rounded-lg">
    <h2 class="text-2xl font-bold mb-6 text-center">회원가입</h2>
    <form @submit.prevent="register">
      <input v-model="username" placeholder="아이디" class="input" />
      <input v-model="password" type="password" placeholder="비밀번호" class="input" />
      <input v-model="email" placeholder="이메일" class="input" />
      <button type="submit" class="btn">회원가입</button>
    </form>
    <p class="mt-4 text-center">
      이미 계정이 있나요?
      <router-link to="/login" class="text-blue-500">로그인</router-link>
    </p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { registerUser } from '../api/authApi'

const router = useRouter()
const username = ref('')
const password = ref('')
const email = ref('')

const register = async () => {
  try {
    await registerUser({ username: username.value, password: password.value, email: email.value })
    alert('회원가입 성공!')
    router.push('/login')
  } catch (err) {
    alert('회원가입 실패: ' + err.message)
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
  background-color: #10b981;
  color: white;
  font-weight: bold;
  border-radius: 0.5rem;
  transition: background 0.3s;
}
.btn:hover {
  background-color: #059669;
}
</style>
