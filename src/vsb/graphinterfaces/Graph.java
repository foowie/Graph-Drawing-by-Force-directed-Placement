package vsb.graphinterfaces;

import java.util.Set;

public interface Graph {
	
	void addVertex(Vertex vertex);
	void addEdge(Vertex vertexA, Vertex vertexB);
	Set<Vertex> getVertices();
	Set<Edge> getEdges();
	Set<Vertex> getVerticesBy(VertexFilter filter);
	Set<Edge> getEdgesBy(EdgeFilter filter);
	
}
