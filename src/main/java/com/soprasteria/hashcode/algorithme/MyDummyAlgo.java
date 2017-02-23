package com.soprasteria.hashcode.algorithme;

import com.soprasteria.hashcode.dto.Cache;
import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.output.CacheSortie;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;

/**
 * Algo le plus simple du monde pour vérifier que tout est ok.
 * 
 * @author smonfort
 *
 */
public class MyDummyAlgo extends AbstractAlgorithme {

	@Override
	public SortieAlgorithme optimiser(FileData entree) {
		SortieAlgorithme output = new SortieAlgorithme();
		output.setNombreCacheUtilises(entree.getCaches().size());
		for (Cache cache : entree.getCaches()) {
			CacheSortie cacheSortie = new CacheSortie();
			cacheSortie.setIdCache(cache.getId());
			cacheSortie.getIdsVideo().add(entree.getVideos().get(0).getId());
			cacheSortie.getIdsVideo().add(entree.getVideos().get(1).getId());
			output.getCaches().add(cacheSortie);
		}
		return output;
	}

	public static void main(String[] args) {
		new MyDummyAlgo().run("src/main/resources/kittens.in");
	}

}
