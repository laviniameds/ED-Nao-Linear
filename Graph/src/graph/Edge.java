/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import graph.interfaces.IEdge;

/**
 *
 * @author lavinia
 */
public class Edge implements IEdge{
    
    private Vertex vertexTo;
    private Vertex vertexFrom;
    private double value;
    private boolean isDirected;
    
    public Edge(Vertex vertexTo, Vertex vertexFrom){
        super();
        this.vertexTo = vertexTo;
        this.vertexFrom = vertexFrom;
        this.isDirected=false;
    }
    
    public Edge(Vertex vertexTo, Vertex vertexFrom, 
            double value){
        super();
        this.vertexTo = vertexTo;
        this.vertexFrom = vertexFrom;
        this.isDirected = false;
        this.value = value;
    }
    
    public Edge(Vertex vertexTo, Vertex vertexFrom, 
            double value, boolean isDirected){
        super();
        this.vertexTo = vertexTo;
        this.vertexFrom = vertexFrom;
        this.isDirected = false;
        this.value = value;
        this.isDirected = isDirected;
    }

    @Override
    public Vertex getVertexTo() {
        return this.vertexTo;
    }

    @Override
    public void setVertexTo(Vertex vertexTo) {
        this.vertexTo = vertexTo;
    }

    @Override
    public Vertex getVertexFrom() {
        return this.vertexFrom;
    }

    @Override
    public void setVertexFrom(Vertex vertexFrom) {
        this.vertexFrom = vertexFrom;
    }

    @Override
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override
    public void setIsDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }
  
    public String toString(){
        //return "["+vertexFrom+"-"+vertexTo+":"+value+"]";        
        return "["+value+"]";
    }
    
}
