package vsb.rob040.gaks.fruchtermanreingold

import scala.collection.mutable.HashMap
import vsb.graphinterfaces.Metadata

/**
 * Metadata of single vertex
 */
class MetadataImpl extends Metadata {

	val map = new HashMap[String, Any]();
	
	def setValue(name: String, value: Any) = map.put(name, value)
	
	def getValue(name: String): Option[Any] = map.get(name)
	
	def getX: Int = map.get("x").getOrElse(0).asInstanceOf[Int]
	def setX(x: Int) = map.put("x", x)
	def getY: Int = map.get("y").getOrElse(0).asInstanceOf[Int]
	def setY(y: Int) = map.put("y", y)
	
}
