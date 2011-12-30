package vsb.graphinterfaces

trait Metadata {

	def setValue(name: String, value: Any)
	def getValue(name: String): Option[Any]
	
	def getX: Int
	def setX(x: Int)
	def getY: Int
	def setY(x: Int)
	
}
