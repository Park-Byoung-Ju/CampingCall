package com.example.demo.campingcall.camp.domain;

import java.time.LocalDate;
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

@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="camping")
@Entity
public class CampBooking {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="contentId") 
    private int contentId;
	
	@Column(name="userId") 
    private int userId;
	
	@Column(name="bookingNumber") 
    private int bookingNumber;
	
	@Column(name="price") 
    private int price;
	
	@Column(name="date") 
    private LocalDate date;
	
	@Column(name="createdAt") 
	@CreationTimestamp
    private LocalDateTime createdAt;
}
