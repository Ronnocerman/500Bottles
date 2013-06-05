package com._500bottles.action;

import com._500bottles.exception.cellar.CellarException;
import com._500bottles.manager.CellarManager;
import com._500bottles.manager.SessionManager;
import com._500bottles.object.cellar.CellarItem;

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
	public static void setCellarQuantity(long id, int quantity) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
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
	public static void incCellarQuantity(long id) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
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
	public static void decCellarQuantity(long id) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
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
	public static void setCellarNotes(long id, String notes) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
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
	public static String getCellarNotes(long id) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		return CellarManager.getByWineID(userID, id).getNotes();
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
	public static void clearCellarNotes(long id) throws CellarException
	{
		long userID = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		CellarItem ci = CellarManager.getByWineID(userID, id);
		ci.setNotes("");
		CellarManager.editCellarItem(ci);
	}
}
