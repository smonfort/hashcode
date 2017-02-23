/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  Endpoint.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jdecure
 * 
 */
public class Endpoint {

	private Map<Integer, Integer> cachesLatencies;
	private Map<Integer, Cache> caches;

	private int datacenterLatency;
	private int id;

	/**
	 * 
	 */
	public Endpoint() {
		cachesLatencies = new HashMap<>();
		caches = new HashMap<>();
	}

	public void addCache(int idCache, int cacheLatency, int cacheSize) {
		caches.put(idCache, new Cache(idCache, cacheLatency, cacheSize));
		cachesLatencies.put(idCache, cacheLatency);
	}
	
	public void addCache(Cache cache) {
		caches.put(cache.getId(), cache);
		cachesLatencies.put(cache.getId(), cache.getLatency());
	}

	/**
	 * @param caches
	 *            the caches to set
	 */
	public void setCaches(Map<Integer, Cache> caches) {
		this.caches = caches;
	}

	/**
	 * @return the caches
	 */
	public Map<Integer, Cache> getCaches() {
		return caches;
	}

	/**
	 * @param cachesLatencies
	 *            the cachesLatencies to set
	 */
	public void setCachesLatencies(Map<Integer, Integer> cachesLatencies) {
		this.cachesLatencies = cachesLatencies;
	}

	/**
	 * @return the cachesLatencies
	 */
	public Map<Integer, Integer> getCachesLatencies() {
		return cachesLatencies;
	}

	/**
	 * @param datacenterLatency
	 *            the datacenterLatency to set
	 */
	public void setDatacenterLatency(int datacenterLatency) {
		this.datacenterLatency = datacenterLatency;
	}

	/**
	 * @return the datacenterLatency
	 */
	public int getDatacenterLatency() {
		return datacenterLatency;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
