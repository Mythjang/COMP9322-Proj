package com.model;

public class Link {	
	private String href;
	private String rel;
	public Link(String href, String rel) {
		super();
		this.href = href;
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
		
}
