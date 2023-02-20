package shop.mtcoding.blog.model;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoveRepository {
        public Love findByBoardIdAndUserId(int boardId, int userId);

        public List<Love> findAll();

        public Love findById(int id);

        public int insert(Love love);

        public int updateById(Love love);

        public int deleteById(int id);
}