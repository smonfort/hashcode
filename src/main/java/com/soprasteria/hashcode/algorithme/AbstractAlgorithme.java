package com.soprasteria.hashcode.algorithme;

import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;
import com.soprasteria.hashcode.dto.writer.FileWriter;

public abstract class AbstractAlgorithme implements Algorithme {

	private FileData lire(String entree) {
		return null;
	}

	private void ecrire(SortieAlgorithme sortie, String nomFichier) {
		FileWriter.ecrire(sortie, nomFichier);
	}

	public void run(String entree) {
		FileData data = lire(entree);
		SortieAlgorithme sortie = optimiser(data);
		ecrire(sortie, entree + ".output");
	}

}
