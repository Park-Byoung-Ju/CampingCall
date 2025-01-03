package com.example.demo.campingcall.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.campingcall.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

	// 오류나는데 작동하는데는 문제가 없음
	@Query(value="SELECT * FROM `board` ORDER BY `id` DESC LIMIT :start, :end", nativeQuery=true)
	public List<Board> boardList(@Param("start") int start
								,@Param("end") int end);
	
	@Query(value="SELECT COUNT(*) FROM `board`", nativeQuery=true)
	public int listByAllCount();
	
	public List<Board> findTop5ByTitleContains(@Param("search") String search);
	
	public int countByTitleContains(@Param("search") String search);
}
