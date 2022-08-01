package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	//  -> postBO    -> userBO
	// timelineBO -> commentBO -> userBO
	@Autowired
	private UserBO userBO;
	
	public void createComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
	public List<Comment> getCommentListByPostId(int postId){
		return commentDAO.selectCommentListByPostId(postId);
		
	}
	//List<comment> -> List<CommentView>=> comment 패키지에서
	public List<CommentView> generateCommentViewListByPostId(int postId){
		List<CommentView> resultList = new ArrayList<>();
		List<Comment> commentList = getCommentListByPostId(postId); // 5
		
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			// 댓글 내용
			commentView.setComment(comment);
			
			// 댓글 쓴이
			int userId = comment.getUserid();
			User user = userBO.getUserById(userId);
			commentView.setUser(user);
			
			// 결과 리스트에 넣는다
			resultList.add(commentView);
		}
		
		return resultList;
		
		
	}
	public void deleteComment(int id) {
		commentDAO.deleteComment(id);
	}
	public void deleteCommentByPostId(int postId) {
		commentDAO.deleteCommentByPostId(postId);
	}

}
