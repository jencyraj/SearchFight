package com.searchfight.search.secure.services;

import java.util.*;

import org.springframework.stereotype.Service;
import com.searchfight.search.secure.factory.FactorySearch;
import com.searchfight.search.secure.model.enginename;
import com.searchfight.search.secure.model.teamResult;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
@Data

public class Searchservices {

    
   
    
    
    
	
	FactorySearch factorysearch = new FactorySearch();
	public Searchservices(FactorySearch factorysearch_val) {
		factorysearch =factorysearch_val;
		
	}

	public Map<String, List<teamResult>> search(String[] terms) 
    {
        Map<String, List<teamResult>> results = new HashMap<>();
        for (String term : terms) {
            for (enginename engineName : enginename.values()) 
            {
                Search engine = factorysearch.getSearchEngine(engineName);
                long hits = engine.get_Hits(term);
                if (results.get(term) == null) 
                {
                    List<teamResult> engineList = new ArrayList<>(singletonList(new teamResult(term, engineName.name(), hits)));
                    results.put(term, engineList);
                } 
                else 
                {
                    results.get(term).add(new teamResult(term, engineName.name(), hits));
                }
            }
        }
        listResultsPerTerm(results);
        return results;
    }

    public Map<String, String> getEnginesWinnerEngine(Map<String, List<teamResult>> results) {
        Map<String, String> winners = new HashMap<>();
        for (int i = 0; i < enginename.values().length; i++) {
            long engineWinnerHits = 0;
            String engineName = "";
            String winnerTerm = "";
            for (Map.Entry<String, List<teamResult>> entry : results.entrySet()) {
                long currentEngineHit = entry.getValue().get(i).getHitResult();
                if (engineWinnerHits < currentEngineHit) {
                    engineWinnerHits = currentEngineHit;
                    winnerTerm = entry.getKey();
                    engineName = entry.getValue().get(i).getEngineName();
                }
            }
            winners.put(engineName, winnerTerm);
        }
        listEnginesWinner(winners);
        return winners;
    }

    public String getTotalWinnerEngine(Map<String, List<teamResult>> results) {
        String winner = "";
        long winnerHits = 0;
        for (Map.Entry<String, List<teamResult>> entry : results.entrySet()) {
            Long termTotalEnginesHits = entry.getValue().stream()
                    .map(teamResult::getHitResult)
                    .reduce(0L, Long::sum);
            if (winnerHits < termTotalEnginesHits) {
                winnerHits = termTotalEnginesHits;
                winner = entry.getKey();
            }
        }
        return winner;
    }

    private void listEnginesWinner(Map<String, String> engineWinners) {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, String> entry : engineWinners.entrySet()) {
            str.append(entry.getKey()).append(" Winner: ").append(entry.getValue());
            str.append(" \n");
        }
        System.out.println(str.toString().trim());
    }

    private void listResultsPerTerm(Map<String, List<teamResult>> results) {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, List<teamResult>> entry : results.entrySet()) {
            str.append(entry.getKey()).append(": ");
            for (teamResult searchTerm : entry.getValue()) {
                str.append(" ").append(searchTerm.getEngineName())
                        .append(": ").append(searchTerm.getHitResult());
            }
            str.append(" \n");
        }
        System.out.println(str.toString().trim());
    }
}
