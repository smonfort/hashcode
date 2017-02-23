/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  Cache.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.dto;

/**
 * @author jdecure
 */
public class Cache
{

    private int id;

    private int size;

    private int latency;

    public Cache(int id, int size, int latency)
    {
        this.id = id;
        this.size = size;
        this.latency = latency;
    }

    public boolean equals(Object other)
    {
        return other instanceof Cache && ((Cache) other).getId() == getId();
    }

    public int hashCode()
    {
        return getId();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size)
    {
        this.size = size;
    }

    /**
     * @return the size
     */
    public int getSize()
    {
        return size;
    }

    /**
     * @return the latency
     */
    public int getLatency()
    {
        return latency;
    }

    /**
     * @param latency the latency to set
     */
    public void setLatency(int latency)
    {
        this.latency = latency;
    }
}
