package com.sns.comment.model;

import com.sns.user.model.User;

public class CommentView {
	private User user; //댓글쓴이 정보
	private Comment comment; //댓글 정보
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
