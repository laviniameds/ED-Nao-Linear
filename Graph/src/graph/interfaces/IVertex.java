/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.interfaces;

/**
 *
 * @author lavinia
 */
public interface IVertex {
    
    public abstract int getKey();
    public abstract void setKey(int key);
    public abstract double getValue();
    public abstract void setValue(double value);
    
}
