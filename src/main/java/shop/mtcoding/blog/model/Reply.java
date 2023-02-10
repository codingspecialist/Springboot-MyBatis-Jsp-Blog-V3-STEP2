package shop.mtcoding.blog.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class Reply {
    private int id;
    private String comment;
    private int userId;
    private int boardId;
    private Timestamp createdAt;
}
