package com.soprasteria.hashcode.dto.output;

import java.util.List;

public class SortieAlgorithme {

	private List<Cache> caches;

	private long nombreCacheUtilises;

	public long getNombreCacheUtilises() {
		return nombreCacheUtilises;
	}

	public void setNombreCacheUtilises(long nombreCacheUtilises) {
		this.nombreCacheUtilises = nombreCacheUtilises;
	}

	public List<Cache> getCaches() {
		return caches;
	}

	public void setCaches(List<Cache> caches) {
		this.caches = caches;
	}

}
