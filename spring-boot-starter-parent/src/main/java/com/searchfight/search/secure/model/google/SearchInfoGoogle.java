package com.searchfight.search.secure.model.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SearchInfoGoogle {

    private long TotalCount;

	public long getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(long totalCount) {
		TotalCount = totalCount;
	}

	

	
}
