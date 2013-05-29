package com._500bottles.object.winebook;

import java.util.Date;
import java.util.Vector;
import com._500bottles.object.wine.Wine;
import org.json.simple.JSONArray;

/**
 * Winebook Entry object representing an individual entry in the user's
 * Winebook journal.
 */
public class Entry {
	private long entryId;
	private long userId;
	private String title;
	private String content;
	private Vector<Photo> photos;
	private Vector<Wine> wines;
	private Date dateCreated;
	private Date dateLastEdited;

	/**
	 * Default Entry constructor. Initializes entry object to minimum values.
	 */
	public Entry()
	{
		entryId = 0;
		content = "";
		photos = new Vector<Photo>();
		wines = new Vector<Wine>();
		dateCreated = new Date();
		dateLastEdited = new Date();
	}

	/**
	 * Entry constructor including only entry ID and content.
	 * @param entryId		Entry ID.
	 * @param content		Textual content for this entry.
	 * @param dateCreated	Date entry was created.
	 * @param dateLastEdited		Date of last entry edit.
	 */
	public Entry(long entryId, String title, String content,
				 Date dateCreated, Date dateLastEdited)
	{
		this.entryId = entryId;
		this.title = title;
		this.content = content;
		this.photos = new Vector<Photo>();
		this.wines = new Vector<Wine>();
		this.dateCreated = dateCreated;
		this.dateLastEdited = dateLastEdited;
	}

	/**
	 * Entry constructor including entry ID, content, photos, and wines.
	 * @param entryId		ID of entry.
	 * @param content		Textual content for this entry.
	 * @param photos		Vector of Photo objects.
	 * @param wines			Vector of Wine objects.
	 * @param dateCreated	Date entry created.
	 * @param dateLastEdited		Date of last entry edit.
	 */
	public Entry(long entryId, String title, String content, Vector<Photo> photos,
				 Vector<Wine> wines, Date dateCreated, Date dateLastEdited)
	{
		this.entryId = entryId;
		this.title = title;
		this.content = content;
		this.photos = photos;
		this.wines = wines;
		this.dateCreated = dateCreated;
		this.dateLastEdited = dateLastEdited;
	}

	/**
	 * Returns this entries ID.
	 * @return	ID of this entry.
	 */
	public long getEntryId()
	{
		return entryId;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	/**
	 * Returns the title of this entry.
	 * @return	The title of this entry.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the date this entry was created.
	 * @return	Date object for the date this entry was created.
	 */
	public Date getDateCreated()
	{
		return dateCreated;
	}

	/**
	 * Returns the date this entry was last edited.
	 * @return	Date object for the date this entry was last edited.
	 */
	public Date getDateLastEdited()
	{
		return dateLastEdited;
	}

	/**
	 * Returns this entry's textual content.
	 * @return	This entries textual content.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * Returns array of this entry's Photo objects.
	 * @return	Array of Photo objects.
	 */
	public Photo[] getArrayOfPhotos()
	{
		return (Photo[]) photos.toArray();
	}

	/**
	 * Returns this entry's collection of Photo objects as a
	 * JSONArray object which contains only the IDs of the associated
	 * Photo objects. For use in database storage.
	 * @return	JSONArray object of this entry's photos.
	 */
	public JSONArray getPhotoIdsAsJSONArray()
	{
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < photos.size(); i++)
			jsonArray.add(photos.get(i).getId());

		return jsonArray;
	}

	/**
	 * Returns this entry's collection of Wine objects as a JSONArray object
	 * which contains only the IDs of the associated Wine objects. For use
	 * in database storage.
	 * @return	JSONArray object of this entry's photos.
	 */
	public JSONArray getWineIdsAsJSONArray()
	{
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < photos.size(); i++)
			jsonArray.add(photos.get(i).getId());

		return jsonArray;
	}

	/**
	 * Sets the ID of this entry.
	 * @param entryId	The ID of this entry.
	 */
	public void setEntryId(long entryId)
	{
		this.entryId = entryId;
	}

	/**
	 * Sets the title of this entry.
	 * @param title	The entry title.
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Sets the textual content of this entry.
	 * @param content	Textual content to add to this entry.
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * Sets the creation date of this entry.
	 * @param dateCreated	Date of entry creation.
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Sets the last edit date of this entry.
	 * @param dateLastEdited	Date of entry last edit.
	 */
	public void setDateLastEdited(Date dateLastEdited) {
		this.dateLastEdited = dateLastEdited;
	}

	/**
	 * Adds the specified photo to this entry if it does not already exist.
	 * @param photo	The photo to add to this entry.
	 */
	public void addPhoto(Photo photo)
	{
		if (!photos.contains(photo))
			photos.add(photo);
	}

	/**
	 * Adds photos to this entry from a JSONArray object. All existing photos
	 * associated with this entry object will be removed (if present).
	 * @param photos	JSONArray object of Photo objects.
	 */
	public void addPhotos(JSONArray photos)
	{
	}

	/**
	 * Removes the specified photo from this entry. Returns true if the entry
	 * contained the specified photo, otherwise false.
	 * @param photo	Photo to remove from this entry.
	 * @return		True if the photo was removed.
	 */
	public boolean removePhoto(Photo photo)
	{
		return photos.remove(photo);
	}

	/**
	 * Adds the specified wine to this entry if it does not already exist.
	 * @param wine	Wine to add to this entry.
	 */
	public void addWine(Wine wine)
	{
		if (!wines.contains(wine))
			wines.add(wine);
	}

	/**
	 * Removes the specified wine from this entry's wines. Returns true if the
	 * entry contained the specified wine, or false otherwise.
	 * @param wine	Wine object representing wine to be removed.
	 * @return		True if the wine was removed.
	 */
	public boolean removeWine(Wine wine)
	{
		return wines.remove(wine);
	}
}