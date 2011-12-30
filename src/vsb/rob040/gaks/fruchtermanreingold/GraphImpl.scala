package vsb.rob040.gaks.fruchtermanreingold

import scala.collection.mutable.HashSet
import vsb.graphinterfaces._

class GraphImpl extends Graph {

	val vertices = new HashSet[Vertex]
	val edges = new HashSet[Edge]
	
	var edgeCounter = 0
	
	def addVertex(vertex: Vertex) = {
		if(vertex == null)
			throw new NullPointerException("Vertex can't be null")
		if(vertices.contains(vertex))
			throw new IllegalArgumentException("Vertex allready exists")
		vertices.add(vertex)
	}
	
	def addEdge(vertexA: Vertex, vertexB: Vertex) = {
		if(vertexA == null || vertexB == null)
			throw new NullPointerException("Vertexes can't be null")
		if(!vertices.contains(vertexA) || !vertices.contains(vertexB))
			throw new IllegalStateException("Can't add edge to vertexes that are not present in graph yet")
		val edge = new EdgeImpl(edgeCounter, vertexA, vertexB)
		vertexA.addEdge(edge)
		vertexB.addEdge(edge)
		edges.add(edge)
		edgeCounter += 1
	}
	
	def getVertices: scala.collection.Set[Vertex] = vertices
	
	def getVerticesMap: scala.collection.Map[Long, Vertex] = vertices.map(vertex => (vertex.getId, vertex)).toMap
	
	def getEdges: scala.collection.Set[Edge] = edges
	
	def getVerticesBy(filter: VertexFilter): scala.collection.Set[Vertex] = {
		if(filter == null)
			throw new NullPointerException("Filter can't be null")
		vertices.filter(x => filter.isFiltered(x))
	}
	
	def getEdgesBy(filter: EdgeFilter): scala.collection.Set[Edge] = {
		if(filter == null)
			throw new NullPointerException("Filter can't be null")
		edges.filter(x => filter.isFiltered(x))
	}
	
}
