package shop.mtcoding.blog.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyResp {

    @Getter
    @Setter
    public static class ReplyDetailRespDto {
        private Integer id;
        private String comment;
        private Integer userId;
        private Integer boardId;
        private String username;
    }
}
