/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  InputFileReader.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.soprasteria.hashcode.dto.Cache;
import com.soprasteria.hashcode.dto.Endpoint;
import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.Request;
import com.soprasteria.hashcode.dto.Video;

/**
 * @author jdecure
 *
 */
public class InputFileReader {
	
	public static void main(String[] args) {
		FileData fd = readFile("d:\\hashcode\\kittens.in");
	}

	public static FileData readFile(String filePath) {
		Map<Integer, Video> videos = null;
		Map<Integer, Endpoint> endpoints = null;
		
		List<Video> videosAsList = null;
		List<Endpoint> endpointsAsList = null;
		List<Cache> cachesAsList = null;
		List<Request> requests = null;
		
		FileData fileData = new FileData();
		try {
			List<String> lines = FileUtils.readLines(new File(filePath));
			
			int index = 0;
			boolean inEndpoint = false;
			boolean inRequest = false;
			int currentEndpointIndex = -1;
			int currentEndpointCacheCount = 0;
			int currentCacheIndex = -1;
			int maxEndpoint = 0;
			Endpoint currentEndpoint = null;
			Cache currentCache = null;
			Request currentRequest = null;
			String[] lineData = null;
			
			for (String line : lines) {
				// Lecture de la première ligne : entete
				if (index == 0) {
					//System.out.println("Lecture de la premiere ligne : informations generales.");
					lineData = line.split(" ");
					fileData.setVideoCount(Integer.valueOf(lineData[0]));
					fileData.setEndpointCount(Integer.valueOf(lineData[1]));
					maxEndpoint = fileData.getEndpointCount();
					fileData.setRequestCount(Integer.valueOf(lineData[2]));
					fileData.setCacheCount(Integer.valueOf(lineData[3]));
					fileData.setCacheSize(Integer.valueOf(lineData[4]));
					
					videosAsList = new ArrayList<>(fileData.getVideoCount());
					videos = new HashMap<>(fileData.getVideoCount());
					endpointsAsList = new ArrayList<>(fileData.getEndpointCount());
					endpoints = new HashMap<>(fileData.getEndpointCount());
					cachesAsList = new ArrayList<>(fileData.getCacheCount());
					requests = new ArrayList<>(fileData.getRequestCount());
				}
				
				// Lecture des tailles des videos
				if (index == 1) {
					//System.out.println("Lecture de la dexième ligne : taille des videos.");
					Video video = null;
					int videoIndex = 0;
					lineData = line.split(" ");
					for (String videoSize : lineData) {
						video = new Video(videoIndex, Integer.valueOf(videoSize));
						videosAsList.add(video);
						videos.put(video.getId(), video);
						videoIndex += 1;
					}
				}
				
				// Lecture d'un endpoint
				if (index > 1 && !inEndpoint && currentEndpointIndex < (maxEndpoint - 1)) {
					currentEndpointIndex +=1;
					//System.out.println("Lecture du Endpoint : " + currentEndpointIndex);
					
					lineData = line.split(" ");
					
					currentEndpoint = new Endpoint();
					currentEndpoint.setId(currentEndpointIndex);
					currentEndpoint.setDatacenterLatency(Integer.valueOf(lineData[0]));
					currentEndpointCacheCount = Integer.valueOf(lineData[1]);
					endpointsAsList.add(currentEndpoint);
					endpoints.put(currentEndpoint.getId(), currentEndpoint);
					//System.out.println("Latence vers datacenter : " + currentEndpoint.getDatacenterLatency() + ", Nombre de cache pour ce endpoint : " + currentEndpointCacheCount);
					
					if (currentEndpointCacheCount > 0) {
						inEndpoint = true;
					}
					else if (currentEndpointIndex == (maxEndpoint - 1)) {
                        //System.out.println("Fin de la lecture des " + maxEndpoint + " Endpoints!");
                        inRequest = true;
                    }
					
				} else
				
				// Lecture des caches reliés au endpoints
				if (inEndpoint) {
					currentCacheIndex += 1;
					lineData = line.split(" ");
					currentCache = new Cache(Integer.valueOf(lineData[0]), fileData.getCacheSize(), Integer.valueOf(lineData[1]));
					currentEndpoint.addCache(currentCache);
					//caches.put(currentCache.getId(), currentCache);
					cachesAsList.add(currentCache);
					
					//System.out.println("Current cache index : " + currentCacheIndex + ",currentEndpointCacheCount : " + currentEndpointCacheCount + ", current cache latency : " + currentCache.getLatency());
					if (currentCacheIndex == currentEndpointCacheCount - 1) {
						inEndpoint = false;
						currentCacheIndex = -1;
						//System.out.println("Fin de la lecture du Endpoint : " + currentEndpointIndex);
						if (currentEndpointIndex == (maxEndpoint - 1)) {
							//System.out.println("Fin de la lecture des " + maxEndpoint + " Endpoints!");
							inRequest = true;
						}
					}
				} else if (inRequest) {
					// Lecture des requêtes
					
					//System.out.println("Lecture de la requête");
					//System.out.println(line);
					lineData = line.split(" ");
					
					currentRequest = new Request(Integer.valueOf(lineData[2]), videos.get(Integer.valueOf(lineData[0])), endpoints.get(Integer.valueOf(lineData[1])));
					requests.add(currentRequest);
				}

				index += 1;
			}
			
			fileData.setCaches(cachesAsList);
			fileData.setRequests(requests);
			fileData.setEndpoints(endpointsAsList);
			fileData.setVideos(videosAsList);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileData;
	}
}
