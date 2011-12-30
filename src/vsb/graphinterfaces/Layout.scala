package vsb.graphinterfaces

trait Layout {

	def getWidth: Int
	def getHeight: Int
	def getGraph: Graph
	
	def run
	
}
