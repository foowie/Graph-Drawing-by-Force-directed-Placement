package vsb.rob040.gaks.fruchtermanreingold

import scala.collection.mutable.HashSet
import vsb.graphinterfaces._
import scala.collection.Set

/**
 * Single vertex of graph
 */
class VertexImpl(id: Long, metadata: Metadata = new MetadataImpl) extends Vertex {

	private val edges = new HashSet[Edge]
	
	def getId: Long = id
	
	def addEdge(edge: Edge) = {
		if(edge == null)
			throw new NullPointerException("Edge can't be null")
		if(!edge.contains(this))
			throw new IllegalStateException("When adding edge, it must contains current vertex !")
		if(edges.contains(edge))
			throw new IllegalStateException("Trying to add duplicite edge")
		edges.add(edge)
	}
	
	def getMetadata: Metadata = metadata
	
	def getEdges: Set[Edge] = edges
	
	def getEdgesBy(filter: EdgeFilter): Set[Edge] = {
		if(filter == null)
			throw new NullPointerException("Filter is null")
		return getEdges.filter((x) => filter.isFiltered(x))
	}
	
	def getAdjacentVertices: Set[Vertex] = {
		val res = new HashSet[Vertex]()
		edges.foreach((x) =>
			res.add(if(x.getVertexA == this) x.getVertexB else x.getVertexA)
		)
		return res
	}
	
	def getAdjacentVertices(filter: VertexFilter): Set[Vertex] = {
		if(filter == null)
			throw new NullPointerException("Filter is null")
		return getAdjacentVertices.filter((x) => filter.isFiltered(x))
	}	

	override def equals(obj: Any) = obj.isInstanceOf[Vertex] && obj.asInstanceOf[Vertex].getId == id
	
	override def hashCode = id.hashCode
	
}
