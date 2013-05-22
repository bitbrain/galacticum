package de.myreality.dev.galacticum.game.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.myreality.galacticum.util.AbstractNode;
import de.myreality.galacticum.util.Node;

/**
 * Test case for the node implementation 
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 0.1dev
 * @version 0.1dev
 */
public class NodeTest {
	
	private StringNode node1, node2, node3;

	@Before
	public void setUp() throws Exception {
		node1 = new StringNode("Hallo");
		node2 = new StringNode("Welt");
		node3 = new StringNode("!");
	}

	@After
	public void tearDown() throws Exception {
	}

	// ===========================================================
	// Tests
	// ===========================================================
	
	@Test
	public void testAddParent() {
		node1.addParent(node2);
		node2.addParent(node3);		
		assertTrue("node2 has to be parent of node1", node1.hasParent(node2));
		assertTrue("node3 has to be parent of node2", node2.hasParent(node3));
	}
	
	@Test
	public void testRemoveParent() {
		node2.addParent(node3);	
		
		assertTrue("node3 should not be a parent anymore.", node2.hasParent(node3));
		
		node2.removeParent(node3);
		
		assertFalse("node3 should not be a parent anymore.", node2.hasParent(node3));
		assertFalse("node2 should not have any parent", node2.hasParent());
	}
	
	@Test
	public void testHasParent() {
		node1.addParent(node2);
		assertTrue("node1 should have a parent.", node1.hasParent());
	}
	
	@Test
	public void testAddChild() {
		node2.addChild(node1);
		node2.addChild(node2);
		assertTrue("node 2 should not have itself as child", node2.getNumberOfChildren() == 1);
		assertTrue("node 2 should be parent of node1", node1.hasParent(node2));
		assertTrue("node 2 should have node1 as child", node2.hasChild(node1));		
	}
	
	
	@Test
	public void testRemoveChild() {
		node3.addChild(node2);
		node3.addChild(node1);
		node2.addChild(node1);
		
		assertTrue("node1 should be a child of node3", node3.hasChild(node1));
		assertTrue("node1 should have node3 as parent.", node1.hasParent(node3));
		assertTrue("node3 should have only 2 children", node3.getNumberOfChildren() == 2);
		node3.removeChild(node1);
		
		assertFalse("node1 should not be a child of node3 anymore.", node3.hasChild(node1));
		assertFalse("node1 should not have node3 as parent anymore.", node1.hasParent(node3));
		assertTrue("node3 should have only 1 child", node3.getNumberOfChildren() == 1);
		
	}

	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	class StringNode extends AbstractNode<StringNode> implements Node<StringNode> {
		
		String value;
		
		public StringNode(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			String s = "";
			if (hasChildren()) {
				for (StringNode p : getChildren()) {
					s += p.toString();
				}
			}
			return s + value;
		}
	}
}
