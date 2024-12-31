package com.example.demo.campingcall.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.comment.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{

	public List<Comment> findByContentIdAndCategory(@Param("contentId") int id
													,@Param("category") int category);
	
	public void deleteByContentIdAndCategory(@Param("contentId") int id
											,@Param("category") int category);
}
