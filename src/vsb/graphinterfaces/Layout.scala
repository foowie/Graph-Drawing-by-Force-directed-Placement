package vsb.graphinterfaces

/**
 * Layout represents executable part of graph, thats improve position of vertices
 */
trait Layout {

	def getWidth: Int
	def getHeight: Int
	def getGraph: Graph
	
	def run
	
}
