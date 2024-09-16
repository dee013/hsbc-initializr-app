package org.hacktn.initialr.app.service;

import org.hacktn.initialr.app.dto.GitRequestDto;
import org.hacktn.initialr.app.model.GitRepoOrder;
import org.hacktn.initialr.app.repository.GitRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@PropertySource("classpath:gitconfig.yaml")
public class GitService {

    @Autowired
    private GitRequestRepository gitRequestRepository;

    @Value("${github.api-url}")
    private String GITHUB_API_URL;

    public GitRepoOrder createRepository(GitRequestDto gitRequestDto) {
        GitRepoOrder gitRequest = new GitRepoOrder();
        gitRequest.setPersonalAccessToken(gitRequestDto.getPersonalAccessToken());
        gitRequest.setRepositoryName(gitRequestDto.getRepositoryName());

        // Save request to H2 database
        gitRequestRepository.save(gitRequest);

        // Create request body
        String requestBody = "{\"name\":\"" + gitRequestDto.getRepositoryName() + "\"}";

        // Send POST request to GitHub API using WebClient
        WebClient webClient = WebClient.builder()
                .baseUrl(GITHUB_API_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "token " + gitRequestDto.getPersonalAccessToken())
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
                .build();

        String response = webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Save response status
        gitRequest.setResponseStatus(response);
        gitRequestRepository.save(gitRequest);

        return gitRequest;
    }
}