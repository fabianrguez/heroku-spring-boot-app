package com.example.heroku.repository;

import com.example.heroku.model.Comments;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comments, Long> {
}
