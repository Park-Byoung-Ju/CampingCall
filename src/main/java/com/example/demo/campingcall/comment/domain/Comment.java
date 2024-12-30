package com.example.demo.campingcall.comment.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.demo.campingcall.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="comment")
@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId") 
	private int userId;
	
	@Column(name="contentId")
	private int contentId;
	
	@Column(name="category")
	private int category;
	
	@Column(name="contents")
	private String contents;
	
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
	@Transient
	private User user;
}
