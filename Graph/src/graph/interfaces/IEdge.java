/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.interfaces;

import graph.Vertex;

/**
 *
 * @author lavinia
 */
public interface IEdge {
    
    public abstract Vertex getVertexTo();
    public abstract void setVertexTo(Vertex vertexTo);
    public abstract Vertex getVertexFrom();
    public abstract void setVertexFrom(Vertex vertexFrom);
    public abstract boolean isDirected();
    public abstract void setIsDirected(boolean isDirected);  
    public abstract double getValue();
    public abstract void setValue(double value);    
    
}
