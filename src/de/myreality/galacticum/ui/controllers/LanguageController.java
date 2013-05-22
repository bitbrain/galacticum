package de.myreality.galacticum.ui.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.xml.xpp3.Attributes;
import de.myreality.galacticum.exceptions.LanguageNotSupportedException;
import de.myreality.galacticum.util.Language;

/**
 * Controls the language selection of a drop down
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class LanguageController implements Controller {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void bind(Nifty nifty, Screen screen, Element element, Properties parameter,
			Attributes controlDefinitionAttributes) {
		@SuppressWarnings("unchecked")
		DropDown<Language> resolutionDropDown = screen.findNiftyControl("drpLanguage",
				DropDown.class);
		resolutionDropDown.addAllItems(getAllLanguages(nifty));
	}

	@Override
	public void init(Properties parameter, Attributes controlDefinitionAttributes) {

	}

	@Override
	public boolean inputEvent(NiftyInputEvent inputEvent) {
		return false;
	}

	@Override
	public void onFocus(boolean getFocus) {

	}

	@Override
	public void onStartScreen() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	private List<Language> getAllLanguages(Nifty nifty) {
		List<Language> languages = new ArrayList<Language>();

		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			Language language;
			try {
				language = new Language(locale);
				languages.add(language);
			} catch (LanguageNotSupportedException e) {
				// e.printStackTrace();
			}

		}

		sortLanguages(languages);

		return languages;
	}

	/**
	 * Sorts a List of Languages alphabetically.
	 * 
	 * @param languages
	 *            The Language to sort.
	 * @return The given list, but then sorted.
	 */
	private List<Language> sortLanguages(List<Language> languages) {
		Collections.sort(languages, new Comparator<Language>() {
			public int compare(Language lang1, Language lang2) {
				return lang1.getLangName().compareTo(lang2.getLangName());
			};
		});
		return languages;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
