package shop.mtcoding.blog.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyRepository {
        public List<Reply> findAll();

        public Reply findById(int id);

        public int insert(@Param("comment") String comment, @Param("userId") int userId, @Param("boardId") int boardId);

        public int updateById(@Param("id") int id, @Param("comment") String comment);

        public int deleteById(int id);
}