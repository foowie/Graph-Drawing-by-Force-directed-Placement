package vsb.rob040.gaks.fruchtermanreingold

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import vsb.graphinterfaces._

class VertexImplTest {
	
	var vertex: Vertex = null
	
	var vertexA: Vertex = null
	var vertexB: Vertex = null
	var vertexC: Vertex = null
	
	var vertexFilter: VertexFilter = null
	var edgeFilter: EdgeFilter = null
	
	@Before def setUp = {
		vertex = new VertexImpl(1)
		vertexA = new VertexImpl(2)
		vertexB = new VertexImpl(3)
		vertexC = new VertexImpl(4)
		vertexFilter = new VertexFilterHelper
		edgeFilter = new EdgeFilterHelper
	}
	
	@Test def testGetId = assertEquals(1, vertex.getId)
	
	@Test def testMetadata = assertTrue(vertex.getMetadata.isInstanceOf[Metadata])
	
	@Test def testAddEdge = {
		vertex.addEdge(new EdgeImpl(1, vertex, vertexA))
	}
	
	@Test(expected=classOf[NullPointerException])
	def testAddEdgeNull = {
		vertex.addEdge(null)
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testAddEdgeExists = {
		vertex.addEdge(new EdgeImpl(1, vertex, vertexA))
		vertex.addEdge(new EdgeImpl(1, vertex, vertexA))
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testAddEdgeNotContainsThisVertex = {
		vertex.addEdge(new EdgeImpl(1, vertexA, vertexB))
	}
	
	@Test def testGetEdge = {
		val edges = List(new EdgeImpl(1, vertex, vertexA), new EdgeImpl(2, vertexB, vertex))
		edges.foreach((x) => vertex.addEdge(x))

		val vertexEdges = vertex.getEdges
		assertEquals(2, vertexEdges.size)
		edges.foreach((x) => assertTrue(vertexEdges.contains(x)))
	}
	
	@Test def testgetAdjacentVertices = {
		val edges = List(new EdgeImpl(1, vertex, vertexA), new EdgeImpl(2, vertex, vertexB))
		edges.foreach((x) => vertex.addEdge(x))

		val vertexes = vertex.getAdjacentVertices
		val expected = List(vertexA, vertexB)
		
		assertEquals(2, vertexes.size)
		expected.foreach((x) => assertTrue(vertexes.contains(x)))
	}
	
	@Test(expected=classOf[NullPointerException])
	def testGetEdgeByNull = {
		vertex.getEdgesBy(null)
		fail
	}
	
	@Test def testGetEdgeByEmpty = { 
		val edges = vertex.getEdgesBy(edgeFilter)
		assertNotNull(edges)
		assertEquals(0, edges.size)
	}
	
	@Test def testGetEdgeBy = {
		vertex.addEdge(new EdgeImpl(6, vertex, vertexB))
		vertex.addEdge(new EdgeImpl(1, vertex, vertexA))
		vertex.addEdge(new EdgeImpl(5, vertex, vertexC))
		val edges = vertex.getEdgesBy(edgeFilter)
		assertNotNull(edges)
		assertEquals(1, edges.size)
		assertEquals(new EdgeImpl(1, vertex, vertexA), edges.last)
	}
	
	@Test(expected=classOf[NullPointerException])
	def testGetAdjacentVerticesNull = {
		vertex.getAdjacentVertices(null.asInstanceOf[VertexFilter])
		fail
	}
	
	@Test def testGetAdjacentVerticesEmpty = { 
		val vs = vertex.getAdjacentVertices(vertexFilter)
		assertNotNull(vs)
		assertEquals(0, vs.size)
	}
	
	@Test def testGetAdjacentVertices = {
		vertex.addEdge(new EdgeImpl(6, vertex, vertexB))
		vertex.addEdge(new EdgeImpl(1, vertex, vertexA))
		vertex.addEdge(new EdgeImpl(5, vertex, vertexC))
		val vs = vertex.getAdjacentVertices(vertexFilter)
		assertNotNull(vs)
		assertEquals(1, vs.size)
		assertEquals(vertexA, vs.last)
	}
	
	
}

class VertexFilterHelper extends VertexFilter {
	def isFiltered(vertex: Vertex): Boolean = vertex.getId < 3
}

class EdgeFilterHelper extends EdgeFilter {
	def isFiltered(edge: Edge): Boolean = edge.getId < 5 
}
