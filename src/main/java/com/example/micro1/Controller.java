package com.example.micro1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gety")
public class Controller {

    public static final String URL
            = "http://MICROCHELIK2";

    @Autowired
    @LoadBalanced
    private WebClient.Builder webClient;


    @GetMapping("/{name}")
    public String index(@PathVariable("name") String name) {
        System.err.println(name);
          WebClient client =  webClient.baseUrl(URL).build();

        return client.post()
                .uri("/posty/post")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Dto(name)), Dto.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
