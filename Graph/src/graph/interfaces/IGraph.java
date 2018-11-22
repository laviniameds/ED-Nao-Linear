/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.interfaces;

import graph.Edge;
import graph.Vertex;
import java.util.Vector;

/**
 *
 * @author lavinia
 */
public interface IGraph {
    
    public abstract void insertVertex(Vertex vertex);
    public abstract void removeVertex(Vertex vertex);
    public abstract Edge insertEdge(Vertex vertex1, Vertex vertex2, double value);
    public abstract Edge insertEdge(Vertex vertex1, Vertex vertex2);
    public abstract void removeEdge(Edge edge);
    public abstract Edge insertArc(Vertex vertex1, Vertex vertex2, double value);
    public abstract Edge insertArc(Vertex vertex1, Vertex vertex2);
    public abstract void removeArc(Edge edge);
    public abstract int grade(Vertex vertex);
    public abstract int order();
    public abstract Vector vertices();
    public abstract Vector edges();
    public abstract Vector incidentEdges(Vertex vertex);
    public abstract Vector lastVertices(Edge edge);
    public abstract Vertex opposite(Vertex vertex, Edge edge);
    public abstract boolean isAdjacent(Vertex vertex1, Vertex vertex2);
    
}
