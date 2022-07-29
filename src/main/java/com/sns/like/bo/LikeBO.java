package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public void like(int postId, int userId) {
		if (likeDAO.existLikeByPostIdAndUserId(postId, userId)) {
			// 좋아요가 있으면
			likeDAO.deleteLikeByPostIdAndUserId(postId, userId);
		} else {
			// 좋아요가 없으면
			likeDAO.insertLike(postId, userId);
		}
	}
}
