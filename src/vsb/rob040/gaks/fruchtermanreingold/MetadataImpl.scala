package vsb.rob040.gaks.fruchtermanreingold

import scala.collection.mutable.HashMap
import vsb.graphinterfaces.Metadata

class MetadataImpl extends Metadata {

	val map = new HashMap[String, Any]();
	
	def setValue(name: String, value: Any) = map.put(name, value)
	
	def getValue(name: String): Option[Any] = map.get(name)
	
}
