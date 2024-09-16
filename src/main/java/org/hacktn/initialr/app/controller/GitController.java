package org.hacktn.initialr.app.controller;

import org.hacktn.initialr.app.dto.GitRequestDto;
import org.hacktn.initialr.app.model.GitRepoOrder;
import org.hacktn.initialr.app.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/git")
public class GitController {

    @Autowired
    private GitService gitService;

    @PostMapping("/create-repo")
    public ResponseEntity<GitRepoOrder> createRepository(@RequestBody GitRequestDto gitRequestDto) {
        GitRepoOrder response = gitService.createRepository(gitRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}