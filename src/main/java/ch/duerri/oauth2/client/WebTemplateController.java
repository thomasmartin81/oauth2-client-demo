package ch.duerri.oauth2.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebTemplateController {

    @Value("${github.client-id}")
    private String gitHubClientId;

    @Value("${springboot.client-id}")
    private String springBootClientId;

    @Value("${springboot.server-base-url}")
    private String springBootServerBaseUrl;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("gitHubClientId", gitHubClientId);
        model.addAttribute("springBootClientId", springBootClientId);
        model.addAttribute("springBootServerAuthorizeUrl", springBootServerBaseUrl + "/oauth/authorize");
        return "home";
    }
}
