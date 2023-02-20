package shop.mtcoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.dto.ResponseDto;
import shop.mtcoding.blog.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blog.dto.board.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.blog.handler.ex.CustomApiException;
import shop.mtcoding.blog.handler.ex.CustomException;
import shop.mtcoding.blog.model.*;
import shop.mtcoding.blog.service.BoardService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private LoveRepository loveRepository;

    @PutMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable int id, @RequestBody BoardUpdateReqDto boardUpdateReqDto, HttpServletResponse response){
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (boardUpdateReqDto.getTitle() == null || boardUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("title을 작성해주세요");
        }
        if (boardUpdateReqDto.getContent() == null || boardUpdateReqDto.getContent().isEmpty()) {
            throw new CustomApiException("content를 작성해주세요");
        }
        boardService.게시글수정(id, boardUpdateReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글수정성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable int id){
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        boardService.게시글삭제(id, principal.getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "삭제성공", null), HttpStatus.OK);
    }

    // dev 개발
    @PostMapping("/board")
    public @ResponseBody ResponseEntity<?> save(@RequestBody BoardSaveReqDto BoardSaveReqDto) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (BoardSaveReqDto.getTitle() == null || BoardSaveReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("title을 작성해주세요");
        }
        if (BoardSaveReqDto.getContent() == null || BoardSaveReqDto.getContent().isEmpty()) {
            throw new CustomApiException("content를 작성해주세요");
        }
        if (BoardSaveReqDto.getTitle().length() > 100) {
            throw new CustomApiException("title의 길이가 100자 이하여야 합니다");
        }

        boardService.글쓰기(BoardSaveReqDto, principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "글쓰기성공", null), HttpStatus.CREATED);
    }

    @GetMapping({ "/", "/board" })
    public String main(Model model) {
        model.addAttribute("dtos", boardRepository.findAllWithUser());
        return "board/main";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal != null) {
            model.addAttribute("loveDto", loveRepository.findByBoardIdAndUserId(id, principal.getId()));
        }
        model.addAttribute("boardDto", boardRepository.findByIdWithUser(id));
        model.addAttribute("replyDtos", replyRepository.findByBoardIdWithUser(id));
        return "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        Board boardPS = boardRepository.findById(id);
        if(boardPS == null){
            throw new CustomException("없는 게시글을 수정할 수 없습니다");
        }
        if(boardPS.getUserId() != principal.getId()){
            throw new CustomException("게시글을 수정할 권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        model.addAttribute("board",boardPS);
        return "board/updateForm";
    }
}
