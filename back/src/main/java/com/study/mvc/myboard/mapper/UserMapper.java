package com.study.mvc.myboard.mapper;

import com.study.mvc.myboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO findById(String id);

    // 회원 정보 삽입
    int insertUser(UserDTO user);

    // 회원 권한 삽입 (회원가입 시 Service가 호출)
    int insertUserAuth(@Param("id") String id, @Param("auth") String auth);

    // 회원 정보 수정 (비밀번호 변경 등)
    // int updateUser(UserDTO user);

    // 회원 탈퇴 (Soft Delete: status를 DELETED로 변경)
    int deleteUser(String id);

    List<UserDTO> findAll();

    // ID로 권한 정보 읽기 (Spring Security에서 사용)
    List<String> readAuth(String id);
}