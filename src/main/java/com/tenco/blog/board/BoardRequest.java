package com.tenco.blog.board;

import com.tenco.blog.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 클라이언트에게 넘어온 데이터를
 * Object로 변화해서 전달하는 DTO 역할을 담당한다
 */
public class BoardRequest {

    // 게시글 저장 DTO
    @Data
    public static class SaveDTO {
        @NotEmpty(message = "제목은 필수입니다")
        @Size(min = 1, max = 100, message = "제목은 1~100자 이내로 작성해주세요")
        private String title;
        @NotEmpty(message = "내용은 필수입니다")
        @Size(min = 10, max = 1000, message = "내용은 10~1000자 이내로 작성해주세요")
        private String content;

        // (User) <-- toEntity() 호출할 때 세션에서 가져와서 넣어 주면 됨
        public Board toEntity(User user) {
            return Board.builder()
                    .title(this.title)
                    .user(user)
                    .content(this.content)
                    .build();
        }
    }

    // 게시글 수정용 DTO 설계
    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "제목은 필수입니다")
        @Size(min = 1, max = 100, message = "제목은 1~100자 이내로 작성해주세요")
        private String title;
        @NotEmpty(message = "내용은 필수입니다")
        @Size(min = 10, max = 1000, message = "내용은 10~1000자 이내로 작성해주세요")
        private String content;

        // toEntity 메서드 안 만들 예정 (더티 체킹 활용)
        // em.find() <--- Board <-- 영속화 <-- 상태값을 변경하면 자동 갱신
    }
}
