package org.packt.client;

import java.io.Serializable;

import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class SessionCounter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int searches = 0;
	
	public SessionCounter(){
		System.out.println("Creating a new session counter");
	}

	public int getSearches() {
		return searches;
	}

	public void setSearches(int searches) {
		this.searches = searches;
	}
}
