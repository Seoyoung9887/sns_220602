package com.sns.timeline;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
//	@Autowired
//	private PostBO postBO;
	@Autowired
	private TimelineBO timelineBO;
	
	@RequestMapping("/timeline_view")
	public String timelineView(Model model, HttpSession session) {
		List<CardView> cardViewList = timelineBO.generateCardViewList((Integer)session.getAttribute("userId"));
		model.addAttribute("cardViewList", cardViewList);
		
//		List<Post> postList = postBO.getPostList();
		
//		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}


