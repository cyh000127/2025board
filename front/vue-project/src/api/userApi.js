import axios from 'axios'

export const registerUser = async (userData) => {
  const res = await axios.post('/api/user/register', userData)
  return res.data
}

export const loginUser = async (username) => {
  const res = await axios.get(`/api/user/${username}`)
  return res.data
}