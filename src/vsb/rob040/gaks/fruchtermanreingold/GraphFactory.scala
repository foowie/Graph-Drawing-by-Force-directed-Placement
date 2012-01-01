package vsb.rob040.gaks.fruchtermanreingold

import vsb.graphinterfaces.Graph
import vsb.rob040.gaks.fruchtermanreingold.source.GraphSource

/**
 * Create implementation of graph from graph source
 */
class GraphFactory(graphSource: GraphSource) {
	
	{ // constructor
		if(graphSource == null)
			throw new NullPointerException("GraphSource can't be null !")
	}
	
	/**
	 * Create implementation of graph from graph source
	 */
	def createGraph: Graph = {
		val graph = new GraphImpl
		if(graphSource == null)
			throw new NullPointerException("GraphSource cant be null")
		
		// generate all vertices
		graphSource.getVertices.foreach(id => graph.addVertex(new VertexImpl(id)))
		
		// generate all edges
		val vertices = graph.getVerticesMap
		graphSource.getEdges.foreach(edge => {
				val vertex1 = vertices.get(edge.getValue1)
				val vertex2 = vertices.get(edge.getValue2)
				if(vertex1 == None || vertex2 == None)
					throw new IllegalStateException("Vertices not found " + edge.getValue1 + "/" + edge.getValue2)
				graph.addEdge(vertex1.get, vertex2.get)
			})
		return graph
	}
}
