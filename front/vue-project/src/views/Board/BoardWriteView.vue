<template>
  <div class="page-wrapper">
    <Header />

    <div class="form-card">
      <h1 class="form-title">새 게시글 작성</h1>

      <form @submit.prevent="handleSubmit">
        <!-- '제목' 입력란 -->
        <div class="form-group">
          <label for="title" class="form-label">제목</label>
          <input type="text" id="title" v-model="form.title" required class="form-input" />
        </div>

        <!-- '내용' 입력란 -->
        <div class="form-group">
          <label for="content" class="form-label">내용</label>
          <textarea id="content" rows="10" v-model="form.content" required class="form-textarea"></textarea>
        </div>

        <!-- 버튼 컨테이너 -->
        <div class="button-container">
          <button type="button" @click="goBack" class="btn btn-secondary">취소</button>
          <button type="submit" class="btn btn-primary">등록</button>
        </div>
      </form>
    </div>
    <Footer />
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import Header from "../../components/Header.vue";
import Footer from "../../components/Footer.vue";
import { createBoard } from "../../api/boardApi";

const router = useRouter();

//
const form = ref({
  title: "",
  content: "",
  writer: "",
});

const handleSubmit = async () => {
  if (!form.value.title.trim() || !form.value.content.trim()) {
    alert("제목과 내용은 필수입니다.");
    return;
  }

  try {
    // 'form.value'에는 title과 content만 담겨있습니다.
    await createBoard(form.value);
    alert("게시글이 성공적으로 등록되었습니다.");
    router.push("/board"); // 목록으로 이동
  } catch (err) {
    console.error("게시글 작성 실패:", err);
    alert("게시글 작성에 실패했습니다.");
  }
};

const goBack = () => {
  router.go(-1); // 이전 페이지로 (목록 or 상세)
};
</script>

<style scoped>
:root {
  --primary-color: #007bff; /* 유쾌한 파란색 */
  --secondary-color: #6c757d; /* 사무적인 회색 */
  --bg-color: #f8f9fa; /* 배경 (기존 gray-100) */
  --card-bg: #ffffff; /* 카드 배경 */
  --text-color: #212529; /* 기본 텍스트 */
  --label-color: #495057; /* 라벨 (기존 gray-700) */
  --border-color: #ced4da; /* 테두리 (기존 gray-300) */
  --focus-ring-color: rgba(0, 123, 255, 0.25); /* 유쾌한 포커스 효과 */
}

/* 페이지 전체를 감싸는 래퍼
  (min-h-screen bg-gray-100)
*/
.page-wrapper {
  min-height: 100vh;
  background-color: var(--bg-color);
  padding-bottom: 4rem; /* 푸터 공간 확보 */
}

/*
  흰색 폼 카드
  (max-w-3xl mx-auto p-6 bg-white shadow rounded-lg mt-8)
*/
.form-card {
  max-width: 56rem; /* 896px  */
  margin: 2rem auto; /* mt-8 mx-auto */
  padding: 2.5rem; /* p-10 (p-6보다 넓게) */
  background-color: var(--card-bg);
  border-radius: 0.75rem; /* rounded-lg (조금 더 부드럽게) */
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.05), 0 4px 6px -2px rgba(0, 0, 0, 0.05); /* shadow (더 은은하게) */
  border: 1px solid #e5e7eb; /* 경계선 추가 */
}

/*
  폼 제목
  (text-3xl font-bold mb-6 text-center)
*/
.form-title {
  font-size: 1.875rem; /* text-3xl */
  font-weight: 700; /* font-bold */
  margin-bottom: 1.5rem; /* mb-6 */
  text-align: center;
  color: var(--text-color);
}

/*
  폼 그룹 (라벨 + 입력창)
  (mb-4 / mb-6)
*/
.form-group {
  margin-bottom: 1.5rem; /* mb-6 */
}

/*
  폼 라벨
  (block text-sm font-medium text-gray-700)
*/
.form-label {
  display: block;
  font-size: 0.875rem; /* text-sm */
  font-weight: 600; /* font-medium (조금 더 진하게) */
  color: var(--label-color);
  margin-bottom: 0.5rem; /* 라벨과 입력창 사이 여백 */
}

/*
  공통 입력창 스타일 (input, textarea)
  (mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm)
*/
.form-input,
.form-textarea {
  display: block;
  width: 100%;
  padding: 0.75rem 1rem; /* px-3 py-2 (조금 더 넉넉하게) */
  font-size: 1rem;
  line-height: 1.5;
  color: var(--text-color);
  background-color: var(--card-bg);
  border: 1px solid var(--border-color);
  border-radius: 0.375rem; /* rounded-md */
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-sm */
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

/* "유쾌한" 포커스 효과 */
.form-input:focus,
.form-textarea:focus {
  border-color: var(--primary-color);
  outline: 0;
  box-shadow: 0 0 0 0.2rem var(--focus-ring-color);
}

.form-textarea {
  resize: none;
}

/*
  버튼 컨테이너
  (flex justify-end space-x-2)
*/
.button-container {
  display: flex;
  justify-content: flex-end;
  margin-top: 2rem; /* 폼과 버튼 사이 여백 */
}

/*
  공통 버튼 스타일
  (px-4 py-2 rounded)
*/
.btn {
  padding: 0.65rem 1.25rem; /* px-4 py-2 (조금 더 크게) */
  font-size: 0.9rem;
  font-weight: 600;
  border: none;
  border-radius: 0.375rem; /* rounded */
  cursor: pointer;
  transition: background-color 0.2s ease, box-shadow 0.2s ease;
  margin-left: 0.5rem; /* space-x-2 */
}

/*
  기본(등록) 버튼 - "유쾌한"
  (bg-blue-500 text-white hover:bg-blue-600)
*/
.btn-primary {
  background-color: var(--primary-color);
  color: white;
}
.btn-primary:hover {
  background-color: #0056b3; /* 더 진한 파란색 */
  box-shadow: 0 4px 8px rgba(0, 123, 255, 0.2);
}

/*
  보조(취소) 버튼 - "사무적인"
  (bg-gray-500 text-white hover:bg-gray-600)
*/
.btn-secondary {
  background-color: var(--secondary-color);
  color: white;
}
.btn-secondary:hover {
  background-color: #5a6268;
}
</style>
