package shop.mtcoding.blog.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class Board {
    private int id;
    private String title;
    private String content;
    private String thumbnail;
    private int userId;
    private Timestamp createdAt;
}
