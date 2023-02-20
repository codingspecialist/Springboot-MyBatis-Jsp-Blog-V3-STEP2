<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<input type="hidden" id="boardId" value="${boardDto.id}" />

<div class="container my-3">
    <c:if test="${boardDto.userId == principal.id}">
        <div class="mb-3">
            <a href="/board/${boardDto.id}/updateForm" class="btn btn-warning">수정</a>
            <button onclick="deleteById(${boardDto.id})" class="btn btn-danger">삭제</button>
        </div>
    </c:if>


    <div class="mb-2 d-flex justify-content-end">
        글 번호 :
        <span id="id" class="me-3">
            <i>${boardDto.id}</i>
        </span>
        작성자 :
        <span class="me-3">
            <i>${boardDto.username} </i>
        </span>
    </div>

    <div>
        <h1><b>${boardDto.title}</b></h1>
    </div>
    <hr/>
    <div>
        <div>${boardDto.content}</div>
    </div>
    <hr/>

    <c:choose>
        <c:when test="${loveDto == null}">
            <i id="heart" class="fa-regular fa-heart fa-lg" value="${loveDto.id}" onclick="loveOrCancel()"></i>
        </c:when>
        <c:otherwise>
            <i id="heart" class="fa-solid fa-heart fa-lg" value="${loveDto.id}" onclick="loveOrCancel()"></i>
        </c:otherwise>
    </c:choose>

    <script>


        function loveOrCancel(){
            let boardId = $("#boardId").val();
            let id = $("#heart").attr("value");
            console.log(id);

            if (id == "") {
                // 좋아요 통신 요청 (POST)
                let data = {
                    boardId: boardId
                }

                $.ajax({
                    type: "post",
                    url: "/love",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done((res) => { // 20X 일때
                    //alert(res.msg);
                    $("#heart").attr("value", res.data);
                    $("#heart").addClass("fa-solid");
                    $("#heart").removeClass("fa-regular");
                }).fail((err) => { // 40X, 50X 일때
                    alert(err.responseJSON.msg);
                });
            } else {
                // 좋아요 취소 통신 요청 (DELETE)
                $.ajax({
                    type: "delete",
                    url: "/love/"+id,
                    dataType: "json"
                }).done((res) => { // 20X 일때
                    //alert(res.msg);
                    $("#heart").attr("value", "");
                    $("#heart").removeClass("fa-solid");
                    $("#heart").addClass("fa-regular");
                }).fail((err) => { // 40X, 50X 일때
                    alert(err.responseJSON.msg);
                });
            }
        }


    </script>

    <div class="card mt-3">
        <form action="/reply" method="post">
            <input type="hidden" name="boardId" value="${boardDto.id}">
            <div class="card-body">
                <textarea name="comment" id="reply-comment" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="submit" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br/>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">
            <c:forEach items="${replyDtos}" var="reply">
                <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                    <div>${reply.comment}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : ${reply.username} &nbsp;</div>
                        <c:if test="${principal.id == reply.userId}">
                            <button onClick="deleteByReplyId(${reply.id})" class="badge bg-secondary">삭제</button>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script>
    function deleteByReplyId(id){
        $.ajax({
            type: "delete",
            url: "/reply/" + id,
            dataType: "json"
        }).done((res) => { // 20X 일때
            alert(res.msg);
            //location.reload();
            $("#reply-"+id).remove();
        }).fail((err) => { // 40X, 50X 일때
            alert(err.responseJSON.msg);
        });
        // $("#reply-"+id).remove();
        // location.reload();
    }

    function deleteById(id) {
        $.ajax({
            type: "delete",
            url: "/board/" + id,
            dataType: "json"
        }).done((res) => { // 20X 일때
            alert(res.msg);
            location.href = "/";
        }).fail((err) => { // 40X, 50X 일때
            alert(err.responseJSON.msg);
        });
    }
</script>

<%@ include file="../layout/footer.jsp" %>