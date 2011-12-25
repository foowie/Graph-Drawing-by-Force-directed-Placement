package vsb.rob040.gaks.fruchtermanreingold

import vsb.graphinterfaces._

class VertexImpl(id: Long, metadata: Metadata = new MetadataImpl) extends Vertex {

	def getId: Long = id
	
	def addEdge(edge: Edge) = null
	
	def getMetadata: Metadata = null
	
	def getEdges: java.util.Set[Edge] = null
	
	def getEdgesBy(filter: EdgeFilter): java.util.Set[Edge] = null
	
	def getAdjacentVertices: java.util.Set[Vertex] = null
	
	def getAdjacentVertices(filter: VertexFilter): java.util.Set[Vertex] = null	

	override def equals(obj: Any) = obj.isInstanceOf[Vertex] && obj.asInstanceOf[Vertex].getId == id
	
}
