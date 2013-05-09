package org.packt.client;

import java.io.Serializable;

import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class RequestCounter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int searches = 0;
	
	public RequestCounter(){
	}

	public synchronized int getSearches() {
		return searches;
	}

	public synchronized void setSearches(int searches) {
		this.searches = searches;
	}
}
