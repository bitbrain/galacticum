package de.myreality.galacticum.core.chunks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.myreality.dev.chronos.util.Point2f;

/**
 * Test all chunk utilities
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class ChunkUtilsTest {
	
	@Test
	public void testTranslateToIndex() {
		
		System.out.println(ChunkUtils.translateToIndex(1023));
		
		// Initialization
		Point2f index = new Point2f(0, 0);		
		
		Point2f outerPosition = new Point2f(-1, -1);
		Point2f innerPosition1 = new Point2f(ChunkSystem.CHUNK_SIZE - 1, ChunkSystem.CHUNK_SIZE - 1);
		Point2f innerPosition2 = new Point2f(ChunkSystem.CHUNK_SIZE / 2, ChunkSystem.CHUNK_SIZE / 2);
		assertFalse(outerPosition.x + " should be out of the box.", index.x == ChunkUtils.translateToIndex((int) outerPosition.x));
		assertFalse(outerPosition.y + " should be out of the box.", index.y == ChunkUtils.translateToIndex((int) outerPosition.y));
		assertTrue(innerPosition1.x + " should be in the box.", index.x == ChunkUtils.translateToIndex((int) innerPosition1.x));
		assertTrue(innerPosition1.y + " should be in the box.", index.y == ChunkUtils.translateToIndex((int) innerPosition1.y));
		assertTrue(innerPosition2.x + " should be in the box.", index.x == ChunkUtils.translateToIndex((int) innerPosition2.x));
		assertTrue(innerPosition2.y + " should be in the box.", index.y == ChunkUtils.translateToIndex((int) innerPosition2.y));
		
		
		
	}
}
