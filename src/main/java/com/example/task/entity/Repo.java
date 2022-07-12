package com.example.task.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.http.ResponseEntity;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
@Entity
public class Repo {
	@Id
	private Long id;
	private String fullName;
	private String description;
	private String cloneUrl;
	private Long stars;
	private Date createdAt;
	public Mono<ResponseEntity> map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
