package shop.mtcoding.blog.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserRepository {
        public List<User> findAll();

        public User findById(int id);

        public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

        public User findByUsername(@Param("username") String username);

        public int insert(@Param("username") String username, @Param("password") String password,
                        @Param("email") String email);

        public int updateById(@Param("id") int id,
                              @Param("username") String username,
                              @Param("password") String password,
                              @Param("email") String email,
                              @Param("profile") String profile,
                              @Param("createdAt") Timestamp createdAt);

        public int deleteById(int id);
}