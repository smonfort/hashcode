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

	private int videoCount;
	private int endpointCount;
	private int requestCount;
	private int cacheCount;
	private int cacheSize;

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

	/**
	 * @return the videoCount
	 */
	public int getVideoCount() {
		return videoCount;
	}

	/**
	 * @param videoCount
	 *            the videoCount to set
	 */
	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	/**
	 * @return the endpointCount
	 */
	public int getEndpointCount() {
		return endpointCount;
	}

	/**
	 * @param endpointCount
	 *            the endpointCount to set
	 */
	public void setEndpointCount(int endpointCount) {
		this.endpointCount = endpointCount;
	}

	/**
	 * @return the requestCount
	 */
	public int getRequestCount() {
		return requestCount;
	}

	/**
	 * @param requestCount
	 *            the requestCount to set
	 */
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}

	/**
	 * @return the cacheCount
	 */
	public int getCacheCount() {
		return cacheCount;
	}

	/**
	 * @param cacheCount
	 *            the cacheCount to set
	 */
	public void setCacheCount(int cacheCount) {
		this.cacheCount = cacheCount;
	}

	/**
	 * @return the cacheSize
	 */
	public int getCacheSize() {
		return cacheSize;
	}

	/**
	 * @param cacheSize
	 *            the cacheSize to set
	 */
	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

}
