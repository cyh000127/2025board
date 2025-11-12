<template>
  <div class="min-h-screen bg-gray-100">
    <Header />
    <div class="max-w-3xl mx-auto p-6 bg-white shadow rounded-lg mt-8">
      <div v-if="board">
        <h1 class="text-3xl font-bold mb-3">{{ board.title }}</h1>
        
        <div class="flex justify-between items-center text-gray-500 text-sm mb-6 pb-4 border-b">
          <span>작성자: {{ board.writer || "탈퇴한 회원" }}</span>
          <span>
            작성일: {{ formatDate(board.reg_date) }}
            <template v-if="board.update_date">
              (수정: {{ formatDate(board.update_date) }})
            </template>
          </span>
        </div>

        <div class="text-gray-800 text-lg min-h-[200px] whitespace-pre-wrap">
          {{ board.content }}
        </div>

        <div class="flex justify-between items-center mt-8 pt-4 border-t">
          <button @click="goToList" class="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600">
            목록
          </button>
          <div class="space-x-2">
            <button @click="goToEdit" class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
              수정
            </button>
            <button @click="handleDelete" class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">
              삭제
            </button>
          </div>
        </div>
      </div>
      <div v-else-if="error" class="text-center text-red-500">
        게시글을 불러오는 중 오류가 발생했습니다.
      </div>
      <div v-else class="text-center text-gray-500">
        게시글을 불러오는 중입니다...
      </div>
    </div>
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Header from '../../components/Header.vue'
import Footer from '../../components/Footer.vue'
import { getBoard, deleteBoard } from '../../api/boardApi'

// 라우터에서 props로 :no 값을 받음
const props = defineProps({
  no: [String, Number]
})

const router = useRouter()
const board = ref(null)
const error = ref(null)

onMounted(async () => {
  try {
    board.value = await getBoard(props.no)
  } catch (err) {
    console.error("게시글 상세 조회 실패:", err)
    error.value = err
  }
})

const handleDelete = async () => {
  if (confirm("정말로 이 게시글을 삭제하시겠습니까?")) {
    try {
      await deleteBoard(props.no)
      alert("게시글이 삭제되었습니다.")
      router.push('/board') // 목록으로 이동
    } catch (err) {
      console.error("게시글 삭제 실패:", err)
      alert("게시글 삭제에 실패했습니다.")
    }
  }
}

const goToList = () => {
  router.push('/board')
}

const goToEdit = () => {
  router.push(`/board/edit/${props.no}`)
}

const formatDate = (date) => {
  if (!date) return ""
  return new Date(date).toLocaleString()
}
</script>