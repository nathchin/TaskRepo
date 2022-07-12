package com.example.task.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.task.entity.Repo;
import com.example.task.service.GithubService;

import io.swagger.annotations.Api;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/repositories")
@Api(value="Get repository information")
public class RepoController {
	
	@Autowired
	GithubService githubService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test(){
		return "ttest";
	}
	
	// blocking
	@RequestMapping(value="/blocking/{owner}/{repositoryname}", method = RequestMethod.GET)
	public ResponseEntity<Repo> getRepositoryInfoBlocking(@PathVariable("owner") String owner, @PathVariable("repositoryname") String repositoryname) {
		return ResponseEntity.ok(githubService.getGithubRepoInfo(owner, repositoryname));
	}
	
	// non blocking
	@RequestMapping(value="/nonblocking/{owner}/{repositoryname}", method = RequestMethod.GET)
	public Mono<ResponseEntity<Repo>> getRepositoryInfoNonBlocking(@PathVariable("owner") String owner, @PathVariable("repositoryname") String repositoryname) {
		Repo body = githubService.getGithubRepoInfo(owner, repositoryname);
		return Mono.just(ResponseEntity.status(HttpStatus.OK).body(body))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
	}
	
//	@RequestMapping(value="/repositories/{owner}/{repositoryname}", method = RequestMethod.GET)
//	public Mono<Repo> getRepositoryInfoNonBlocking(@PathVariable("owner") String owner, @PathVariable("repositoryname") String repositoryname) {
//		return Mono.defer(() -> Mono.just(githubService.getGithubRepoInfo(owner, repositoryname)).delaySubscription(Duration.ofMillis(500)));
//	}
	
//	@RequestMapping(value="/repositories/{owner}/{repositoryname}", method = RequestMethod.GET)
//	public Mono<ServerResponse> getRepositoryInfoNonBlocking(ServerRequest request) { 
//        String owner = String.valueOf(request.pathVariable("owner"));
//        String repositoryname = String.valueOf(request.pathVariable("repositoryname"));
//        return ServerResponse.ok().bodyValue(githubService.getGithubRepoInfo(owner, repositoryname));
//        
//    }
	
	
	

}
