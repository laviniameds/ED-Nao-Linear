/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import graph.interfaces.IVertex;

/**
 *
 * @author lavinia
 */
public class Vertex implements IVertex{
    
    private int key;
    private double value;
    
    public Vertex(int key, double value){
        super();
        this.key = key;
        this.value = value;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
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
        //return "["+key+" - "+value+"]";
        return "["+value+"]";
    }
    
}
