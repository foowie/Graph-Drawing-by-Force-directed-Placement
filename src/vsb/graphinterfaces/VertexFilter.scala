package vsb.graphinterfaces

/**
 * Get part of vertices by given condition
 */
trait VertexFilter {

	def isFiltered(vertex: Vertex): Boolean
	
}
