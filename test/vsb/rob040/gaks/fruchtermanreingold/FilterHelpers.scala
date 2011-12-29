package vsb.rob040.gaks.fruchtermanreingold

import vsb.graphinterfaces._

class VertexFilterHelper(allowLessThen: Int = 3) extends VertexFilter {
	def isFiltered(vertex: Vertex): Boolean = vertex.getId < allowLessThen
}

class EdgeFilterHelper(allowLessThen: Int = 5) extends EdgeFilter {
	def isFiltered(edge: Edge): Boolean = edge.getId < allowLessThen 
}