package vsb.rob040.gaks.fruchtermanreingold.source

import java.io.BufferedReader
import java.io.Reader
import java.io.StreamTokenizer
import scala.collection.mutable.HashSet

class GraphSourceReader(reader: Reader) extends GraphSource {
	
	var vertices: Set[Int] = null
	
	var edges: Set[NodePair] = null
	
	{
		val tokenizer = new StreamTokenizer(new BufferedReader(reader));

		val startIndex = readNumber(tokenizer).get
		val count = readNumber(tokenizer).get
		vertices = startIndex.until(startIndex + count).toSet[Int]
		val es = new HashSet[NodePair]
		while(readNumber(tokenizer, true) != None) {
			val fromVertex = tokenizer.nval.intValue
			val toVertex = readNumber(tokenizer).get
			if(!vertices.contains(fromVertex) || !vertices.contains(toVertex))
				throw new IllegalStateException("Invalid vertex number in edge " + fromVertex + "/" + toVertex)
			es.add(new NodePair(fromVertex, toVertex))
		}
		edges = es.toSet[NodePair]
	}
	
	private def readNumber(tokenizer: StreamTokenizer, optional: Boolean = false): Option[Int] = {
		if(tokenizer.nextToken == StreamTokenizer.TT_EOF) {
			if(optional)
				return None
			else
				throw new IllegalStateException("Invalid input file !")
		}
			
		if(tokenizer.ttype != StreamTokenizer.TT_NUMBER)
			throw new IllegalStateException("Invalid input file !")

		return Some(tokenizer.nval.intValue)
	}
	
	def getEdges: Set[NodePair] = edges
	
	def getVertices: Set[Int] = vertices
	
}
