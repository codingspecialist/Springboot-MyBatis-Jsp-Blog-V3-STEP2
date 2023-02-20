package shop.mtcoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.handler.ex.CustomApiException;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.BoardRepository;
import shop.mtcoding.blog.model.Love;
import shop.mtcoding.blog.model.LoveRepository;

@Service
public class LoveService {

    @Autowired
    private LoveRepository loveRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 좋아요취소(int id, int userId){
        Love lovePS = loveRepository.findById(id);
        if(lovePS == null){
            throw new CustomApiException("좋아요 내역이 존재하지 않습니다");
        }
        if(lovePS.getUserId() != userId){
            throw new CustomApiException("좋아요 취소 권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        int result = loveRepository.deleteById(id); // 핵심로직
        if(result != 1){
            throw new CustomApiException("서버 에러", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public int 좋아요(int boardId, int userId){
        // 게시글 존재 여부 체크
        Board boardOP =  boardRepository.findById(boardId);
        if(boardOP == null){
            throw new CustomApiException("게시글이 존재하지 않습니다");
        }

        Love love = new Love();
        love.setBoardId(boardId);
        love.setUserId(userId);
        loveRepository.insert(love);
        return love.getId();
    }

}
