package vsb.rob040.gaks.fruchtermanreingold.view

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import vsb.graphinterfaces.Layout

/**
 * Class that store graph using given LayoutRenderer into PNG file
 */
class LayoutImage(val renderer: LayoutRenderer, border: Int) {
	
	/**
	 * Store layout into given file
	 */
	def save(layout: Layout, file: File) = {
		
		if(file == null)
			throw new NullPointerException("File cant be null")
		
		val img = new BufferedImage(
			layout.getWidth + border * 2, 
			layout.getHeight + border * 2, 
			BufferedImage.TYPE_INT_ARGB
		)
		renderer.render(layout, img.getGraphics)
		ImageIO.write(img, "png", file);
		
	}
	
}
