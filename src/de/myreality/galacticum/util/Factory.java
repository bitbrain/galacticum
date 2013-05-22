package de.myreality.galacticum.util;

import java.io.Externalizable;

/**
 * Simple factory pattern
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * 
 * @param <T> Type of the factory
 */
public interface Factory<T> extends Externalizable {

    /**
     * Creates a new instance
     * 
     * @return A new instance of a produced object
     */
    T create();

    /**
     * @return Seed of the
     */
    Seed getSeed();
}
