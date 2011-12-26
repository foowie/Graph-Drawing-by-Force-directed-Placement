package vsb.graphinterfaces

trait Vertex {
	
	def getId: Long
	def addEdge(edge: Edge)
	def getMetadata: Metadata
	def getEdges: Set[Edge]
	def getEdgesBy(filter: EdgeFilter): Set[Edge]
	def getAdjacentVertices: Set[Vertex]
	def getAdjacentVertices(filter: VertexFilter): Set[Vertex]

}
