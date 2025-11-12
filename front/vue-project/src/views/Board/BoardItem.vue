<template>
  <router-link :to="`/board/${item.no}`" class="block">
    <div class="bg-white p-4 shadow rounded-lg hover:shadow-lg transition">
      <h2 class="text-xl font-bold truncate">{{ item.title }}</h2>
      <p class="text-gray-700 mt-2 truncate">{{ item.content }}</p>
      <div class="text-gray-500 text-sm mt-2">
        {{ item.writer || "탈퇴한 회원" }} • {{ formatDate(item.reg_date) }}
      </div>
    </div>
  </router-link>
</template>

<script setup>
import { defineProps } from 'vue'
defineProps({ item: Object })

const formatDate = (date) => {
  if (!date) return ""
  return new Date(date).toLocaleString()
}
</script>