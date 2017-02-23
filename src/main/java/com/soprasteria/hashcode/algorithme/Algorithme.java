package com.soprasteria.hashcode.algorithme;

import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;

public interface Algorithme {

	public SortieAlgorithme optimiser(FileData entree);

}
