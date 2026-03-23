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

// 방으로 초대하는 인원을 관리하기 위한 DTO
@Builder(toBuilder=true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invite")
@Entity
public class Invite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="roomId")
	private int roomId;
	
	@Column(name="userId")
	private int userId;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
}
