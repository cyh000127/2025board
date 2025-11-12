<template>
  <div class="min-h-screen bg-gray-100">
    <Header />
    <div class="max-w-3xl mx-auto p-6 bg-white shadow rounded-lg mt-8">
      <h1 class="text-3xl font-bold mb-6 text-center">새 게시글 작성</h1>
      
      <form @submit.prevent="handleSubmit">
        <div class="mb-4">
          <label for="writer" class="block text-sm font-medium text-gray-700">작성자</label>
          <input 
            type="text" 
            id="writer" 
            v-model="form.writer"
            required
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        
        <div class="mb-4">
          <label for="title" class="block text-sm font-medium text-gray-700">제목</label>
          <input 
            type="text" 
            id="title" 
            v-model="form.title"
            required
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
        
        <div class="mb-6">
          <label for="content" class="block text-sm font-medium text-gray-700">내용</label>
          <textarea 
            id="content" 
            rows="10" 
            v-model="form.content"
            required
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500"
          ></textarea>
        </div>
        
        <div class="flex justify-end space-x-2">
          <button type="button" @click="goBack" class="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600">
            취소
          </button>
          <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
            등록
          </button>
        </div>
      </form>
    </div>
    <Footer />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import { createBoard } from '../../api/boardApi'

const router = useRouter()
const form = ref({
  title: '',
  content: '',
  writer: '' // 백엔드 DTO에 writer가 있으므로 추가
})

const handleSubmit = async () => {
  if (!form.value.title.trim() || !form.value.content.trim() || !form.value.writer.trim()) {
    alert("모든 필드를 입력해주세요.");
    return;
  }
  
  try {
    await createBoard(form.value)
    alert("게시글이 성공적으로 등록되었습니다.")
    router.push('/board') // 목록으로 이동
  } catch (err) {
    console.error("게시글 작성 실패:", err)
    alert("게시글 작성에 실패했습니다.")
  }
}

const goBack = () => {
  router.go(-1) // 이전 페이지로 (목록 or 상세)
}
</script>