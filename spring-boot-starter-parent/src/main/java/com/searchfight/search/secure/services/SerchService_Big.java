package com.searchfight.search.secure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;
import com.searchfight.search.secure.exception.exceptionapi;
import com.searchfight.search.secure.model.bing.SearchResultBing;
@Service
@RequiredArgsConstructor
public class SerchService_Big extends Search {

    private RestTemplate restTemplate;
    private String baseUrl;
    private String apiKey;

    @Autowired
    public SerchService_Big(RestTemplate restTemplate, @Value("${baseUrl.bing}") String baseUrl,
                             @Value("${apiKey.bing}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    private String buildUrl(String searchTerm) {
        UriComponentsBuilder uri = fromUriString(baseUrl)
                .queryParam("q", searchTerm);
        return uri.toUriString();
    }
    
    @Override
    public long get_Hits(String searchTerm) {
        long hits = 0;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Subscription-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<SearchResultBing> response = restTemplate
                    .exchange(buildUrl(searchTerm), GET, entity, SearchResultBing.class);
            if (response.getStatusCode()==OK)
            {
            	if(response.getBody().getSearchinformationbing()!=null)
            	{
            		hits = response.getBody().getSearchinformationbing().getTotalMatch();
            	}
            }

             else {
                throw new exceptionapi("Error: No response found " + response.getStatusCodeValue());
            }
        } catch (Exception e) {
            throw new exceptionapi("OOPS!!Bing API failed", e);
        }
        return hits;
    }

    
}