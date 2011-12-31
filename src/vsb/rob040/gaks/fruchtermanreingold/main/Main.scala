package vsb.rob040.gaks.fruchtermanreingold.main

import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import vsb.rob040.gaks.fruchtermanreingold._
import vsb.rob040.gaks.fruchtermanreingold.source._
import vsb.rob040.gaks.fruchtermanreingold.view._
import vsb.rob040.gaks.fruchtermanreingold.vertexneighbours._

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
		println(" -h=NUMBER - height of image, default is width")
		println(" -t=NUMBER - number of iterations, default is 100")
		println(" -b=NUMBER - outer border of image, default is 20px")
		println(" -g=yes/no - use square-grid algorithm for repulsion computation, default is yes")
		println(" -n=yes/no - disply vertice names, default is yes")
		println(" -s=yes/no - disply info about graph, default is no")
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
		var height: Int = -1
		var iterations: Int = 100
		var outerBorder: Int = 20
		var displyVerticeNames: Boolean = true
		var displayInfo: Boolean = false
		var squareGrid: Boolean = true
		
		
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
						case 'g' => squareGrid = value.toLowerCase.equals("yes")
						case 'n' => displyVerticeNames = value.toLowerCase.equals("yes")
						case 's' => displayInfo = value.toLowerCase.equals("yes")
						case _ => throw new IllegalArgumentException("Invalid option '" + arg.charAt(1) + "'")
					}
				} else
					throw new IllegalArgumentException("Invalid option '" + arg + "'")
			})
		
		if(input == null)
			return println("No file specified !!!")
		if(output == null)
			output = input
		if(height < 0)
			height = width
		
		val start = System.currentTimeMillis
		
		val reader = new InputStreamReader(new FileInputStream(input))
		val graph = new GraphFactory(new GraphSourceReader(reader)).createGraph
		val layout = new FruchtermanReingoldLayout(width, height, graph, iterations)
		val gi = new LayoutImage(new LayoutRendererImpl(outerBorder, displyVerticeNames), outerBorder)
		
		if(squareGrid)
			layout.vertexNeighbours = new GridSquareVertexNeighbours(graph, layout.width, layout.height, (2*layout.k).floor.toInt)
		
		layout.onLoop = (i, last) => {
			if(last)
				gi.save(layout, new File(output + ".png"))			
		}
		
		layout.run
		
		val stop = System.currentTimeMillis
		if(displayInfo)
			println("Graph '" + input + "' generated in " + (stop - start) + "ms Vertices: " + graph.getVertices.size + " Edges: " + graph.getEdges.size + (if(squareGrid)" with square-grid algorithm"else""))
	}

}
