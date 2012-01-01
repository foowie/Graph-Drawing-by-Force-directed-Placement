package vsb.rob040.gaks.fruchtermanreingold.view

import java.awt.Color
import java.awt.Graphics
import vsb.graphinterfaces.Layout

/**
 * Draw given layout into graphics object
 */
class LayoutRendererImpl(border: Int = 20, displyVerticeNames: Boolean = true) extends LayoutRenderer {
	
	var minVertexSize = 5
	var maxVertexSize = 31
	
	/**
	 * Draw given layout into graphics object
	 */
	def render(layout: Layout, graphics: Graphics) = {
		if(layout == null)
			throw new NullPointerException("Layout cant be null")
		if(graphics == null)
			throw new NullPointerException("Graphics cant be null")
			
		// minimum count of edges on single vertex
		val baseEdges = layout.getGraph.getVertices.reduceLeft((x, y) => if (x.getEdges.size < y.getEdges.size) x else y).getEdges.size
		// maximum difference of edges in vertices
		val maxDiffEdges = layout.getGraph.getVertices.reduceLeft((x, y) => if (x.getEdges.size > y.getEdges.size) x else y).getEdges.size - baseEdges
		
		// clear background
		graphics.setColor(Color.WHITE)
		graphics.fillRect(0, 0, layout.getWidth + border * 2, layout.getHeight + border * 2)

		// draw edges
		graphics.setColor(Color.BLACK)
		layout.getGraph.getEdges.foreach(edge => {
				graphics.drawLine(
					border + edge.getVertexA.getMetadata.getX, 
					border + edge.getVertexA.getMetadata.getY, 
					border + edge.getVertexB.getMetadata.getX, 
					border + edge.getVertexB.getMetadata.getY
				)
			})
		
		// draw vertices and its numbers
		graphics.setColor(Color.RED)
		layout.getGraph.getVertices.foreach(vertex => {
				// compute size of oval
				val size = if(maxDiffEdges == 0) minVertexSize else ((((vertex.getEdges.size - baseEdges) / maxDiffEdges.toDouble) * (maxVertexSize - minVertexSize)) + minVertexSize).toInt
				graphics.fillOval(
					border + vertex.getMetadata.getX - size/2, 
					border + vertex.getMetadata.getY - size/2, 
					size, 
					size
				)
				if(displyVerticeNames) {
					graphics.drawString(
						vertex.getId.toString, 
						border + vertex.getMetadata.getX + size/2, 
						border + vertex.getMetadata.getY
					)
				}
			})
	}
	
}
