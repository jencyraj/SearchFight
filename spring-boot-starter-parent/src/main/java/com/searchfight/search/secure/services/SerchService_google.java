package com.searchfight.search.secure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import com.searchfight.search.secure.model.google.SearchResultGoogle;
import com.searchfight.search.secure.exception.exceptionapi;
@Service
@RequiredArgsConstructor
public class SerchService_google extends Search {

    private RestTemplate restTemplate;
    private String baseUrl;
    private String cx;
    private String apiKey;

    @Autowired
    public SerchService_google(RestTemplate restTemplate, @Value("${baseUrl.google}") String baseUrl,
                               @Value("${googleCx}") String cx, @Value("${apiKey.google}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.cx = cx;
        this.apiKey = apiKey;
        
        System.out.println(apiKey);
    }

    @Override
    public long get_Hits(String searchTerm) {
        try {
            ResponseEntity<SearchResultGoogle> response
                    = restTemplate.getForEntity(create_Url(searchTerm), SearchResultGoogle.class);
            if (response.getStatusCode() == OK) 
            {
            	return response.getBody().getGooglesearchinformation().getTotalCount();               
            	
            } 
            else 
            {
                throw new exceptionapi("No response found " + response.getStatusCodeValue());
            }
        } 
        catch (Exception e) 
        {
            throw new exceptionapi("OOPS!! Google API failed", e);
        }
    }

    private String create_Url(String searchTerm) {
        UriComponentsBuilder uri = fromUriString(baseUrl)
                .queryParam("key", apiKey)
                .queryParam("cx", cx).queryParam("q", searchTerm);
        return uri.toUriString();
    }
}
