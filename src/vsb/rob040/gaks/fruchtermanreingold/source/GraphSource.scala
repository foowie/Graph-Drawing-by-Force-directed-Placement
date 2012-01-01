package vsb.rob040.gaks.fruchtermanreingold.source

/**
 * Pair of vertices (numbers) representing edge
 */
class NodePair(val value1: Int, val value2: Int) {
	
	def getValue1 = value1
	def getValue2 = value2
	
	override def equals(obj: Any): Boolean = {
		if(!obj.isInstanceOf[NodePair])
			return false
		val other = obj.asInstanceOf[NodePair]
		return (other.value1 == value1 && other.value2 == value2) || (other.value2 == value1 && other.value1 == value2)
	}
	
	override def hashCode = value1.hashCode + value2.hashCode
}

/**
 * Create graph data from any source
 */
trait GraphSource {
	
	def getEdges: scala.collection.Set[NodePair]
	def getVertices: scala.collection.Set[Int]
	
}
