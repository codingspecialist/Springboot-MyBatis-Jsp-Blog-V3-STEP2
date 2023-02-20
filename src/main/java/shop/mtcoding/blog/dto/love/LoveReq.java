package shop.mtcoding.blog.dto.love;

import lombok.Getter;
import lombok.Setter;

public class LoveReq {
    @Setter
    @Getter
    public static class LoveSaveReqDto {
        private Integer boardId;
    }
}
