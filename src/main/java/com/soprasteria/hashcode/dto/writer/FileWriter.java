package com.soprasteria.hashcode.dto.writer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.soprasteria.hashcode.dto.output.CacheSortie;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;

public class FileWriter {

	public static void ecrire(SortieAlgorithme sortie, String nomFichier) {
		StringBuffer bf = new StringBuffer();

		bf.append(sortie.getNombreCacheUtilises());
		bf.append("\n");
		for (CacheSortie cache : sortie.getCaches()) {
			bf.append(cache.getIdCache());
			bf.append(" ");
			boolean premiereVideo = true;
			for (Integer idVideo : cache.getIdsVideo()) {
				if (!premiereVideo) {
					bf.append(" ");
				}
				bf.append(idVideo);
				premiereVideo = false;

			}
			bf.append("\n");
		}

		try {
			FileUtils.writeStringToFile(new File("target/" + nomFichier),
					bf.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SortieAlgorithme sortie = new SortieAlgorithme();
		sortie.setNombreCacheUtilises(2);
		CacheSortie cache0 = new CacheSortie();
		cache0.setIdCache(0);
		cache0.getIdsVideo().add(1);
		cache0.getIdsVideo().add(2);
		sortie.getCaches().add(cache0);
		CacheSortie cache1 = new CacheSortie();
		cache1.setIdCache(1);
		cache1.getIdsVideo().add(3);
		sortie.getCaches().add(cache1);

		FileWriter.ecrire(sortie, "test.txt");

	}

}
