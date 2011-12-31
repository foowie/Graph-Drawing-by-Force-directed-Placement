package vsb.rob040.gaks.fruchtermanreingold.view

import java.awt.Color
import java.awt.Graphics
import vsb.graphinterfaces.Layout

class LayoutRendererImpl(border: Int = 20, displyVerticeNames: Boolean = true) extends LayoutRenderer {
	
	var minVertexSize = 5
	var maxVertexSize = 31
	
	def render(layout: Layout, graphics: Graphics) = {
		if(layout == null)
			throw new NullPointerException("Layout cant be null")
		if(graphics == null)
			throw new NullPointerException("Graphics cant be null")
		
		val baseEdges = layout.getGraph.getVertices.reduceLeft((x, y) => if (x.getEdges.size < y.getEdges.size) x else y).getEdges.size
		val maxEdges = layout.getGraph.getVertices.reduceLeft((x, y) => if (x.getEdges.size > y.getEdges.size) x else y).getEdges.size - baseEdges
		
		graphics.setColor(Color.WHITE)
		graphics.fillRect(0, 0, layout.getWidth + border * 2, layout.getHeight + border * 2)

		graphics.setColor(Color.BLACK)
		layout.getGraph.getEdges.foreach(edge => {
				graphics.drawLine(border + edge.getVertexA.getMetadata.getX, border + edge.getVertexA.getMetadata.getY, border + edge.getVertexB.getMetadata.getX, border + edge.getVertexB.getMetadata.getY)
			})
		
		graphics.setColor(Color.RED)
		layout.getGraph.getVertices.foreach(vertex => {
				val size = if(maxEdges == 0) minVertexSize else ((((vertex.getEdges.size - baseEdges) / maxEdges.toDouble) * (maxVertexSize - minVertexSize)) + minVertexSize).toInt
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
