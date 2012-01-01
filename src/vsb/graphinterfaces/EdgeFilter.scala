package vsb.graphinterfaces

/**
 * Get part of edges by given condition
 */
trait EdgeFilter {
	
	def isFiltered(edge: Edge): Boolean

}
