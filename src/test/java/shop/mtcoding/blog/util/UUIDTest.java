package shop.mtcoding.blog.util;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {

    @Test
    public void uuid_test() throws Exception{
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }
}
