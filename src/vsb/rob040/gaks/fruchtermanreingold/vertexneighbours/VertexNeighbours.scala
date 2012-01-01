package vsb.rob040.gaks.fruchtermanreingold.vertexneighbours

import vsb.graphinterfaces.Vertex

/**
 * Get all vertices that are close enough
 */
trait VertexNeighbours {
	
	/**
	 * Is called before iteration
	 */
	def beforeIteration
	
	/**
	 * Use callback on all vertices that are close enough to given vertex
	 */
	def forNeighbours(vertex: Vertex, callback: Vertex => Unit)
		
}