package vsb.rob040.gaks.fruchtermanreingold.source

import java.io.StringReader
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import java.io.Reader

class GraphSourceReaderTest {
	
	val triangle = "1 3\n1 2\n2 3\n1 3"
	var triangleGraphSource: GraphSource = null
	
	@Before def setUp = {
		triangleGraphSource = new GraphSourceReader(new StringReader(triangle))
	}
	
	
	@Test(expected=classOf[NullPointerException])
	def testConstructorIsNull = {
		new GraphSourceReader(null.asInstanceOf[Reader])
		fail
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testConstructorInvalidInputCharacters = {
		new GraphSourceReader(new StringReader("a b c"))
		fail
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testConstructorInvalidInputMissingFrom = {
		new GraphSourceReader(new StringReader(""))
		fail
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testConstructorInvalidInputMissingTo = {
		new GraphSourceReader(new StringReader("1"))
		fail
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testConstructorInvalidInputMissingEdgeTo = {
		new GraphSourceReader(new StringReader("1 2\n1"))
		fail
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testConstructorInvalidInputInvalidEdgeVertex = {
		new GraphSourceReader(new StringReader("1 2\n6 8"))
		fail
	}
	
	@Test def testGetVertices = {
		val vertices = triangleGraphSource.getVertices
		assertEquals(3, vertices.size)
		assertTrue(vertices.contains(1))
		assertTrue(vertices.contains(2))
		assertTrue(vertices.contains(3))
	}
	
	@Test def testGetEdges = {
		val edges = triangleGraphSource.getEdges
		assertEquals(3, edges.size)
		assertTrue(edges.contains(new NodePair(1, 2)))
		assertTrue(edges.contains(new NodePair(1, 3)))
		assertTrue(edges.contains(new NodePair(2, 3)))
	}
	
}

