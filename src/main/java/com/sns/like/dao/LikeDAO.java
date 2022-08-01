package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.model.Like;

@Repository
public interface LikeDAO {

	public boolean existLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void deleteLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public Like selectLikeByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int selectLikeCountByPostId(int postId);
	public void deleteLikeByPostId(int postId);
}