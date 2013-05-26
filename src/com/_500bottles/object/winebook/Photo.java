package com._500bottles.object.winebook;

/**
 * Winebook Photo object representing an individual photo in the user's Winebook
 * entries.
 */
public class Photo
{

	private long photoId;
	private String filename;

	/**
	 * Default constructor. Initializes to default values.
	 */
	public Photo()
	{
		photoId = 0;
		filename = "";
	}

	/**
	 * Photo constructor including both photo ID and filename.
	 * @param photoId		Photo ID.
	 * @param filename		Filename.
	 */
	public Photo(long photoId, String filename)
	{
		this.setId(photoId);
		this.setFilename(filename);
	}

	/**
	 * Returns this photo ID.
	 * @return ID of this photo.
	 */
	public long getId()
	{
		return photoId;
	}

	/**
	 * Sets the ID of this photo.
	 * @param photoId	The ID of this photo.
	 */
	public void setId(long photoId)
	{
		this.photoId = photoId;
	}

	/**
	 * Returns this filename.
	 * @return Filename of this photo.
	 */
	public String getFilename()
	{
		return filename;
	}

	/**
	 * Sets the filename of this photo.
	 * @param filename	The filename of this photo
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}