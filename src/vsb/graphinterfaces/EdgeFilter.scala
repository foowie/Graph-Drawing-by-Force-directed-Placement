package vsb.graphinterfaces

trait EdgeFilter {
	
	def isFiltered(edge: Edge): Boolean

}
