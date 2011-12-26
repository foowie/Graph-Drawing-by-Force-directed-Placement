package vsb.rob040.gaks.fruchtermanreingold

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import vsb.graphinterfaces._

class EdgeImplTest {
	
	var edge: Edge = null
	
	@Before def setUp = {
		edge = new EdgeImpl(5, new VertexImpl(1), new VertexImpl(2))
	}
	
	@Test def testGetId = assertEquals(5, edge.getId)
	
	@Test(expected=classOf[NullPointerException])
	def testConstructorVertexANull = {
		val temp = new EdgeImpl(5, null, new VertexImpl(2))
	}
	
	@Test(expected=classOf[NullPointerException])
	def testConstructorVertexBNull = {
		val temp = new EdgeImpl(5, new VertexImpl(1), null)
	}
	
	@Test(expected=classOf[IllegalStateException])
	def testConstructorVertexesAreSame = {
		val temp = new EdgeImpl(5, new VertexImpl(1), new VertexImpl(1))
	}
	
	@Test def testGetVertexA = assertEquals(1, edge.getVertexA.getId)
	
	@Test def testGetVertexB = assertEquals(2, edge.getVertexB.getId)
	
	@Test def testContainsA = assertTrue(edge.contains(new VertexImpl(1)))
	
	@Test def testContainsB = assertTrue(edge.contains(new VertexImpl(2)))
	
	@Test def testContainsNot = assertFalse(edge.contains(new VertexImpl(5)))
	
	@Test def testMetadata = assertTrue(edge.getMetadata.isInstanceOf[Metadata])
	
}
