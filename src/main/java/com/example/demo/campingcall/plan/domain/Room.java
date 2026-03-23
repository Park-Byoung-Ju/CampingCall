package com.example.demo.campingcall.plan.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

// 일정을 만들기 위한 방
@Builder(toBuilder=true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planRoom")
@Entity
public class Room {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="title") 
	private String title;
	
	@Column(name="master") 
	private int master;
	
	@Column(name="startDate") 
	private LocalDate startDate;
	
	@Column(name="endDate") 
	private LocalDate endDate;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
