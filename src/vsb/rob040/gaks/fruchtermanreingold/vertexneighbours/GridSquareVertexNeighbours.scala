package vsb.rob040.gaks.fruchtermanreingold.vertexneighbours

import scala.collection.mutable.HashSet
import vsb.graphinterfaces.Graph
import vsb.graphinterfaces.Vertex

class GridSquareVertexNeighbours(graph: Graph, width: Int, height: Int, boxSize: Int) extends VertexNeighbours {
	
	val radius2: Long = boxSize * boxSize
	var boxes: Array[Array[HashSet[Vertex]]] = null
	
	{
		val xBoxesCount = (width / boxSize) + 1
		val yBoxesCount = (height / boxSize) + 1
		
		boxes = Array.ofDim[HashSet[Vertex]](xBoxesCount, yBoxesCount)
		
		for(x <- 0 to xBoxesCount - 1) {
			for(y <- 0 to yBoxesCount - 1) {
				boxes(x)(y) = new HashSet[Vertex]
			}
		}
	}
	
	def beforeIteration = {
		boxes.foreach(y => y.foreach(box => box.clear))
		graph.getVertices.foreach(vertex => boxes(vertex.getMetadata.getX / boxSize)(vertex.getMetadata.getY / boxSize).add(vertex))
	}

	def forNeighbours(vertex: Vertex, callback: Vertex => Unit) = {
		val x = vertex.getMetadata.getX / boxSize
		val y = vertex.getMetadata.getY / boxSize
		for(i <- math.max(0, x-1) to math.min(boxes.size-1, x+1))
			for(j <- math.max(0, y-1) to math.min(boxes(i).size-1, y+1))
				boxes(i)(j).foreach(v => if(areCloseEnough(vertex, v)) callback(v))
	}
	
	def areCloseEnough(v: Vertex, u: Vertex): Boolean = {
		val x = v.getMetadata.getX - u.getMetadata.getX
		val y = v.getMetadata.getY - u.getMetadata.getY
		return (x*x + y*y) < radius2
	}
	
}
