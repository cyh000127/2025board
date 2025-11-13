package com.study.mvc.myboard.service;

import com.study.mvc.myboard.dto.UserDTO;

import java.util.List;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface UserService {

    /**
     * 회원가입을 처리합니다.
     * (비밀번호 암호화 및 기본 권한 부여 포함)
     * @param user (id, password, email, name)
     * @return 성공 여부
     */
    boolean registerUser(UserDTO user);

    /**
     * 특정 회원 정보를 조회합니다. (권한 정보 포함)
     * @param id 회원 ID
     * @return UserDTO (없으면 null)
     */
    UserDTO getUser(String id);

    /**
     * 회원 탈퇴를 처리합니다. (Soft Delete)
     * @param id 회원 ID
     * @return 성공 여부
     */
    boolean deleteUser(String id);

    /**
     * 전체 회원 목록을 조회합니다. (관리자용)
     * @return 회원 DTO 리스트
     */
    List<UserDTO> getUserList();
}