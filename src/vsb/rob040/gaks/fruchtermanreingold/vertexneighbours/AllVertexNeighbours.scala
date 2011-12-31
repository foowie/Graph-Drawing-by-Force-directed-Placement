package vsb.rob040.gaks.fruchtermanreingold.vertexneighbours

import vsb.graphinterfaces.Graph
import vsb.graphinterfaces.Vertex

class AllVertexNeighbours(graph: Graph) extends VertexNeighbours {

	def beforeIteration = Unit
	def forNeighbours(vertex: Vertex, callback: Vertex => Unit) = graph.getVertices.foreach(callback)
	
}