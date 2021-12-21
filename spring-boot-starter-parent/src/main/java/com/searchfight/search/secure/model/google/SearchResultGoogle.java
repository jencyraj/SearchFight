package com.searchfight.search.secure.model.google;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SearchResultGoogle {

    private SearchInfoGoogle googlesearchinformation;

	public SearchInfoGoogle getGooglesearchinformation() {
		return googlesearchinformation;
	}

	public void setGooglesearchinformation(SearchInfoGoogle googlesearchinformation) {
		this.googlesearchinformation = googlesearchinformation;
	}
}
