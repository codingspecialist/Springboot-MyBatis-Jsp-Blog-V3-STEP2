# Blog 프로젝트

## 기술스택
- JDK 11
- Springboot 2.7.8
- MyBatis
- 테스트 h2 디비
- 프로덕션 MySQL 디비
- JSP

## 모델링
### 1단계
- User
- Board
### 2단계
- Reply
### 3단계
- Love

## 기능정리
### 1단계 (완료)
- 회원가입, 로그인, 글쓰기, 글목록보기(섬네일제외), 글상세보기, 글삭제, 글수정, 섬네일보기
### 2단계 (시작)
- 댓글쓰기, 댓글목록보기, 댓글삭제, 프로필사진추가및변경하기, 프로필사진보기
```sql
select bt.id, bt.title, bt.content,ut.username board_writer_username, rt.id reply_id, rt.comment reply_comment, rt.user_id reply_writer_id, cut.username reply_writer_username
from board_tb bt
inner join user_tb ut
on bt.user_id = ut.id
left outer join reply_tb rt
on bt.id = rt.board_id
inner join user_tb cut
on rt.user_id = cut.id
where bt.id = 1;

select bt.id, bt.title, bt.content, bt.user_id, ut.username, rt.id, rt.comment
from board_tb bt
inner join user_tb ut
on bt.user_id = ut.id
left outer join reply_tb rt
on bt.id = rt.board_id
where bt.id = 1;

select rt.id, rt.comment, rt.user_id, rt.board_id, ut.username
from reply_tb rt
inner join user_tb ut
on rt.user_id = ut.id
where rt.board_id = 1;
```
### 3단계
- 좋아요하기, 좋아요취소, 좋아요보기
### 4단계
- 아이디중복체크, 회원수정하기, 페이징
### 5단계
- 검색

### 기술블로그
- 회원가입 (주소)
- 로그인 (주소)