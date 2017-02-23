package com.soprasteria.hashcode.dto;

public class VideoInCache
{
    private Video video;

    private long latency;

    private int count;

    public Video getVideo()
    {
        return video;
    }

    public void setVideo(Video video)
    {
        this.video = video;
    }

    public long getLatency()
    {
        return latency;
    }

    public void setLatency(long latency)
    {
        this.latency = latency;
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
