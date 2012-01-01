package vsb.rob040.gaks.fruchtermanreingold.vertexneighbours

import scala.collection.mutable.HashSet
import vsb.graphinterfaces.Graph
import vsb.graphinterfaces.Vertex

/**
 * Get all vertices that are close enough
 * Use square-grid algorithm
 */
class GridSquareVertexNeighbours(graph: Graph, width: Int, height: Int, boxSize: Int) extends VertexNeighbours {
	
	private val radius2: Long = boxSize * boxSize
	private var boxes: Array[Array[HashSet[Vertex]]] = null
	
	{ // constructor
		// compute dimensons
		val xBoxesCount = (width / boxSize) + 1
		val yBoxesCount = (height / boxSize) + 1
		
		// create boxes
		boxes = Array.ofDim[HashSet[Vertex]](xBoxesCount, yBoxesCount)
		
		// initialize boxes
		for(x <- 0 to xBoxesCount - 1)
			for(y <- 0 to yBoxesCount - 1)
				boxes(x)(y) = new HashSet[Vertex]
	}
	
	/**
	 * Is called before iteration
	 * Initializes vertices into boxes
	 */
	def beforeIteration = {
		boxes.foreach(y => y.foreach(box => box.clear))
		graph.getVertices.foreach(vertex => 
			boxes(vertex.getMetadata.getX / boxSize)(vertex.getMetadata.getY / boxSize).add(vertex)
		)
	}

	/**
	 * Use callback on all vertices that are close enough to given vertex
	 */
	def forNeighbours(vertex: Vertex, callback: Vertex => Unit) = {
		val x = vertex.getMetadata.getX / boxSize
		val y = vertex.getMetadata.getY / boxSize
		// iterate through nearest squares
		for(i <- math.max(0, x-1) to math.min(boxes.size-1, x+1))
			for(j <- math.max(0, y-1) to math.min(boxes(i).size-1, y+1))
				boxes(i)(j).foreach(v => if(areCloseEnough(vertex, v)) callback(v))
	}
	
	/**
	 * Return true, if given vertices are closer then box size
	 */
	private def areCloseEnough(v: Vertex, u: Vertex): Boolean = {
		val x = v.getMetadata.getX - u.getMetadata.getX
		val y = v.getMetadata.getY - u.getMetadata.getY
		return (x*x + y*y) <= radius2
	}
	
}
