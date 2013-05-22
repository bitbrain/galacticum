package de.myreality.galacticum.util;

import java.io.Serializable;
import java.util.Locale;

import de.myreality.galacticum.exceptions.LanguageNotSupportedException;

/**
 * Language class for languages
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * 
 */
public class Language implements Serializable {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = -4634941232823538467L;

	// ===========================================================
	// Fields
	// ===========================================================

	// Language code
	private Locale langCode;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Language() {

	}

	public Language(Locale locale) throws LanguageNotSupportedException {
		this.langCode = locale;
		mapCode(langCode);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public Locale getLocale() {
		return langCode;
	}

	public String getLangName() {
		try {
			return mapCode(langCode);
		} catch (LanguageNotSupportedException e) {
			return e.getMessage();
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public String toString() {
		return getLangName();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private String mapCode(Locale code) throws LanguageNotSupportedException {
		if (code.equals(Locale.ENGLISH)) {
			return "English";
		}

		if (code.equals(Locale.GERMAN)) {
			return "German";
		}

		if (code.toString().equals("nl_NL")) {
			return "Dutch";
		}

		throw new LanguageNotSupportedException(code + " is not supported.");
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}