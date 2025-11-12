// src/api/boardApi.js

import axios from 'axios'

// 전체 게시글 가져오기
export const getBoardList = async () => {
  const res = await axios.get('/api/board/list')
  return res.data
}

// 게시글 상세보기
export const getBoard = async (no) => {
  const res = await axios.get(`/api/board/${no}`)
  return res.data
}

// 게시글 작성
export const createBoard = async (data) => {
  // data: { title, content, writer }
  const res = await axios.post('/api/board/create', data)
  return res.data
}

// 게시글 수정 (추가)
export const updateBoard = async (data) => {
  // data: { no, title, content }
  const res = await axios.put('/api/board/update', data)
  return res.data
}

// 게시글 삭제 (추가)
export const deleteBoard = async (no) => {
  const res = await axios.delete(`/api/board/delete/${no}`)
  return res.data
}