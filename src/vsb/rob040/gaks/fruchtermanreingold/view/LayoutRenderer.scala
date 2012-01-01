package vsb.rob040.gaks.fruchtermanreingold.view

import java.awt.Graphics
import vsb.graphinterfaces.Layout

/**
 * Draw given layout into graphics object
 */
trait LayoutRenderer {

	/**
	 * Draw given layout into graphics object
	 */
	def render(layout: Layout, graphics: Graphics) 
	
}
