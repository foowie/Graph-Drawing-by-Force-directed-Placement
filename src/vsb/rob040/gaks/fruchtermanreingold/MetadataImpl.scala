package vsb.rob040.gaks.fruchtermanreingold

import java.util.HashMap
import vsb.graphinterfaces.Metadata

class MetadataImpl extends Metadata {

	val map = new HashMap[String, Object]();
	
	def setValue(name: String, value: Object) = map.put(name, value)
	
	def getValue(name: String): Object = map.get(name)
	
}
