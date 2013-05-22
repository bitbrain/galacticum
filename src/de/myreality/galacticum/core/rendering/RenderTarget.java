package de.myreality.galacticum.core.rendering;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Basic render target that can be rendered by a renderer
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface RenderTarget {

	void beforeRender();

	void computeRendering(GameContainer gc, Graphics g);

	void afterRender();

	boolean hasRendered();
}
