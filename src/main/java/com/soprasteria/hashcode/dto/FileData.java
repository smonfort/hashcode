/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  FileData.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.dto;

import java.util.List;

/**
 * @author jdecure
 *
 */
public class FileData {

	private List<Cache> caches;

	private List<Endpoint> endpoints;

	private List<Request> requests;

	private List<Video> videos;

	public List<Cache> getCaches() {
		return caches;
	}

	public void setCaches(List<Cache> caches) {
		this.caches = caches;
	}

	public List<Endpoint> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(List<Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

}
