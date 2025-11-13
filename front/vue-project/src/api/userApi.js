import axios from "axios";

export const registerUser = async (userData) => {
  const res = await axios.post("/api/user/register", userData);
  return res.data;
};

/**
 * 로그인 API
 * @param {string} id - 사용자 ID
 * @param {string} password - 사용자 평문 비밀번호
 */
export const loginUser = async (id, password) => {
  // Spring Security의 formLogin은 'application/x-www-form-urlencoded' 형식을 기대합니다.
  // URLSearchParams를 사용하여 해당 형식으로 데이터를 구성합니다.
  const params = new URLSearchParams();
  params.append("id", id); // SecurityConfig의 .usernameParameter("id")와 일치
  params.append("password", password); // SecurityConfig의 .passwordParameter("password")와 일치

  // SecurityConfig의 .loginProcessingUrl("/api/user/login")으로 요청
  const res = await axios.post("/api/user/login", params, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
  });

  // 성공 시  200 OK와 JSON 응답이 옴,
  return res.data;
};

/**
 * 로그아웃 API
 * Spring Security가 이 요청을 가로채 세션을 무효화합니다.
 */
export const logoutUser = async () => {
  // SecurityConfig의 .logoutUrl("/api/user/logout")로 POST 요청
  const res = await axios.post("/api/user/logout");
  return res.data;
};
