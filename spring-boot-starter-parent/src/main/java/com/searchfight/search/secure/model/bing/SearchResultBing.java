package com.searchfight.search.secure.model.bing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SearchResultBing {

    private SearchInfoBing searchinformationbing;

	public SearchInfoBing getSearchinformationbing() {
		return searchinformationbing;
	}

	public void setSearchinformationbing(SearchInfoBing searchinformationbing) {
		this.searchinformationbing = searchinformationbing;
	}

	
}
