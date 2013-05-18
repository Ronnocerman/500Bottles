package com._500bottles.object.winebook;

public class Photo
{

	private long photoId;
	private String filename;

	/**
	 * 
	 */
	public Photo()
	{

	}

	/**
	 * 
	 * @param photoId
	 * @param filename
	 */
	public Photo(long photoId, String filename)
	{
		this.setId(photoId);
		this.setFilename(filename);
	}

	/**
	 * 
	 * @return
	 */
	public long getId()
	{
		return photoId;
	}

	/**
	 * 
	 * @param photoId
	 */
	public void setId(long photoId)
	{
		this.photoId = photoId;
	}

	/**
	 * 
	 * @return
	 */
	public String getFilename()
	{
		return filename;
	}

	/**
	 * 
	 * @param filename
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}
