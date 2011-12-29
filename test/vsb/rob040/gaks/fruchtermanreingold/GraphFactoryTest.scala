package vsb.rob040.gaks.fruchtermanreingold

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import vsb.graphinterfaces._
import vsb.rob040.gaks.fruchtermanreingold.source.GraphSource
import vsb.rob040.gaks.fruchtermanreingold.source.NodePair

class GraphFactoryTest {
	
	var factory: GraphFactory = null
	
	@Before def setUp = {
		factory = new GraphFactory(new GraphSourceHelper)
	}
	
	
	@Test(expected=classOf[NullPointerException])
	def testConstructorVertexANull = {
		new GraphFactory(null)
		fail
	}
	
	@Test def testCreateGraph = {
		val graph = factory.createGraph
		assertTrue(graph.isInstanceOf[Graph])
		val edges = graph.getEdges
		assertEquals(3, edges.size)
		val vertices = graph.getVertices
		assertEquals(3, vertices.size)
	}
	
	private class GraphSourceHelper extends GraphSource {
		def getEdges: Set[NodePair] = Set(new NodePair(1, 2), new NodePair(1, 3), new NodePair(2, 3))
		def getVertices: Set[Int] = Set(1, 2, 3)
	}
	
}
