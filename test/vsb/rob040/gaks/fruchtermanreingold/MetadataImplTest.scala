package vsb.rob040.gaks.fruchtermanreingold

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import vsb.graphinterfaces._

class MetadataImplTest {
	
	var metadata: Metadata = null
	
	@Before def setUp = {
		metadata = new MetadataImpl()
	}
	
	@Test def testValueInt = {
		metadata.setValue("x", 5)
		assertEquals(5, metadata.getValue("x"))
	}
	
	@Test def testValueString = {
		metadata.setValue("y", "y")
		assertEquals("y", metadata.getValue("y"))
	}
	
	@Test def testValueMulti = {
		metadata.setValue("x", 1)
		metadata.setValue("y", "y")
		assertEquals(1, metadata.getValue("x"))
		assertEquals("y", metadata.getValue("y"))
	}
	
	@Test def testValueNull = {
		metadata.setValue("null", null)
		assertEquals(null, metadata.getValue("null"))
	}
	
	@Test def testValueNotPresent = {
		assertEquals(null, metadata.getValue("abc"))
	}
	
}
