package com.soprasteria.hashcode.algorithme;

import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;
import com.soprasteria.hashcode.dto.writer.FileWriter;
import com.soprasteria.hashcode.reader.InputFileReader;

public abstract class AbstractAlgorithme implements Algorithme {

	private FileData lire(String entree) {
		return InputFileReader.readFile(entree);
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
