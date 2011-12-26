package vsb.graphinterfaces

trait Metadata {

	def setValue(name: String, value: Any)
	def getValue(name: String): Option[Any]
	
}
