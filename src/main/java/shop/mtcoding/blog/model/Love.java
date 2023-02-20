package shop.mtcoding.blog.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class Love {
    private Integer id;
    private Integer boardId;
    private Integer userId;
    private Timestamp createdAt;
}
