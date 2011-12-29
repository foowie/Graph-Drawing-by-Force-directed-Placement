package vsb.rob040.gaks.fruchtermanreingold

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import vsb.graphinterfaces._

class GraphImplTest {
	
	var graph: Graph = null
	
	var vertexA: Vertex = null
	var vertexB: Vertex = null
	var vertexC: Vertex = null
	
	@Before def setUp = {
		graph = new GraphImpl
		vertexA = new VertexImpl(1)
		vertexB = new VertexImpl(2)
		vertexC = new VertexImpl(3)
	}
	
	
	@Test(expected=classOf[NullPointerException])
	def testAddVertexNull = {
		graph.addVertex(null)
		fail
	}
	
	@Test def testVertex = {
		graph.addVertex(vertexA)
		graph.addVertex(vertexB)
		val vertices = graph.getVertices
		assertEquals(2, vertices.size)
		assertTrue(vertices.contains(vertexA))
		assertTrue(vertices.contains(vertexB))
	}
	
	@Test(expected=classOf[NullPointerException])
	def testGetVerticesByNull = {
		graph.getVerticesBy(null)
		fail
	}
	
	@Test def testGetVerticesBy = {
		graph.addVertex(vertexA)
		graph.addVertex(vertexB)
		graph.addVertex(vertexC)
		val vertices = graph.getVerticesBy(new VertexFilterHelper)
		assertEquals(2, vertices.size)
		assertTrue(vertices.contains(vertexA))
		assertTrue(vertices.contains(vertexB))
	}
	
	@Test(expected=classOf[NullPointerException])
	def testAddEdgeNull1 = {
		graph.addEdge(null, vertexB)
		fail
	}
	
	@Test(expected=classOf[NullPointerException])
	def testAddEdgeNull2 = {
		graph.addEdge(vertexA, null)
		fail
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testAddEdgeNoVertexes = {
		graph.addEdge(vertexA, vertexB)
		fail
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testAddEdgeDuplicitVertex = {
		graph.addVertex(vertexA)
		graph.addEdge(vertexA, vertexA)
		fail
	}
	
	@Test def testEdge = {
		graph.addVertex(vertexA)
		graph.addVertex(vertexB)
		graph.addEdge(vertexA, vertexB)
		assertEquals(1, vertexA.getEdges.size)
		assertEquals(1, vertexB.getEdges.size)
	}
	
	@Test def testAddGetEdge = {
		graph.addVertex(vertexA)
		graph.addVertex(vertexB)
		graph.addVertex(vertexC)
		graph.addEdge(vertexA, vertexB)
		graph.addEdge(vertexA, vertexC)
		val edges = graph.getEdges
		assertEquals(2, edges.size)
	}
	
	@Test(expected=classOf[NullPointerException])
	def testGetEdgesByNull = {
		graph.getEdgesBy(null)
		fail
	}
	
	@Test def testGetEdgesBy = {
		graph.addVertex(vertexA)
		graph.addVertex(vertexB)
		graph.addVertex(vertexC)
		graph.addEdge(vertexA, vertexB)
		graph.addEdge(vertexA, vertexC)
		val edges = graph.getEdgesBy(new EdgeFilterHelper(1))
		assertEquals(1, edges.size)
	}
	
}
