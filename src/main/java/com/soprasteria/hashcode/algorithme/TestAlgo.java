package com.soprasteria.hashcode.algorithme;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.soprasteria.hashcode.dto.Cache;
import com.soprasteria.hashcode.dto.Endpoint;
import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.Request;
import com.soprasteria.hashcode.dto.Video;
import com.soprasteria.hashcode.dto.output.CacheSortie;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;

/**
 * Nouvel algo, à tester
 * 
 * @author smonfort
 *
 */
public class TestAlgo extends AbstractAlgorithme {

	// Retourne la latence moyenne d'un endpoint vers ses caches
	private long getAverageCacheLatencyForEndpoint(Endpoint endpoint) {
		if (endpoint.getCachesLatencies().isEmpty()) {
			return endpoint.getDatacenterLatency();
		}
		long latency = 0;
		for (Integer cacheId : endpoint.getCachesLatencies().keySet()) {
			latency = latency + endpoint.getCachesLatencies().get(cacheId);
		}
		return latency / endpoint.getCachesLatencies().size();
	}

	private Long getCost(FileData entree, Endpoint endpoint) {
		// Un endpoint est d'autant plus couteux qu'il demande beaucoup de
		// vidéos en étant loin du central
		long estimationGain = endpoint.getDatacenterLatency()
				- getAverageCacheLatencyForEndpoint(endpoint);
		long cost = 0;

		// On inspecte les requêtes pour déterminer le gain à optimiser le
		// endpoint
		for (Request req : entree.getRequests()) {
			if (req.getEndpoint().getId() == endpoint.getId()) {
				cost = cost + req.getCount() * estimationGain;
			}
		}
		return cost;
	}

	/**
	 * Tente de mettre en cache la vidéo pour ce endpoint en regardant les
	 * caches associés
	 * 
	 * @param video
	 * @param endpoint
	 * @param output
	 */
	private void mettreEnCacheVideoPourEndpoint(FileData entree, Video video,
			Endpoint endpoint, SortieAlgorithme output) {
		Map<Integer, Cache> cachesDuEndpoint = endpoint.getCaches();

		// Parcourir tous les caches du endpoint et tenter d'y mettre la vidéo
		for (Integer key : cachesDuEndpoint.keySet()) {
			mettreVideoDansCache(entree, cachesDuEndpoint.get(key), video,
					output);
		}

	}

	/**
	 * Tenter de mettre la vidéo dans le cache
	 * 
	 * @param cache
	 * @param video
	 * @param output
	 */
	private void mettreVideoDansCache(FileData entree, Cache cache,
			Video video, SortieAlgorithme output) {

		CacheSortie cacheARemplir = null;
		for (CacheSortie cacheSortie : output.getCaches()) {
			if (cacheSortie.getIdCache() == cache.getId()) {
				cacheARemplir = cacheSortie;
				break;
			}
		}
		// Si le cache n'est pas présent dans la sortie, le rajouter
		if (cacheARemplir == null) {
			cacheARemplir = new CacheSortie();
			cacheARemplir.setIdCache(cache.getId());
			// Vérifier si la vidéo rentre
			if (video.getSize() < cache.getSize()) {
				cacheARemplir.getIdsVideo().add(video.getId());
				output.getCaches().add(cacheARemplir);
			}
		}
		// Sinon, regarder ce qu'il y a déjà dans le cache et on rajoute la
		// vidéo si ça passe
		List<Integer> videos = cacheARemplir.getIdsVideo();
		int taille = 0;
		for (Integer idVideo : videos) {
			// Récupérer la taille des vidéos présentes dans le cache
			for (Video vid : entree.getVideos()) {
				if (vid.getId() == idVideo) {
					taille += vid.getSize();
				}
			}
		}
		// Si ça passe, on ajoute!
		if (taille + video.getSize() < cache.getSize()) {
			cacheARemplir.getIdsVideo().add(video.getId());
		}

	}

	private void traiterEndpoint(FileData entree, Endpoint endpoint,
			SortieAlgorithme output) {
		// Pour un endpoint donné, remplir l'ensemble de ses caches avec les
		// vidéos demandées par ce endpoint

		// Prendre en priorité les vidéos les plus demandées pour ce endpoint
		Map<Integer, Video> videosEndpoint = new TreeMap<Integer, Video>();
		for (Request req : entree.getRequests()) {
			if (req.getEndpoint().getId() == endpoint.getId()) {
				videosEndpoint.put(-req.getCount(), req.getVideo());
			}
		}

		// Traiter en premier les videos à décharger
		for (Integer key : videosEndpoint.keySet()) {
			mettreEnCacheVideoPourEndpoint(entree, videosEndpoint.get(key),
					endpoint, output);
		}

	}

	@Override
	public SortieAlgorithme optimiser(FileData entree) {
		SortieAlgorithme output = new SortieAlgorithme();

		// Déterminer la latence que l'on peut gagner par endpoint
		Map<Long, Endpoint> latencesEndpoint = new TreeMap<Long, Endpoint>();

		for (Endpoint endpoint : entree.getEndpoints()) {
			Long cost = getCost(entree, endpoint);
			latencesEndpoint.put(-cost, endpoint);
		}

		// Traiter en premier les endpoints à décharger
		for (Long key : latencesEndpoint.keySet()) {
			traiterEndpoint(entree, latencesEndpoint.get(key), output);
		}

		output.setNombreCacheUtilises(output.getCaches().size());

		return output;
	}

	public static void main(String[] args) {
		new TestAlgo().run("src/main/resources/me_at_the_zoo.in");
		System.out.println("Ok pour me_at_the_zoo.in");
		new TestAlgo().run("src/main/resources/videos_worth_spreading.in");
		System.out.println("Ok pour videos_worth_spreading.in");
		new TestAlgo().run("src/main/resources/kittens.in");
		System.out.println("Ok pour kittens.in");
	}

}
