package de.myreality.dev.controls.progressbar;

import java.util.Properties;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.xml.xpp3.Attributes;

/**
 * Progress bar element
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @version 1.0
 * @since 1.0
 */
public class ProgressBar implements Controller {
	
	// The XML progress bar element
	private Element progressText;
	private Element progressElement;
	private boolean scanning;
	private float progress;
	private static final String SCANNING_WIDTH = "25%";
	
	@Override
	public void bind(Nifty nifty, Screen screen, Element element,
			Properties parameter, Attributes controlDefinitionAttributes) {
		progressElement = element.findElementByName("element");
		progressText = element.findElementByName("text");
		String scanningString = controlDefinitionAttributes.getWithDefault("scanning", "false");
		
		if (scanningString.equals("true")) {
			setScanning(true);
		}
		
		setProgress(0);
	}

	@Override
	public void init(Properties parameter,
			Attributes controlDefinitionAttributes) {

	}
	
	public boolean isScanning() {
		return scanning;
	}
	
	public void setScanning(boolean scanning) {
		this.scanning = scanning;
		
		if (scanning) {
			applyScanning();
		} else {
			applyProgress();
		}
	}

	@Override
	public boolean inputEvent(NiftyInputEvent inputEvent) {
		return false;
	}

	@Override
	public void onFocus(boolean getFocus) {

	}
	
	public float getProgress() {
		return progress;
	}
	
	public void setProgress(float progress) {
		
		if (progress > 100f) {
			progress = 100f;
		}
		
		if (progress < 0f) {
			progress = 0f;
		}
		this.progress = progress;
		
		if (!scanning) {
			applyProgress();
		}
	}
	
	private void applyProgress() {
		String progressString = String.valueOf(roundDecimalToString(progress, 2)) + "%";
		progressElement.setConstraintWidth(new SizeValue(progressString));
		progressText.getRenderer(TextRenderer.class).setText(progressString);
		progressElement.getParent().getParent().layoutElements();
	}
	
	private void applyScanning() {
		progressText.getRenderer(TextRenderer.class).setText("");
		progressElement.setConstraintWidth(new SizeValue("0%"));
	}

	@Override
	public void onStartScreen() {

	}
	
	
	private String roundDecimalToString(float value, int floating) {
		String stringValue = String.valueOf(value);
		stringValue = removeCharsAt(stringValue, stringValue.lastIndexOf('.') + ++floating);
		return appendNull(stringValue, floating);
	}

	private String removeCharsAt(String s, int pos) {
		if (pos < s.length()) {
			return s.substring(0, pos);
		} else {
			return s;
		}
	}
	
	private String appendNull(String numberString, int floating) {
		
		int pointIndex = numberString.lastIndexOf('.');
		while (numberString.length() - pointIndex < floating) {
			numberString += "0";
		}
		
		return numberString;
	}

}
