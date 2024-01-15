package com.brunomurakami.softplan.controller;

import com.brunomurakami.softplan.model.Pessoa;
import com.brunomurakami.softplan.services.SourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/source")
@AllArgsConstructor
public class SourceController {

    private final SourceService service;
    @GetMapping("")
    public Map<String, String> getGithubURL() {
        return service.getGithubURL();
    }
}
