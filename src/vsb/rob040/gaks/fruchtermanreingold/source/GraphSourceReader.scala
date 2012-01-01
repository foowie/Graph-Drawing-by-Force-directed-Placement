package vsb.rob040.gaks.fruchtermanreingold.source

import java.io.BufferedReader
import java.io.Reader
import java.io.StreamTokenizer
import scala.collection.mutable.HashSet

/**
 * Create graph source from any type of reader
 * Format is:
 * 
 * START_VERTEX_NUMBER VERTICES_COUNT
 * EDGE1_VERTEX1 EDGE1_VERTEX2
 * EDGE2_VERTEX1 EDGE2_VERTEX2
 * ...
 */
class GraphSourceReader(reader: Reader) extends GraphSource {
	
	private val vertices = new HashSet[Int]
	private val edges = new HashSet[NodePair]
	
	{ // constructor
		val tokenizer = new StreamTokenizer(new BufferedReader(reader));
		val startIndex = readNumber(tokenizer).get
		val count = readNumber(tokenizer).get
		
		// generate vertice numbers
		vertices ++= startIndex.until(startIndex + count)
		
		// load edges
		while(readNumber(tokenizer, true) != None) {
			val fromVertex = tokenizer.nval.intValue
			val toVertex = readNumber(tokenizer).get
			if(!vertices.contains(fromVertex) || !vertices.contains(toVertex))
				throw new IllegalStateException("Invalid vertex number in edge " + fromVertex + "/" + toVertex)
			edges.add(new NodePair(fromVertex, toVertex))
		}
	}

	/**
	 * Get loaded edges
	 */
	def getEdges: scala.collection.Set[NodePair] = edges
	
	/**
	 * Get loaded vertices
	 */
	def getVertices: scala.collection.Set[Int] = vertices
	
	/**
	 * Read single number from given tokenizer, throws exception when EOF and optional=false
	 */
	private def readNumber(tokenizer: StreamTokenizer, optional: Boolean = false): Option[Int] = {
		if(tokenizer.nextToken == StreamTokenizer.TT_EOF) {
			if(optional)
				return None
			else
				throw new IllegalStateException("Invalid input file (missing second edge vertex) !")
		}
			
		if(tokenizer.ttype != StreamTokenizer.TT_NUMBER)
			throw new IllegalStateException("Invalid input file (not a number) '" + tokenizer.sval + "' !")

		return Some(tokenizer.nval.intValue)
	}
	
}
