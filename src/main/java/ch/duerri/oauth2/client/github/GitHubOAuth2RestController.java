package ch.duerri.oauth2.client.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class GitHubOAuth2RestController {
    @Value("${github.client-id}")
    private String clientId;

    @Value("${github.client-secret}")
    private String clientSecret;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping(value = "/oauth2/code/github", produces = MediaType.APPLICATION_JSON_VALUE)
    public String processCodeAuthorization(@RequestParam("code") String code, @RequestParam("state") String state){
        System.out.println("code=" + code);
        System.out.println("state=" + state);

        GitHubAccessResponse gitHubAccessResponse = webClientBuilder.build() //
                .post() //
                .uri("https://github.com/login/oauth/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code) //
               .accept(MediaType.APPLICATION_JSON)
               .retrieve() //
                .bodyToMono(GitHubAccessResponse.class) // Reactive will wait on the mono until it will return
                .block(); // blocks wait until the mono is set, otherwise the whole method must be changed to mono

        System.out.println(gitHubAccessResponse);

        String userInfo = webClientBuilder.build() //
                .get() //
                .uri("https://api.github.com/user") //
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", gitHubAccessResponse.getTokenType() + " " + gitHubAccessResponse.getAccessToken()) //
                .retrieve() //
                .bodyToMono(String.class) // Reactive will wait on the mono until it will return
                .block(); // blocks wait until the mono is set, otherwise the whole method must be changed to mono

        System.out.println(userInfo);

        return userInfo;
    }
}
