package ru.itis.homework_2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itis.homework_2.services.ProfanityFilterService;

@Component
public class ProfanityFilterServiceImpl implements ProfanityFilterService {
    @Autowired
    private RestTemplate restTemplate;

    public boolean containsProfanity(String text) {
        try {
            String url = URL + text;
            String response = restTemplate.getForObject(url, String.class);
            return Boolean.parseBoolean(response);
        } catch (Exception e) {
            return true;
        }
    }
}
