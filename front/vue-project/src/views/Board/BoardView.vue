<template>
  <div class="min-h-screen bg-gray-100">
    <Header />
    <div class="max-w-4xl mx-auto p-4">
      <div class="flex justify-between items-center mb-6">
        <h1 class="text-3xl font-bold">게시판</h1>
        <router-link 
          to="/board/write" 
          class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition"
        >
          글쓰기
        </router-link>
      </div>
      <div class="grid gap-4">
        <BoardItem v-for="item in boardList" :key="item.no" :item="item" />
      </div>
    </div>
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Header from '../../components/Header.vue' 
import Footer from '../../components/Footer.vue' 
import BoardItem from './BoardItem.vue' // 경로는 맞는지 확인
import { getBoardList } from '../../api/boardApi' // 경로는 맞는지 확인

const boardList = ref([])

onMounted(async () => {
  try {
    boardList.value = await getBoardList()
  } catch (err) {
    console.error("게시판 불러오기 실패:", err)
  }
})
</script>