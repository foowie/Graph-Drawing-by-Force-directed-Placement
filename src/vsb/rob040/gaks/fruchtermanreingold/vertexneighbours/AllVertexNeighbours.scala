package vsb.rob040.gaks.fruchtermanreingold.vertexneighbours

import vsb.graphinterfaces.Graph
import vsb.graphinterfaces.Vertex

/**
 * Get all vertices
 */
class AllVertexNeighbours(graph: Graph) extends VertexNeighbours {

	/**
	 * Is called before iteration
	 */
	def beforeIteration = Unit
	
	/**
	 * Use callback on all vertices
	 */
	def forNeighbours(vertex: Vertex, callback: Vertex => Unit) = graph.getVertices.foreach(callback)
	
}