package vsb.graphinterfaces

trait Edge {
	
	def getId: Long
	def getMetadata: Metadata
	def getVertexA: Vertex
	def getVertexB: Vertex
	def contains(vertex: Vertex): Boolean

}
