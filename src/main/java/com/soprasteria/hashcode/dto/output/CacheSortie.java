/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  Cache.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.dto.output;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jdecure
 *
 */
public class CacheSortie {

	private List<Integer> idsVideo;

	private int idCache;

	public int getIdCache() {
		return idCache;
	}

	public void setIdCache(int idCache) {
		this.idCache = idCache;
	}

	public List<Integer> getIdsVideo() {
		if (idsVideo == null) {
			idsVideo = new ArrayList<Integer>();
		}
		return idsVideo;
	}

	public void setIdsVideo(List<Integer> idsVideo) {
		this.idsVideo = idsVideo;
	}

}
