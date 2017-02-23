package com.soprasteria.hashcode.algorithme;

import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;

public abstract class AbstractAlgorithme implements Algorithme {

	private FileData lire(String entree) {
		// FIXME à coder
		return null;
	}

	private void ecrire(SortieAlgorithme sortie) {
		// FIXME à coder
	}

	public void run(String entree) {
		FileData data = lire(entree);
		SortieAlgorithme sortie = optimiser(data);
		ecrire(sortie);
	}

}
