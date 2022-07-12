package com.example.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.task.entity.Repo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GithubServiceTest {
	
	@TestConfiguration
	public class GithubServiceTestContextConfiguration {
		
		@Bean
		public GithubService githubService() {
			return new GithubService() {
				// implement methods
			};
		}
	}
	
	@Autowired
	GithubService githubService;
	
	// mock beans for other service (if any)
	// test cases
	
	@Before
	public void setUp() {
		
	}
		
	@Test
	public void whenValidName_ThenRepoNameShouldBeFound() throws Exception {
		String owner = "nathchin";
		String repoName = "TestRepo";
		Repo repo = githubService.getGithubRepoInfo(owner, repoName);
		
		assertThat(repo.getFullName()).isEqualTo(owner+"/"+repoName);
	}
	
	
}
