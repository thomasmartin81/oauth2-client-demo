package ch.duerri.oauth2.client.springboot;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.Charset;

@RestController
public class SpringBootRestController {
    @Value("${springboot.client-id}")
    private String clientId;

    @Value("${springboot.client-secret}")
    private String clientSecret;

    @Value("${springboot.server-base-url}")
    private String springBootServerBaseUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping(value = "/oauth2/code/springboot", produces = MediaType.APPLICATION_JSON_VALUE)
    public SpringBootAccessResponse processCodeAuthorization(@RequestParam("code") String code, @RequestParam(value = "state", required = false) String state){
        System.out.println("code=" + code);
        System.out.println("state=" + state);

        String basicUserPasswordString = clientId + ":" + clientSecret;
        String authorizationString = "Basic " +  new String(Base64.encodeBase64(basicUserPasswordString.getBytes(Charset.forName("US-ASCII"))));

        SpringBootAccessResponse response = webClientBuilder.build() //
                .post() //
                .uri(springBootServerBaseUrl+"/oauth/token?grant_type=authorization_code&code=" + code) //
                .header("Authorization", authorizationString)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve() //
                .bodyToMono(SpringBootAccessResponse.class) // Reactive will wait on the mono until it will return
                .block(); // blocks wait until the mono is set, otherwise the whole method must be changed to mono

        return response;
    }

}
