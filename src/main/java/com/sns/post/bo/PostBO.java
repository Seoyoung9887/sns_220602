package com.sns.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private CommentBO commentBO;
	@Autowired
	private LikeBO likeBO;
	
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		postDAO.insertPost(userId, content, imagePath);
		
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	public Post getPostBypostIdAndUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdUserId(postId, userId);
	}
	
	public void deletePostByPostIdUserId(int postId, int userId){
		// 삭제할 글을 셀렉트 해온다.
		
		//이미지가 있으면 이미지 삭제
		Post post = getPostBypostIdAndUserId(postId,userId);
		if (post == null) {
			logger.error("[delete post] 삭제할 게시물이 없습니다. postId:{}", postId);
			return;
		}
		if(post.getImagPath() != null) {
			//이미지 삭제
			try {
				fileManager.deleteFile(post.getImagPath());
			} catch (IOException e) {
				logger.error("[delete post] image 삭제 실패. postId{} path{}",postId, post.getImagPath() );
			}
		}
		//글삭제
		postDAO.deletePostByPostIdUserId(postId, userId);
		//댓글들 삭제 byPostId
		commentBO.deleteCommentByPostId(postId);
		
		//좋아요 삭제 byPostId
		likeBO.deleteLikeByPostId(postId);
	}

	
}