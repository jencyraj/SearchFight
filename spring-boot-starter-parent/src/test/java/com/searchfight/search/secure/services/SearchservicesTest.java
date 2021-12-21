package com.searchfight.search.secure.services;
import com.searchfight.search.secure.factory.FactorySearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static com.searchfight.search.secure.model.enginename.BING;
import static com.searchfight.search.secure.model.enginename.GOOGLE;

class SearchservicesTest {

	   private Searchservices searchServices;
	   private FactorySearch factorysearch;
	   private SerchService_google searchGoogleService;
	   private SerchService_Big serchservice_Big;

	   @BeforeEach
	   void setup() {
		   
		   factorysearch = mock(FactorySearch.class);
		   
		   searchGoogleService = mock(SerchService_google.class);
		   
		   serchservice_Big = mock(SerchService_Big.class);
		   searchServices = new Searchservices(factorysearch);
		   
		   
	       when(factorysearch.getSearchEngine(GOOGLE)).thenReturn(searchGoogleService);
	       when(factorysearch.getSearchEngine(BING)).thenReturn(serchservice_Big);
	       when(searchGoogleService.get_Hits("Jency")).thenReturn(10L);
	       when(serchservice_Big.get_Hits("Jency")).thenReturn(11L);
	       when(searchGoogleService.get_Hits("Raj")).thenReturn(13L);
	       when(serchservice_Big.get_Hits("Raj")).thenReturn(14L);
	   }

	    @Test
	    void serchAndReturnResult() {
	        var results = searchServices.search(new String[]{"Jency", "Raj"});

	        assertEquals(2, results.size());
	        assertEquals("GOOGLE", results.get("Jency").get(0).getEngineName());
	        assertEquals("BING", results.get("Jency").get(1).getEngineName());
	        assertEquals(11, results.get("Jency").get(1).getHitResult());

	        assertEquals("GOOGLE", results.get("Raj").get(0).getEngineName());
	        assertEquals("BING", results.get("Raj").get(1).getEngineName());
	        assertEquals(13, results.get("Raj").get(0).getHitResult());
	    }

	    @Test
	    void ReturnTottalWinner() 
	    {
	        when(searchGoogleService.get_Hits("Basil")).thenReturn(18L);
	        when(serchservice_Big.get_Hits("Basil")).thenReturn(50L);
	        var results = searchServices.search(new String[]{"Jency", "Raj", "Basil"});
	        var totalWinner = searchServices.getTotalWinnerEngine(results);
	        assertEquals("Basil", totalWinner);
	    }

	    @Test
	    void returnWinnerPerEngine() {
	        var results = searchServices.search(new String[]{"Jency", "Raj"});
	        var engineWinners = searchServices.getEnginesWinnerEngine(results);
	        assertEquals(2, engineWinners.size());
	        assertEquals("Raj", engineWinners.get("GOOGLE"));
	        assertEquals("Raj",engineWinners.get("BING"));
	    }
	}
