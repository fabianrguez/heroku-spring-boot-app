package com.example.heroku.controller;

import com.example.heroku.model.Comment;
import com.example.heroku.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RestController
@RequestMapping("/comment")
public class CommentController  {

	private static final Logger log = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentRepository commentRepository;

	@RequestMapping(method = RequestMethod.POST, path = "/add")
	public ResponseEntity addComment(@RequestBody Comment comment) {
		if (comment == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		commentRepository.save(comment);
		return new ResponseEntity(HttpStatus.OK);
	}

	@MessageMapping("/comment/add")
	@SendTo("/topic/comments")
	public Comment addWebSocketComment(Comment comment) {
		log.info("Comment: " + comment);
		commentRepository.save(comment);
		return comment;
	}

	@MessageMapping("/comment/delete")
	@SendTo("/topic/comments_deleted")
	public Comment deleteWebsocketComment(Long commentId) {
		Comment comment = commentRepository.findOne(commentId);
		commentRepository.delete(commentId);
		return comment;
	}

	@SubscribeMapping("/comments")
	public Iterable<Comment> initComment() {
		log.info("Cliente subscribed");
		return commentRepository.findAll();
	}
}
