package vsb.graphinterfaces

trait Graph {

	def addVertex(vertex: Vertex)
	def addEdge(vertexA: Vertex, vertexB: Vertex)
	def getVertices: Set[Vertex]
	def getEdges: Set[Edge]
	def getVerticesBy(filter: VertexFilter): Set[Vertex]
	def getEdgesBy(filter: EdgeFilter): Set[Edge]
	
}
