package vsb.graphinterfaces

trait Vertex {
	
	def getId: Long
	def addEdge(edge: Edge)
	def getMetadata: Metadata
	def getEdges: scala.collection.Set[Edge]
	def getEdgesBy(filter: EdgeFilter): scala.collection.Set[Edge]
	def getAdjacentVertices: scala.collection.Set[Vertex]
	def getAdjacentVertices(filter: VertexFilter): scala.collection.Set[Vertex]

}
