package com._500bottles.da.external.snooth;

import java.util.HashMap;

import com._500bottles.da.external.snooth.exception.InvalidLanguage;

/**
 * Created with IntelliJ IDEA. User: administrator Date: 5/22/13 Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Language
{
	private final static String DEFAULT_LANGUAGE = "";
	private static HashMap<String, String> languages;

	private String language;

	public Language()
	{
		this.language = DEFAULT_LANGUAGE;
	}

	public Language(String language) throws InvalidLanguage
	{
		this.setLanguage(language);
	}

	public void setLanguage(String language) throws InvalidLanguage
	{
		if (Language.languages == null)
			initLanguagesMap();

		if (Language.languages.get(language) == null)
			throw new InvalidLanguage();

		this.language = language;
	}

	public String getLanguage()
	{
		return this.toString();
	}

	@Override
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

	/*
	 * private String getLanguageName() { if (Language.languages == null)
	 * initLanguagesMap();
	 * 
	 * return Language.languages.get(language); }
	 */

	private static void initLanguagesMap()
	{
		languages = new HashMap<String, String>();
		languages.put("en", "English");
		languages.put("fr", "French");
		languages.put("de", "German");
		languages.put("it", "Italian");
		languages.put("pt", "Portuguese");
		languages.put("el", "Greek");
		languages.put("es", "Spanish");
	}
}
