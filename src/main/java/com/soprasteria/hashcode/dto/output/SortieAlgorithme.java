package com.soprasteria.hashcode.dto.output;

import java.util.ArrayList;
import java.util.List;

public class SortieAlgorithme {

	private List<CacheSortie> caches;

	private long nombreCacheUtilises;

	public long getNombreCacheUtilises() {
		return nombreCacheUtilises;
	}

	public void setNombreCacheUtilises(long nombreCacheUtilises) {
		this.nombreCacheUtilises = nombreCacheUtilises;
	}

	public List<CacheSortie> getCaches() {
		if (caches == null) {
			caches = new ArrayList<CacheSortie>();
		}
		return caches;
	}

	public void setCaches(List<CacheSortie> caches) {
		this.caches = caches;
	}

}
