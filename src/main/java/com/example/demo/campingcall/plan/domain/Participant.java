package com.example.demo.campingcall.plan.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 방에 소속된 인원을 관리하는 DTO
@Builder(toBuilder=true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "participant")
@Entity
public class Participant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId") 
	private int userId;
	
	@Column(name="roomId") 
	private int roomId;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
}
