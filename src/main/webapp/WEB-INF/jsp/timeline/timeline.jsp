<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			
			<div class="d-flex justify-content-between">
				<%-- 이미지 업로드를 위한 곳 --%>
				<div class="d-flex">
					<input type="file" id="file" class="d-none" accept=".gif,.jpg,.png,.jpeg">
					<a href="#" id="fileUploadBtn">
						<img src="https://cdn-icons-png.flaticon.com/128/54/54975.png" alt="파일선택" width="35">
					</a>
					
					<%-- 업로드 된 임시 파일명 보이는 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button type="button" id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<%-- 카드 마다 영역을 border로 나눔 --%>
			<c:forEach var="card" items="${cardViewList}">
			<div class="card border rounded mt-3">
				<%-- 글쓴이 아이디, 삭제를 위한 ...버튼 : 이 둘을 한 행에 멀리 떨어뜨려 나타내기 위해 d-flex, between --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">${card.user.name}</span>
					
					<%-- 삭제 모달을 뛰우기 위한 ... 버튼 --%>
					<c:if test="${card.user.id eq userId}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#moreModal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
					</c:if>
				</div>
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagPath}" class="w-100" alt="이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<a href="#" class="like-btn">
						<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18px" height="18px" alt="empty heart">
						좋아요 11개
					</a>
				</div>
				
				<%-- 글(post) --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.user.name}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글(comment) --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				<div class="card-comment-list m-2">
					<%-- 댓글 목록 --%>
					<c:forEach var="comment" items="${card.commentList}">
					<div class="card-comment m-1">
						<span class="font-weight-bold">${comment.user.name} : </span>
						<span>${comment.comment.content}</span>
						
						<%-- 댓글 삭제 --%>
						<c:if test="${comment.user.id eq userId}">
						<a href="#" class="commentDelBtn" data-comment-id="${comment.comment.id}">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
						</c:if>
						
					</div>
					</c:forEach>
				</div>
				
				<%-- 댓글 쓰기 --%>
				<c:if test="${not empty userId}">
				<div class="comment-write d-flex boder-top">
				      <input type = "text" class="form-control border-0" placeholder="댓글달기">
				      <button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
				
				</div>
				</c:if>
			</div>
			</c:forEach>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="moreModal">
  <div class="modal-dialog modal-sm modal-dialog-centered">
    <div class="modal-content">
       	<%-- modal 창 안에 내용 넣기 --%>
       	<div class="text-center py-3 ">
       	<%--d-block: 클릭할 수 있는 영역을 넓히기 위해 --%>
       		<a href="#" class="del-post d-block">삭제하기</a>
       	</div>
       	<div class="text-center py-3 border-top ">
       	    <%--data-dismiss: 모달창 닫힘 --%>
       	    <a href="#" class="d-block" data-dismiss="modal">취소</a>
       	</div>
    </div>
  </div>
</div>












<script>
$(document).ready(function() {
	// 파일업로드 이미지 클릭 => input type="file" 숨어있던 창이 열림
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); // a태그가 위로 올라가는 현상을 방지
		
		$('#file').click();   // input file을 클릭한 것과 같은 효과
	});
	
	// 파일 업로드를 했을 때, 확장자 이름 노출, 파일 확장자 검증
	$('#file').on('change', function(e) {
		let fileName = e.target.files[0].name;   // 20220629_140829.jpg
		
		let arr = fileName.split(".");
		
		// 확장자 검증
		if (arr.length < 2 ||
				(arr[arr.length - 1] != 'gif'
						&& arr[arr.length - 1] != 'jpeg'
							&& arr[arr.length - 1] != 'jpg'
								&& arr[arr.length - 1] != 'png')) {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$(this).val(""); // 파일을 비운다. 
			$('#fileName').text(""); // 파일 이름도 비워줌.
			return;
		}
		
		// 임시파일 명 노출
		$('#fileName').text(fileName);
	});
	
	$('#writeBtn').on('click', function() {
		// validation 
		let content = $('#writeTextArea').val();
		console.log(content);
		if (content.length < 1) {
			alert("글 내용을 입력해주세요");
		}
		
		// 파일이 업로드 된 경우 확장자 체크
		let file = $('#file').val();  // 파일 경로만 가져온다.
		console.log(file);  // C:\fakepath\image.png
		if (file != '') {
			let ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
			if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
				alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
				$('#file').val(''); // 파일을 비운다.
				return;
			}
		}
		
		// 폼태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]는 업로드된 첫번째 파일
		
		// AJAX form 데이터 전송
		$.ajax({
			type: "post"
			, url: "/post/create"
			, data: formData
			, enctype: "multipart/form-data"    // 파일 업로드를 위한 필수 설정
			, processData: false    // 파일 업로드를 위한 필수 설정
			, contentType: false    // 파일 업로드를 위한 필수 설정
			, success: function(data) {
				if (data.result == "success") {
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error: function(e) {
				alert("메모 저장에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	//댓글 게시 버튼 클릭
	$('.comment-btn').on('click', function(e){
		let postId = $(this).data('post-id');
		let content = $(this).siblings("input").val().trim();
		
		
		if(content.length < 1){
			alert("댓글 내용을 입력해주세요");
			return;
		}
		$.ajax({
			type:"post"
			,url:"/comment/create"
			,data:{"postId": postId, "content":content}
		    
		//response
		    , success: function(data){
		    	if(data.result == "success"){
		    		location.reload(true); // 댓글 쓰고 나서 새로고침
		    	}else{
		    		alert(data.errorMessage);
		    	}
		    }
		    ,error: function(e){
		    	alert("통신에 실패했습니다")
		    }
		});
		
		
	});
	
	//... 더보기 버튼 클릭시, 모달에 삭제될 글 번호를 넣어준다.
	$('.more-btn').on('click',function(e){
		e.preventDefault(); //a 태그 기본 동작 중단(위로 올라가는거 중단)
		let postId = $(this).data('post-id'); //get
		
		
		//모달에 삭제될 글 번호를 넣어준다.(모달에 재활용 되기 떄문에)
		$('#moreModal').data('post-id',postId); //set  태그: data-post-id="2"
	});
	//모달창 안에 있는 삭제하기 번튼 클릭
	$("#moreModal .del-post").on('click',function(e){
		e.preventDefault(); //a 태그 기본 동작 중단(위로 올라가는거 중단)
		let postId = $('#moreModal').data('post-id');
		alert(postId);
		
		//서버에 삭제 요청
		$.ajax({
			type:"delete"
			,url:"/post/delete"
			,data:{"postId":postId}
		
		    ,success:function(data){
		    	if(data.result == "success"){
		    		location.reload(true);//새로고침
		    	}else{
		    	alert(data.errorMessage)
		        }
		    }
		    ,error:function(e){
		    	alert("삭제하는데 실패했습니다. 관리자에세 문의 주세요")
		    }
		});
		
	});
	$('.commentDelBtn').on('click', function(e){
		e.preventDefault();
		let commentId = $(this).data('comment-id');
		
		$.ajax({
			type:"delete"
			,url:"/comment/delete"
			,data:{"id":commentId}
		    
		    ,success:function(data){
		    	if(data.result == "success"){
		    		location.reload(true);
		    	}else{
		    		alert(data.errorMessage)
		    	}
		    }
		    ,error:function(){
		    	alert("삭제하는데 실패했습니다. 관리자에세 문의 주세요")
		    }
		});
	});
});
</script>

