/* Galacticum space game for Android, PC and browser.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.myreality.galacticum.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for annotation scanning and handling
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1
 * @version 0.1
 */
public final class AnnotationUtils {
	
	 // Returns all interfaces of the class
	public static Class<?>[] getAllInterfaces(Class<?> clazz) {
        return getAllInterfaces(new Class[]{clazz});
    }
	
	
	
	

    //This method walks up the inheritance hierarchy to make sure we get every class/interface that could
    //possibly contain the declaration of the annotated method we're looking for.
    private static Class<?>[] getAllInterfaces(Class<?>[] classes) {
        if (0 == classes.length) {
            return classes;
        } else {
            List<Class<?> > extendedClasses = new ArrayList<Class<?> >();
            for (Class<?> clazz : classes) {
                extendedClasses.addAll(Arrays.asList(clazz.getInterfaces()));
            }
            //Class::getInterfaces() gets only interfaces/classes implemented/extended directly by a given class.
            //We need to walk the whole way up the tree.
            return (Class[]) addAllClasses(classes,
                    getAllInterfaces(
                    extendedClasses.toArray(new Class[extendedClasses.size()])));
        }
    }

    // Adds all classes from array A to array B
    private static Object[] addAllClasses(Class<?>[] A, Class<?>[] B) {
        int aLen = A.length;
        int bLen = B.length;
        Object[] C = new Class[aLen + bLen];
        System.arraycopy(A, 0, C, 0, aLen);
        System.arraycopy(B, 0, C, aLen, bLen);
        return C;
    }

}
