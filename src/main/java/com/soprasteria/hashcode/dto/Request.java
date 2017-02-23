/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  Request.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.dto;

/**
 * @author jdecure
 *
 */
public class Request {

	private int count;
	private Video video;
	private Endpoint endpoint;
	
	/**
	 * 
	 */
	public Request(int count, Video video, Endpoint endpoint) {
		this.count = count;
		this.video = video;
		this.endpoint = endpoint;
	}
	
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * @param video the video to set
	 */
	public void setVideo(Video video) {
		this.video = video;
	}
	
	/**
	 * @return the video
	 */
	public Video getVideo() {
		return video;
	}
	
	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(Endpoint endpoint) {
		this.endpoint = endpoint;
	}
	
	/**
	 * @return the endpoint
	 */
	public Endpoint getEndpoint() {
		return endpoint;
	}
	
}
