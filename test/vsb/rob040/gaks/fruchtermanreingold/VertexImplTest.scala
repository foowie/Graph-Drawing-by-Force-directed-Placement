package vsb.rob040.gaks.fruchtermanreingold

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import vsb.graphinterfaces._

class VertexImplTest {
	
	var vertex: Vertex = null
	
	@Before def setUp = {
		vertex = new VertexImpl(1)
	}
	
	@Test def testGetId = assertEquals(1, vertex.getId)
	
	@Test def testMetadata = assertTrue(vertex.getMetadata.isInstanceOf[Metadata])
	
}
