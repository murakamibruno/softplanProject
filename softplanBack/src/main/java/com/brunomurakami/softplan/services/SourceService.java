package com.brunomurakami.softplan.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SourceService {

    public Map<String, String> getGithubURL() {
        Map<String, String> url = new HashMap<>();
        url.put("url", "https://github.com/murakamibruno/softplanProject/");
        return url;
    }
}
