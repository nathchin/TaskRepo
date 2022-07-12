package com.example.task.entity;

import java.util.Date;

import lombok.Data;

@Data
public class GithubRepo {
	private Long id;
	private String name;
	private String full_name;
	private String description;
	private String clone_url;
	private Long stargazers_count;
	private Date createdAt;
	
}
