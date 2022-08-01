package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class ListRestController {

	@Autowired
	private LikeBO likeBO;
	
	// 좋아요/해제
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		likeBO.like(postId, userId);
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		return result;
	}
}