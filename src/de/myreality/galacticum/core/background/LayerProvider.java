package de.myreality.galacticum.core.background;

import de.myreality.galacticum.core.Boundable;
import de.myreality.galacticum.util.Background;

/**
 * Parallax mapper to manage layers
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public interface LayerProvider extends Background {

    /**
     * Adds a new layer
     */
    void addLayer(ParallaxLayer layer);

    /**
     * @return number of layers
     */
    int getLayerCount();
    
    Boundable getBoundable();
    
    void setBoundable(Boundable boundable);
}
