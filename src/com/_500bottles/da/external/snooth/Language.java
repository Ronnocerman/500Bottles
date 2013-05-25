package com._500bottles.da.external.snooth;

import com._500bottles.da.external.snooth.exception.InvalidLanguage;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 5/22/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Language
{
	private final static String DEFAULT_LANGUAGE = "";
	private static HashMap<String, String> languages;

	private String language;

	public Language ()
	{
		this.language = DEFAULT_LANGUAGE;
	}

	public void setLanguage(String language) throws InvalidLanguage
	{
		if (this.languages == null)
			initLanguagesMap();

		if (this.languages.get(language) == null)
			throw new InvalidLanguage();

		this.language = language;
	}

	public String getLanguage()
	{
		return this.toString();
	}

	public String toString()
	{
		return this.language;
	}

	public boolean equals(Language l)
	{
		if (l.getLanguage() == this.getLanguage())
			return true;

		return false;
	}

	private String getLanguageName()
	{
		if (this.languages == null)
			initLanguagesMap();

		return this.languages.get(language);
	}

	private static void initLanguagesMap()
	{
		languages = new HashMap<String, String>();
		languages.put("en","English");
		languages.put("fr","French");
		languages.put("de","German");
		languages.put("it","Italian");
		languages.put("pt","Portuguese");
		languages.put("el","Greek");
		languages.put("es","Spanish");
	}
}
