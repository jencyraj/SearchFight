package com.searchfight.search.secure;
import com.searchfight.search.secure.model.teamResult;
import com.searchfight.search.secure.services.Searchservices;

import java.util.*;
import static java.util.Arrays.asList;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchFightApp implements CommandLineRunner {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
    	System.out.println("testConnection_3");
    	
        return builder.build();
    }

    @Autowired
    private Searchservices searchServices;
    
    public static void main(String[] args) {
    	System.out.println("testConnection_4");
    	 SpringApplication application = new SpringApplication(SearchFightApp.class);
    	 application.run(args);

        
    }

    
    private String[] Removeduplicates(String[] terms) {
    	System.out.println("testConnection_2");
        Set<String> termsSet = new HashSet<>(asList(terms));
        String[] result = {};
        System.out.println("result" + result);
        return termsSet.toArray(result);
    }
    
    @Override
    public void run(String[] args) {
    	
    	System.out.println("testConnection_1");
    	
        args = Removeduplicates(args);
       // System.out.println(args.length);
        if (args.length > 1) 
        {
            System.out.println("\n" + "*********SEARCH ENGINE FIGHT RESULT******");
            Map<String,List<teamResult>> results = searchServices.search(args);
            searchServices.getTotalWinnerEngine(results);
            System.out.println("Total Winner: " + searchServices.getTotalWinnerEngine(results));
        } 
        else 
        {
            System.out.println("\n" + "OOPS!! Please run the program with at least two distinct words");
        }
    }

    

}