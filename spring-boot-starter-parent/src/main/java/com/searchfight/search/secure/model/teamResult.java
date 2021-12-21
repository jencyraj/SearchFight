package com.searchfight.search.secure.model;

import lombok.Data;

@Data
public class teamResult {

    private String searchTerm;
    private String engineName;
    private long hitResult;

    public teamResult(String searchTerm, String engineName, long hitResult) {
        this.setSearchTerm(searchTerm);
        this.setEngineName(engineName);
        this.setHitResult(hitResult);
    }

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public long getHitResult() {
		return hitResult;
	}

	public void setHitResult(long hitResult) {
		this.hitResult = hitResult;
	}
}
