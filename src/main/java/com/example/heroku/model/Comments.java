package com.example.heroku.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Comments {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_id")
	private Long id;

	@NotNull
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Comments{" +
				"id=" + id +
				", comment='" + comment + '\'' +
				'}';
	}
}
