<template>
  <div class="login-container">
    <Card class="login-form">
      <template #content>
        <form @submit.prevent="handleLogin">
          <!-- 1. 아이디 -->
          <div class="form-group">
            <label for="id" class="form-label">아이디</label>
            <InputText id="id" v-model="form.id" required class="form-input" />
          </div>

          <!-- 2. 비밀번호 -->
          <div class="form-group">
            <label for="password" class="form-label">비밀번호</label>
            <Password id="password" v-model="form.password" required class="form-input" :feedback="false" toggleMask />
          </div>

          <!-- 에러 메시지 표시 (필요시) -->
          <div v-if="errorMessage" class="error-message">
            {{ errorMessage }}
          </div>

          <!-- 로그인 버튼 -->
          <Button type="submit" label="로그인" class="submit-button" :loading="loading" />
        </form>
      </template>
    </Card>

    <!-- 회원가입 링크 -->
    <div class="register-prompt">
      <span>계정이 없으신가요?</span>
      <router-link to="/register" class="register-link">회원가입</router-link>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
// 'userApi'에서 loginUser 함수를 가져옵니다.
import { loginUser } from "../api/userApi";

// PrimeVue 컴포넌트 임포트
import Card from "primevue/card";
import InputText from "primevue/inputtext";
import Password from "primevue/password";
import Button from "primevue/button";

const router = useRouter();

const form = ref({
  id: "",
  password: "",
});

const loading = ref(false);
const errorMessage = ref(null);

// '로그인' 버튼 클릭 시 실행될 함수
const handleLogin = async () => {
  if (!form.value.id || !form.value.password) {
    errorMessage.value = "아이디와 비밀번호를 모두 입력해주세요.";
    return;
  }

  loading.value = true;
  errorMessage.value = null; // 이전 에러 메시지 초기화

  try {
    // userApi.js의 loginUser 함수 호출
    await loginUser(form.value.id, form.value.password);

    alert("로그인 성공!");

    // 로그인 성공 시 게시판 메인 페이지로 이동
    router.push("/board");
  } catch (err) {
    console.error("로그인 실패:", err);
    // SecurityConfig의 failureHandler에서 보낸 에러 메시지를 표시
    errorMessage.value = err.response?.data?.message || "로그인에 실패했습니다.";
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
/* RegisterForm.vue와 유사한 스타일 */
.login-container {
  width: 100%;
}

.login-form {
  background-color: white;
  padding: 1.5rem;
  border-radius: 0.5rem;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -2px rgba(0, 0, 0, 0.1);
}

:deep(.login-form .p-card-content) {
  padding: 0;
}

.form-group {
  margin-bottom: 1.5rem; /* 여백 증가 */
}

.form-label {
  display: block;
  font-size: 0.875rem;
  font-weight: 500;
  color: #374151;
  margin-bottom: 0.5rem;
}

.form-input {
  width: 100%;
}
:deep(.form-input .p-inputtext) {
  width: 100%;
}
:deep(.form-input.p-password .p-inputtext) {
  width: 100%;
}

.submit-button {
  width: 100%;
  margin-top: 0.5rem; /* 버튼 위 여백 */
}

/* 회원가입 링크 */
.register-prompt {
  text-align: center;
  margin-top: 1rem;
  font-size: 0.875rem;
  color: #4b5563;
}
.register-link {
  color: #3b82f6;
  text-decoration: none;
}
.register-link:hover {
  text-decoration: underline;
}

/* 에러 메시지 스타일 */
.error-message {
  color: #ef4444; /* red-500 */
  font-size: 0.875rem;
  margin-bottom: 1rem;
  text-align: center;
}
</style>
