package shop.mtcoding.blog.handler.ex;

public class CustomException extends RuntimeException {
    public CustomException(String msg) {
        super(msg);
    }
}
