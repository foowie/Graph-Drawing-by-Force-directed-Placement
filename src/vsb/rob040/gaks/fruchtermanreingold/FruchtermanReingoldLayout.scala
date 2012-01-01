package vsb.rob040.gaks.fruchtermanreingold

import scala.util.Random
import vsb.graphinterfaces._
import vsb.rob040.gaks.fruchtermanreingold.vertexneighbours._

/**
 * Layout implementation using Fruchterman-Reingold algorithm
 */
class FruchtermanReingoldLayout(width: Int, height: Int, graph: Graph, val iterations: Int) extends Layout {

	private val area = width * height // area of graph
	private val k = 0.8 * math.sqrt(area / graph.getVertices.size) // force constant
	private var temperature = 0.1 * math.sqrt(area) // current temperature
	private var currentIteration = 1 // current iteration
	val epsilon = 0.0001D // minimal distance
	var onLoop: (Int, Boolean) => Unit = null // on-loop callback
	var vertexNeighbours: VertexNeighbours = new AllVertexNeighbours(graph) // vertex neighbours impl
	
	def getWidth: Int = width
	
	def getHeight: Int = height
	
	def getGraph: Graph = graph
	
	def getK: Double = k
	
	/**
	 * Shuffle vertices position
	 */
	def shuffle = {
		val random = new Random
		graph.getVertices.foreach(vertex => {
				vertex.getMetadata.setX(random.nextInt(width))
				vertex.getMetadata.setY(random.nextInt(height))
			})
	}
	
	/**
	 * Execute alg.
	 */
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
	
	/**
	 * Initialize metadata of vertices
	 */
	private def initMetadata(vertex: Vertex) = {
		val vertexMetadata = new VertexMetadata
		vertexMetadata.pos.set(vertex.getMetadata.getX, vertex.getMetadata.getY)
		vertex.getMetadata.setValue("fr", vertexMetadata)
	}
	
	/**
	 * Calculate repulsion force of single vertex
	 */
	private def calculateRepulsion(vertexV: Vertex) = {
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
	
	/**
	 * Calculate attraction force of single edge
	 */
	private def calculateAttraction(edge: Edge) = {
		val metaV = edge.getVertexA.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]
		val metaU = edge.getVertexB.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]

		val delta = metaV.pos - metaU.pos
		val deltaLength = math.max(epsilon, delta.lenght) // avoid x/0
		val force = deltaLength * deltaLength / k

		val disp = delta * force / deltaLength

		metaV.disp -= disp
		metaU.disp += disp
	}
	
	/**
	 * Calculate position of single vertex
	 */
	private def calculatePosition(vertex: Vertex) = {
		val meta = vertex.getMetadata.getValue("fr").get.asInstanceOf[VertexMetadata]
		val delta = math.max(epsilon, meta.disp.lenght) // avoid x/0
		val disp = meta.disp * math.min(temperature, delta) / delta

		meta.pos.x = between(0, meta.pos.x + disp.x, width)
		meta.pos.y = between(0, meta.pos.y + disp.y, height)

		vertex.getMetadata.setX(meta.pos.x.toInt)
		vertex.getMetadata.setY(meta.pos.y.toInt)
	}
	
	/**
	 * Cool graph
	 */
	private def cool(iteration: Int) = {
		temperature = (1.0 - (iteration.toDouble / iterations)) * 0.1 * math.sqrt(area);
	}
	
	/**
	 * Return value when is between range (min, max) or min/max
	 */
	private def between(min: Double, value: Double, max: Double): Double = {
		if(value < min)
			return min
		if(value > max)
			return max
		return value
	}

	/**
	 * Vector impl. for metadata
	 */
	private class Vector(var x: Double = 0.0, var y: Double = 0.0) {

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

	/**
	 * Vertex metadata
	 */
	private class VertexMetadata {
		var pos = new Vector
		var disp = new Vector
	}
	
}

