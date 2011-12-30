package fruchtermanreingold

import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import vsb.rob040.gaks.fruchtermanreingold._
import vsb.rob040.gaks.fruchtermanreingold.source._
import vsb.rob040.gaks.fruchtermanreingold.view._

object Main {

	def printHelp = {
		println("")
		println("Graph Drawing by Force-directed Placement")
		println("Fruchterman-Reingold")
		println("")
		println("Options:")
		println(" -i=FILENAME - load network from FILENAME")
		println(" -o=FILENAME - save result into FILENAME.png, default is INPUT_FILENAME.png")
		println(" -w=NUMBER - width of image, default is 1000px")
		println(" -h=NUMBER - height of image, default is 1000px")
		println(" -t=NUMBER - number of iterations, default is 100")
		println(" -b=NUMBER - outer border of image, default is 20px")
		println(" -n=yes/no - disply vertice names, default is yes")
		println("")
		println("Created by Daniel Robenek 2011")
		println("")
	}
	
	def main(args: Array[String]): Unit = {
		
		if(args.size == 0)
			return printHelp
		
		var input: String = null
		var output: String = null
		var width: Int = 1000
		var height: Int = 1000
		var iterations: Int = 100
		var outerBorder: Int = 20
		var displyVerticeNames: Boolean = true
		
		
		args.foreach(arg => {
				if(arg.startsWith("-")) {
					val value = arg.substring(3)
					arg.charAt(1) match {
						case 'o' => output = value
						case 'i' => input = value
						case 'w' => width = value.toInt
						case 'h' => height = value.toInt
						case 't' => iterations = value.toInt
						case 'b' => outerBorder = value.toInt
						case 'n' => displyVerticeNames = value.toLowerCase.equals("yes")
						case _ => throw new IllegalArgumentException("Invalid option '" + arg.charAt(1) + "'")
					}
				} else
					throw new IllegalArgumentException("Invalid option '" + arg + "'")
			})
		
		if(input == null)
			return println("No file specified !!!")
		if(output == null)
			output = input
		
		val reader = new InputStreamReader(new FileInputStream(input))
		val graph = new GraphFactory(new GraphSourceReader(reader)).createGraph
		val layout = new FruchtermanReingoldLayout(width, height, graph, iterations)
		val gi = new LayoutImage(new LayoutRendererImpl(outerBorder, displyVerticeNames), outerBorder)
		
		layout.onLoop = (i, last) => {
			if(last)
				gi.save(layout, new File(output + ".png"))			
		}
		
		layout.shuffle
		layout.run
		
	}

}
