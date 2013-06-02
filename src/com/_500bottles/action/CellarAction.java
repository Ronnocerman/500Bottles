package com._500bottles.action;

import com._500bottles.exception.cellar.CellarException;
import com._500bottles.manager.CellarManager;
import com._500bottles.object.cellar.CellarItem;
import com._500bottles.object.wine.Wine;

public class CellarAction
{
	/**
	 * Method to set the quantity of a particular wine in a user's cellar.
	 * 
	 * @param userID
	 *            The userID of the user whose cellar is being edited.
	 * @param wine
	 *            The wine whose quantity to edit.
	 * @param quantity
	 *            The quantity to which to set the wine in the cellar.
	 * @throws CellarException
	 *             if the user does not exist.
	 */
	public void setCellarQuantity(long userID, Wine wine, int quantity)
			throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, wine.getId());
		ci.setQuantity(quantity);
		CellarManager.editCellarItem(ci);
	}

	/**
	 * Increments the quantity of a particular wine in a user's cellar.
	 * 
	 * @param userID
	 *            The userID of the user whose cellar is being incremented.
	 * @param wine
	 *            The wine whose quantity to increment.
	 * @throws CellarException
	 *             if the user does not exist.
	 */
	public void incCellarQuantity(long userID, Wine wine)
			throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, wine.getId());
		ci.setQuantity(ci.getQuantity() + 1);
		CellarManager.editCellarItem(ci);
	}

	/**
	 * Decrements the quantity of a particular wine in a user's cellar.
	 * 
	 * @param userID
	 *            The userID of the user whose cellar is being incremented
	 * @param wine
	 *            The wine whose quantity to increment.
	 * @throws CellarException
	 *             if the user does not exist.
	 */
	public void decCellarQuantity(long userID, Wine wine)
			throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, wine.getId());
		ci.setQuantity(ci.getQuantity() - 1);
		CellarManager.editCellarItem(ci);
	}

	/**
	 * Sets the cellar notes of a user for a particular wine.
	 * 
	 * @param userID
	 *            The userID of the user for whom to add notes for a wine.
	 * @param wine
	 *            The wine for which to add notes.
	 * @param notes
	 *            The notes to add to the wine.
	 * @throws CellarException
	 *             if the user does not exist.
	 */
	public void setCellarNotes(long userID, Wine wine, String notes)
			throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, wine.getId());
		ci.setNotes(notes);
		CellarManager.editCellarItem(ci);
	}

	/**
	 * Gets the cellar notes of a user for a particular wine.
	 * 
	 * @param userID
	 *            The userID of the user whose wine notes are being retrieved.
	 * @param wine
	 *            The wine for which to retrieve notes.
	 * @return The notes for the wine by the user.
	 * @throws CellarException
	 *             if the user does not exist.
	 */
	public String getCellarNotes(long userID, Wine wine) throws CellarException
	{
		return CellarManager.getByWineID(userID, wine.getId()).getNotes();
	}

	/**
	 * Clears the cellar notes by a user for a particular wine.
	 * 
	 * @param userID
	 *            The userID of the user whose notes to clear
	 * @param wine
	 *            The wine whose notes to clear.
	 * @throws CellarException
	 *             if the user does not exist.
	 */
	public void clearCellarNotes(long userID, Wine wine) throws CellarException
	{
		CellarItem ci = CellarManager.getByWineID(userID, wine.getId());
		ci.setNotes("");
		CellarManager.editCellarItem(ci);
	}
}
