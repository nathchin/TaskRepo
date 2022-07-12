package com.example.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.task.entity.GithubRepo;
import com.example.task.entity.Repo;

@Service
public class GithubService {
	Logger log = LoggerFactory.getLogger(GithubService.class);
	
	public Repo getGithubRepoInfo(String owner, String repositoryname) {
		if (owner.length()<1 || repositoryname.length()<1) {
			return null;
		}
		
		Repo repo = new Repo();
		String uri = "https://api.github.com/repos/"+owner+"/"+repositoryname;
	    RestTemplate restTemplate = new RestTemplate();
	    GithubRepo githubRepo;
	    try {
	    	githubRepo = restTemplate.getForObject(uri, GithubRepo.class);
	    	repo.setFullName(githubRepo.getFull_name());
		    repo.setDescription(githubRepo.getDescription());
		    repo.setCloneUrl(githubRepo.getClone_url());
		    repo.setStars(githubRepo.getStargazers_count());
		    repo.setCreatedAt(githubRepo.getCreatedAt());
	    } catch (Exception ex) {
	    	log.debug(ex.getMessage());
	    }
	    
		return repo;
	}
}
