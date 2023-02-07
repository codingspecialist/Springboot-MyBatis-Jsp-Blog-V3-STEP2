package shop.mtcoding.blog.handler.ex;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomApiException extends RuntimeException {

    private HttpStatus status;

    public CustomApiException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public CustomApiException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }
}
