package vsb.graphinterfaces;

public interface Edge {

	long getId();
	Metadata getMetadata();
	Vertex getVertexA();
	Vertex getVertexB();
	boolean contains(Vertex vertex);
	
}
