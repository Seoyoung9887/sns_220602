package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	public void createComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
	public List<Comment> getCommentListByPostId(int postId){
		return commentDAO.selectCommentListByPostId(postId);
		
	}
	//List<comment> -> List<CommentView>=> comment 패키지에서
	public List<CommentView> generateCommentViewListByPostId(int postId){
		List<Comment> commentList = getCommentListByPostId(postId);
		
		
	}

}
