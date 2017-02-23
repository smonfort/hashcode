/***********************************************************************************************
 * hashcode
 * (C)opyright 2013 Ministère de l'Intérieur - Tous droits réservés.
 * 
 * Code réalisé par Sopra Group.
 *
 * @date Feb 23, 2017 @author jdecure -  Video.java
 ***********************************************************************************************/
package com.soprasteria.hashcode.dto;

/**
 * @author jdecure
 *
 */
public class Video {

	private int id;
	private int size;
	
	
	public Video(int id, int size) {
		this.id = id;
		this.size = size;
	}
	
	public boolean equals(Object other)
    {
        return other instanceof Video && ((Video) other).getId() == getId();
    }

    public int hashCode()
    {
        return getId();
    }
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
}
