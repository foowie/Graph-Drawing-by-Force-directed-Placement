package vsb.rob040.gaks.fruchtermanreingold.vertexneighbours

import vsb.graphinterfaces.Vertex

trait VertexNeighbours {
	
	def beforeIteration
	def forNeighbours(vertex: Vertex, callback: Vertex => Unit)
		
}