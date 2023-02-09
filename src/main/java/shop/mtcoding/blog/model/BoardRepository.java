package shop.mtcoding.blog.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.mtcoding.blog.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.blog.dto.board.BoardResp.BoardMainRespDto;

import java.util.List;

@Mapper
public interface BoardRepository {

        public BoardDetailRespDto findByIdWithUser(int id);

        public List<BoardMainRespDto> findAllWithUser();

        public List<Board> findAll();

        public Board findById(int id);

        public int insert(@Param("title") String title, @Param("content") String content, @Param("thumbnail") String thumbnail,
                        @Param("userId") int userId);

        public int updateById(@Param("id") int id, @Param("title") String title,
                        @Param("content") String content, @Param("thumbnail") String thumbnail);

        public int deleteById(int id);
}