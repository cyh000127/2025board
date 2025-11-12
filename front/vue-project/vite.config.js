import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import nightwatchPlugin from 'vite-plugin-nightwatch'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx(),
    nightwatchPlugin({
      renderPage: './nightwatch/index.html'
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },

  // --- 이 부분이 추가되었습니다 ---
  server: {
    proxy: {
      // '/api'로 시작하는 모든 요청을
      '/api': {
        // 'http://localhost:8080' (Spring Boot 서버)로 전달합니다.
        target: 'http://localhost:8080',
        changeOrigin: true, // CORS 헤더 문제 해결
      }
    }
  }
  // ------------------------------
})