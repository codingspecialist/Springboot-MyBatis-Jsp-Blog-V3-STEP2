package shop.mtcoding.blog.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String profile; // 사진의 경로 (/images/a.png 폴더에 사진 추가하기)
    private Timestamp createdAt;
}
