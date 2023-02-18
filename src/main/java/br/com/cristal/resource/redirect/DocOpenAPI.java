package br.com.cristal.resource.redirect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DocOpenAPI {

    @Value("${springdoc.swagger-ui.path}")
    private String docUrl;

    @GetMapping("/")
    public void redirect(HttpServletResponse response) {
        response.setHeader("Location", docUrl);
        response.setStatus(302);
    }
}
