package vsb.rob040.gaks.fruchtermanreingold

import scala.util.Random
import vsb.graphinterfaces._
import vsb.rob040.gaks.fruchtermanreingold.vertexneighbours._

class FruchtermanReingoldLayout(val width: Int, val height: Int, val graph: Graph, val iterations: Int) extends Layout {

	val area = width * height
	val k = 0.8 * math.sqrt(area / graph.getVertices.size)
	var temperature = 0.1 * math.sqrt(area)
	val epsilon = 0.0001D
	var currentIteration = 1
	var onLoop: (Int, Boolean) => Unit = null
	var vertexNeighbours: VertexNeighbours = new AllVertexNeighbours(graph)
	
	def getWidth: Int = width
	def getHeight: Int = height
	def getGraph: Graph = graph
	
	def shuffle = {
		val random = new Random
		graph.getVertices.foreach(vertex => {
				vertex.getMetadata.setX(random.nextInt(width))
				vertex.getMetadata.setY(random.nextInt(height))
			})
	}
	
	def run = {
		// shuffle vertices position
		shuffle
		
		// init metadata
		graph.getVertices.foreach(initMetadata)
	
		for(iteration <- 1 to iterations) {
			
			vertexNeighbours.beforeIteration
			
			// clear disp metadata
			graph.getVertices.foreach(vertex => vertex.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata].disp.clear)

			// repulsion
			graph.getVertices.foreach(calculateRepulsion)

			// attraction
			graph.getEdges.foreach(calculateAttraction)

			// position
			graph.getVertices.foreach(calculatePosition)
			
			// cool
			cool(iteration)
			
			// invoke callback
			if(onLoop != null)
				onLoop(iteration, iteration == iterations)
		}
	}
	
	protected def initMetadata(vertex: Vertex) = {
		val vertexMetadata = new VertexMetadata
		vertexMetadata.pos.set(vertex.getMetadata.getX, vertex.getMetadata.getY)
		vertex.getMetadata.setValue("fr", vertexMetadata)
	}
	
	protected def calculateRepulsion(vertexV: Vertex) = {
		val metaV = vertexV.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]
		vertexNeighbours.forNeighbours(vertexV, vertexU => {
				val metaU = vertexU.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]
				if(vertexV != vertexU) {
					val delta = metaV.pos - metaU.pos
					val deltaLength = math.max(epsilon, delta.lenght) // avoid x/0
					val force = k * k / deltaLength
					metaV.disp += delta * force / deltaLength
				}
			})
	}
	
	protected def calculateAttraction(edge: Edge) = {
		val metaV = edge.getVertexA.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]
		val metaU = edge.getVertexB.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]

		val delta = metaV.pos - metaU.pos
		val deltaLength = math.max(epsilon, delta.lenght) // avoid x/0
		val force = deltaLength * deltaLength / k

		val disp = delta * force / deltaLength

		metaV.disp -= disp
		metaU.disp += disp
	}
	
	protected def calculatePosition(vertex: Vertex) = {
		val meta = vertex.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]
		val delta = math.max(epsilon, meta.disp.lenght) // avoid x/0
		val disp = meta.disp * math.min(temperature, delta) / delta

		meta.pos.x = between(0, meta.pos.x + disp.x, width)
		meta.pos.y = between(0, meta.pos.y + disp.y, height)

		vertex.getMetadata.setX(meta.pos.x.toInt)
		vertex.getMetadata.setY(meta.pos.y.toInt)
	}
	
	protected def cool(iteration: Int) = {
		temperature = (1.0 - (iteration.toDouble / iterations)) * 0.1 * math.sqrt(area);
	}
	
	protected def between(min: Double, value: Double, max: Double): Double = {
		if(value < min)
			return min
		if(value > max)
			return max
		return value
	}
	
}

protected class Vector(var x: Double = 0.0, var y: Double = 0.0) {
	
	def +(operand: Vector): Vector = {
		return new Vector(x + operand.x, y + operand.y)
	}
	
	def -(operand: Vector): Vector = {
		return new Vector(x - operand.x, y - operand.y)
	}
	
	def *(operand: Vector): Vector = {
		return new Vector(x * operand.x, y * operand.y)
	}
	
	def *(operand: Double): Vector = {
		return new Vector(x * operand, y * operand)
	}
	
	def /(operand: Double): Vector = {
		return new Vector(x / operand, y / operand)
	}
	
	def isNaN: Boolean = x.isNaN || y.isNaN
	
	def set(x: Double, y: Double): Vector = {
		this.x = x
		this.y = y
		return this
	}
	
	def clear = {
		x = 0.0
		y = 0.0
	}
	
	def lenght = math.sqrt(x * x + y * y)
	
}

protected class VertexMetadata {
	var pos = new Vector
	var disp = new Vector
}
