package shop.mtcoding.blog.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardResp {
    @Setter
    @Getter
    public static class BoardMainRespDto {
        private int id;
        private String title;
        private String username;
    }

    @Setter
    @Getter
    public static class BoardDetailRespDto {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;
    }
}
