package com.searchfight.search.secure.model.bing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SearchInfoBing {

    private long totalMatch;

	public long getTotalMatch() {
		return totalMatch;
	}

	public void setTotalMatch(long totalMatch) {
		this.totalMatch = totalMatch;
	}
}
