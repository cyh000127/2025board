<template>
  <div class="min-h-screen bg-gray-100">
    <Header />
    <div class="max-w-3xl mx-auto p-6 bg-white shadow rounded-lg mt-8">
      <h1 class="text-3xl font-bold mb-6 text-center">게시글 수정</h1>
      
      <form @submit.prevent="handleSubmit" v-if="form">
        <div class="mb-4">
          <label for="writer" class="block text-sm font-medium text-gray-700">작성자</label>
          <input 
            type="text" 
            id="writer" 
            v-model="form.writer"
            disabled
            class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm bg-gray-100"
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
            수정 완료
          </button>
        </div>
      </form>
      <div v-else class="text-center text-gray-500">
        게시글 정보를 불러오는 중입니다...
      </div>
    </div>
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import { getBoard, updateBoard } from '../../api/boardApi'

const props = defineProps({
  no: [String, Number]
})

const router = useRouter()
const form = ref(null)

onMounted(async () => {
  try {
    const boardData = await getBoard(props.no)
    form.value = {
      title: boardData.title,
      content: boardData.content,
      writer: boardData.writer,
      no: boardData.no // updateBoard API에 no가 필요
    }
  } catch (err) {
    console.error("게시글 불러오기 실패:", err)
    alert("게시글 정보를 불러오는데 실패했습니다.")
    router.back()
  }
})

const handleSubmit = async () => {
  if (!form.value.title.trim() || !form.value.content.trim()) {
    alert("제목과 내용은 비워둘 수 없습니다.");
    return;
  }
  
  try {
    await updateBoard(form.value)
    alert("게시글이 성공적으로 수정되었습니다.")
    router.push(`/board/${props.no}`) // 수정 후 상세 페이지로 이동
  } catch (err) {
    console.error("게시글 수정 실패:", err)
    alert("게시글 수정에 실패했습니다.")
  }
}

const goBack = () => {
  router.go(-1) // 이전 페이지로 (상세 페이지)
}
</script>