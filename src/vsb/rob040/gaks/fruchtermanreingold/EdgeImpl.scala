package vsb.rob040.gaks.fruchtermanreingold

import vsb.graphinterfaces._;

/**
 * Implementation of edge between two vertices
 */
class EdgeImpl(id: Long, vertexA: Vertex, vertexB: Vertex, metadata: Metadata = new MetadataImpl()) extends Edge {

	{ // constructor
		if(vertexA == null || vertexB == null)
			throw new NullPointerException("Vertexes should not be null !")
		if(vertexA == vertexB)
			throw new IllegalStateException("Vertexes should not be same !")
	}
	
	def getId: Long = id
	
	def getMetadata: Metadata = metadata
	
	def getVertexA: Vertex = vertexA
	
	def getVertexB: Vertex = vertexB
	
	def contains(vertex: Vertex): Boolean = vertexA == vertex || vertexB == vertex
	
	override def equals(obj: Any) = obj.isInstanceOf[Edge] && obj.asInstanceOf[Edge].getId == id

	override def hashCode = id.hashCode

}
