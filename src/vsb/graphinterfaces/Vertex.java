package vsb.graphinterfaces;

import java.util.Set;

public interface Vertex {
	
	long getId();
	void addEdge(Edge edge);
	Metadata getMetadata();
	Set<Edge> getEdges();
	Set<Edge> getEdgesBy(EdgeFilter filter);
	Set<Vertex> getAdjacentVertices();
	Set<Vertex> getAdjacentVertices(VertexFilter filter);
	
}
