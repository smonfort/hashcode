/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  Cache.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.dto.output;

import java.util.List;

/**
 * @author jdecure
 *
 */
public class Cache {

	private List<String> idVideo;

	private int idCache;

	public int getIdCache() {
		return idCache;
	}

	public void setIdCache(int idCache) {
		this.idCache = idCache;
	}

	public List<String> getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(List<String> idVideo) {
		this.idVideo = idVideo;
	}

}
