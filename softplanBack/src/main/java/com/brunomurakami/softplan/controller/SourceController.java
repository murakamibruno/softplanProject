package com.brunomurakami.softplan.controller;

import com.brunomurakami.softplan.model.Pessoa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/source")
public class SourceController {
    @GetMapping("")
    public Map<String, String> findGithubURL() {
        Map<String, String> url = new HashMap<>();
        url.put("url", "https://github.com/murakamibruno/");
        return url;
    }
}
