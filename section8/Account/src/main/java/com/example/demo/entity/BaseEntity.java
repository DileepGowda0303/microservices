package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class BaseEntity {
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@Column(updatable = false)
	private String createdBy;
	@Column(insertable = false)
	private LocalDateTime updatedAt;
	@Column(insertable = false)
	private String updatedBy;
}
