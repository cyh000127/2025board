<template>
  <div class="page-wrapper">
    <Header />
    <div class="form-container">
      <h1 class="form-title">로그인</h1>
      <!-- components/LoginForm 랜더링  -->
      <LoginForm />
    </div>
    <Footer />
  </div>
</template>

<script setup>
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import LoginForm from "../../components/LoginForm.vue";
</script>

<style scoped>
.page-wrapper {
  min-height: 100vh;
  background-color: #f8f9fa; /* --bg-color */
  padding-bottom: 4rem;
}

/* 폼을 감싸는 컨테이너 */
.form-container {
  max-width: 28rem; /* 448px (로그인 폼이므로 Register보다 좁게) */
  margin: 4rem auto; /* mt-16 mx-auto (위쪽 여백 증가) */
}

.form-title {
  font-size: 1.875rem; /* text-3xl */
  font-weight: 700; /* font-bold */
  margin-bottom: 1.5rem; /* mb-6 */
  text-align: center;
  color: #212529; /* --text-color */
}
</style>
