<template>
  <div class="register-container">
    <form @submit.prevent="handleSubmit" class="register-form">
      
      <div class="form-group">
        <label for="username" class="form-label">아이디</label>
        <input type="text" id="username" v-model="form.username" required class="form-input">
      </div>
      
      <div class="form-group">
        <label for="password" class="form-label">비밀번호</label>
        <input type="password" id="password" v-model="form.password" required class="form-input">
      </div>
      
      <div class="form-group">
        <label for="email" class="form-label">이메일</label>
        <input type="email" id="email" v-model="form.email" required class="form-input">
      </div>
      
      <div class="form-group">
        <label for="mbti" class="form-label">MBTI (선택)</label>
        <input type="text" id="mbti" v-model="form.mbti" maxlength="4" class="form-input">
      </div>
      
      <button type="submit" class="submit-button">
        회원가입
      </button>
    </form>
    
    <div class="login-prompt">
      <span>이미 계정이 있나요? </span>
      <router-link to="/login" class="login-link">로그인</router-link>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { registerUser } from '../api/userApi' 

const router = useRouter()

// 1. form 객체에서 'name' 필드 제거
const form = ref({
  username: '',
  password: '',
  email: '',
  mbti: ''  // 'name' 필드 완전 삭제
})

const handleSubmit = async () => {
  // 2. 유효성 검사에서 'name' 제거
  if (!form.value.username || !form.value.password || !form.value.email) {
    alert('아이디, 비밀번호, 이메일은 필수입니다.');
    return;
  }

  try {
    // 3. 4개 필드가 담긴 form.value를 API로 전송
    const response = await registerUser(form.value);
    
    alert('회원가입이 성공적으로 완료되었습니다!');
    router.push('/login'); 

  } catch (err) {
    console.error("회원가입 실패:", err);
    const errorMessage = err.response?.data || "회원가입 중 오류가 발생했습니다.";
    alert(errorMessage);
  }
}
</script>

<style scoped>
.register-container {
  
  width: 100%;
}

.register-form {
  background-color: white;
  padding: 1.5rem; /* p-6 */
  border-radius: 0.5rem; /* rounded-lg */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -2px rgba(0, 0, 0, 0.1); /* shadow */
}

.form-group {
  margin-bottom: 1rem; /* mb-4 */
}

.form-label {
  display: block;
  font-size: 0.875rem; /* text-sm */
  font-weight: 500; /* font-medium */
  color: #374151; /* text-gray-700 */
}

.form-input {
  margin-top: 0.25rem; /* mt-1 */
  display: block;
  width: 100%; /* w-full */
  padding: 0.5rem 0.75rem; /* px-3 py-2 */
  border: 1px solid #D1D5DB; /* border border-gray-300 */
  border-radius: 0.375rem; /* rounded-md */
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-sm */
  
  /* focus:outline-none focus:ring-blue-500 focus:border-blue-500 */
  outline: none;
}
.form-input:focus {
  border-color: #3B82F6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.4);
}

.submit-button {
  width: 100%; /* w-full */
  padding: 0.5rem 1rem; /* px-4 py-2 */
  background-color: #3B82F6; /* bg-blue-500 */
  color: white; /* text-white */
  border: none;
  border-radius: 0.375rem; /* rounded */
  cursor: pointer;
  transition: background-color 0.2s;
}
.submit-button:hover {
  background-color: #2563EB; /* hover:bg-blue-600 */
}

/* "이미 계정이 있나요?" 링크 */
.login-prompt {
  text-align: center;
  margin-top: 1rem; /* mt-4 */
  font-size: 0.875rem; /* text-sm */
  color: #4B5563; /* text-gray-600 */
}

.login-link {
  color: #3B82F6; /* text-blue-500 */
  text-decoration: none;
}
.login-link:hover {
  text-decoration: underline; /* hover:underline */
}
</style>