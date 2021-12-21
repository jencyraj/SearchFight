package com.searchfight.search.secure.factory;

import com.searchfight.search.secure.model.enginename;
import com.searchfight.search.secure.services.Search;
import com.searchfight.search.secure.services.SerchService_Big;
import com.searchfight.search.secure.services.SerchService_google;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FactorySearch {

    @Autowired
    private SerchService_Big bingSearchService;
    @Autowired
    private SerchService_google googleSearchService;

    public Search getSearchEngine(enginename engineName) {
        switch (engineName) {
            case BING:
                return bingSearchService;
            case GOOGLE:
                return googleSearchService;
        }
        throw new RuntimeException("OOPS!! Search Engine not found");
    }
}
