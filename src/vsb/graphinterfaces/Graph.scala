package vsb.graphinterfaces

trait Graph {

	def addVertex(vertex: Vertex)
	def addEdge(vertexA: Vertex, vertexB: Vertex)
	def getVertices: scala.collection.Set[Vertex]
	def getEdges: scala.collection.Set[Edge]
	def getVerticesBy(filter: VertexFilter): scala.collection.Set[Vertex]
	def getEdgesBy(filter: EdgeFilter): scala.collection.Set[Edge]
	
}
