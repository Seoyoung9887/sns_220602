package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class CardView {
	private User user; //글을 쓴 사람  ${card.user.name}
	private Post post; //글내용
	private List<CommentView> commentList; //글에 대한 댓글 목록 ${card.commentList.comment.content}
	private boolean filledLike;//로그인한 내가 좋아요를 누른 여부
    private int likeCount; //좋아요 개수
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<CommentView> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}
	public boolean isFilledLike() {
		return filledLike;
	}
	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	
     
	
	//getters, setters
}
