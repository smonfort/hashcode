package com.soprasteria.hashcode.algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.soprasteria.hashcode.dto.Cache;
import com.soprasteria.hashcode.dto.Endpoint;
import com.soprasteria.hashcode.dto.FileData;
import com.soprasteria.hashcode.dto.Request;
import com.soprasteria.hashcode.dto.Video;
import com.soprasteria.hashcode.dto.output.CacheSortie;
import com.soprasteria.hashcode.dto.output.SortieAlgorithme;

public class CacheScoreComputerCacheUnique extends AbstractAlgorithme
{
    private int DEFAULT_SIZE_VIDEO = 10000;

    private int DEFAULT_SIZE_CACHE = 10000;

    @Override
    public SortieAlgorithme optimiser(FileData entree)
    {
        Map<Endpoint, List<Request>> mapEndpointsRequests = new HashMap<Endpoint, List<Request>>(entree.getEndpoints().size());

        for (Request request : entree.getRequests())
        {
            associateRequestToEndpoint(request, mapEndpointsRequests);
        }

        Map<Cache, List<VideoInCache>> mapCacheRequests = new HashMap<Cache, List<VideoInCache>>(entree.getCaches().size());
        for (Entry<Endpoint, List<Request>> entry : mapEndpointsRequests.entrySet())
        {
            Endpoint enpoint = entry.getKey();
            for (Entry<Integer, Cache> entryCache : enpoint.getCaches().entrySet())
            {
                associateRequestToCache(entryCache.getValue(), enpoint.getCachesLatencies().get(entryCache.getKey()),
                    enpoint, entry.getValue(), mapCacheRequests);
            }
        }

        SortieAlgorithme resultat = new SortieAlgorithme();
        List<CacheSortie> listCacheSortie = new ArrayList<CacheSortie>(DEFAULT_SIZE_CACHE);
        resultat.setCaches(listCacheSortie);

        Map<Video, List<Cache>> videosDejaEnCache = new HashMap<Video, List<Cache>>(DEFAULT_SIZE_VIDEO);

        for (Entry<Cache, List<VideoInCache>> entryVideo : mapCacheRequests.entrySet())
        {
            Cache cache = entryVideo.getKey();

            List<Integer> listVid = calculterListePourCache(cache, entryVideo.getValue(), videosDejaEnCache);

            CacheSortie sortie = new CacheSortie();
            sortie.setIdCache(cache.getId());
            sortie.setIdsVideo(listVid);
            listCacheSortie.add(sortie);
        }

        return resultat;

    }

    protected void associateRequestToEndpoint(Request request, Map<Endpoint, List<Request>> mapEndpoints)
    {
        List<Request> listRequests = mapEndpoints.get(request.getEndpoint());
        if (listRequests == null)
        {
            listRequests = new ArrayList<Request>();
            mapEndpoints.put(request.getEndpoint(), listRequests);
        }
        listRequests.add(request);
    }

    protected void associateRequestToCache(Cache cache, Integer latenceCache, Endpoint enpoint, List<Request> listRequetes,
        Map<Cache, List<VideoInCache>> mapCacheVideo)
    {
        List<VideoInCache> listVid = mapCacheVideo.get(cache);

        if (listVid == null)
        {
            listVid = new ArrayList<VideoInCache>();
            mapCacheVideo.put(cache, listVid);
        }

        for (Request req : listRequetes)
        {
            VideoInCache vid = new VideoInCache();
            vid.setVideo(req.getVideo());
            vid.setCount(req.getCount());
            vid.setLatencyCache(latenceCache);
            vid.setLatencyDataCenter(enpoint.getDatacenterLatency());
            listVid.add(vid);
        }
    }

    protected List<Integer> calculterListePourCache(Cache cache, List<VideoInCache> listVideosInCache,
        Map<Video, List<Cache>> videosDejaEnCache)
    {
        List<VideoInCache> videoToRemove = new ArrayList<VideoInCache>();

        for (VideoInCache videoInCache : listVideosInCache)
        {
            List<Cache> listCache = videosDejaEnCache.get(videoInCache);
            if (listCache != null && listCache.contains(cache))
            {
                videoToRemove.add(videoInCache);
            }
        }

        listVideosInCache.removeAll(videoToRemove);

        Collections.sort(listVideosInCache, new Comparator<VideoInCache>()
        {
            /**
             * (methode de remplacement) {@inheritDoc}
             * 
             * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
             */
            public int compare(VideoInCache video1, VideoInCache video2)
            {
                long scoreVid1 = computeScoreVideo(video1);
                long scoreVid2 = computeScoreVideo(video2);
                if (scoreVid1 > scoreVid2)
                {
                    return 1;
                }
                else if (scoreVid1 == scoreVid2)
                {
                    return 0;
                }
                return -1;
            }
        });

        List<Integer> listVid = new ArrayList<Integer>();

        int tailleUtiliseDansCache = 0;
        for (VideoInCache video : listVideosInCache)
        {
            if (tailleUtiliseDansCache + video.getVideo().getSize() < cache.getSize() && !listVid.contains(video.getVideo().getId()))
            {
                listVid.add(video.getVideo().getId());
                tailleUtiliseDansCache += video.getVideo().getSize();

                List<Cache> listCache = videosDejaEnCache.get(video.getVideo());
                if (listCache == null)
                {
                    listCache = new ArrayList<Cache>();
                    videosDejaEnCache.put(video.getVideo(), listCache);
                }
                listCache.add(cache);
            }
        }

        return listVid;
    }

    protected long computeScoreVideo(VideoInCache video)
    {
        return video.getCount() * (video.latencyDataCenter - video.latencyCache) * video.getVideo().getSize();
    }

    public class VideoInCache
    {
        private Video video;

        private int latencyCache;

        private int latencyDataCenter;

        private int count;

        public Video getVideo()
        {
            return video;
        }

        public void setVideo(Video video)
        {
            this.video = video;
        }

        public long getLatencyCache()
        {
            return latencyCache;
        }

        public void setLatencyCache(int latencyCache)
        {
            this.latencyCache = latencyCache;
        }

        public long getLatencyDataCenter()
        {
            return latencyDataCenter;
        }

        public void setLatencyDataCenter(int latencyDataCenter)
        {
            this.latencyDataCenter = latencyDataCenter;
        }

        public int getCount()
        {
            return count;
        }

        public void setCount(int count)
        {
            this.count = count;
        }
    }

    public static void main(String[] args)
    {
      new CacheScoreComputerCacheUnique().run("D:/hashcode/kittens.in");
//      new CacheScoreComputer().run("D:/hashcode/me_at_the_zoo.in");
//    new CacheScoreComputer().run("D:/hashcode/trending_today.in");
//    new CacheScoreComputer().run("D:/hashcode/videos_worth_spreading.in");
//      new CacheScoreComputer().run("D:/hashcode/exemple.in");
    }
}
