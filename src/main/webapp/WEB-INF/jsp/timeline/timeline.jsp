<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<button type="button" class="btn btn-info">게시</button>
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
});
</script>












