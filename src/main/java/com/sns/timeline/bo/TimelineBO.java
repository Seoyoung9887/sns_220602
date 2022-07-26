package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.Comment;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	//자기자신의 DAO는 부를 수 있지만, 남의 DAO는 부르면 안된다.
	//상호참조 하면 안된다.BO<->BO(x)
	//TimelineBO-> PostBO
	//view(jsp) -> Controller -> bo-> dao-> mapper.xml
	@Autowired
	private PostBO postBO;
	@Autowired
	private UserBO userBO;
	@Autowired
	private CommentBO commentBO;
	
	public List<CardView> generateCardViewList(){
		// List<CardView> 
		List<CardView> cardViewList = new ArrayList<>();
		//글 목록을 가져온다
		List<Post> postlist = postBO.getPostList();
		for(Post post : postlist) {
			CardView card = new CardView();
			
			//글정보
			card.setPost(post);
			
			//글쓴 유저 정보
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			
			//1:N 글:댓글 댓글들 정보
			int postId = post.getId(); //글번호
			//List<comment> -> List<CommentView>=> comment 패키지에서
			List<Comment> commentList = commentBO.getCommentListByPostId(postId);
			card.setCommentList(commentList);
			//결과 리스트에 카드 저장
			cardViewList.add(card);
			
		}
		
		return cardViewList;
	}

}
