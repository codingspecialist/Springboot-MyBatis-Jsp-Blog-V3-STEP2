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
}
