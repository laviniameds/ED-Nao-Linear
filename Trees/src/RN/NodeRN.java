/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import interfaces.Position;

/**
 *
 * @author lavinia
 */
public class NodeRN implements Position{
    private int key;
    private Object element;
    private NodeRN parent,left,right;
    private int color; // 0 = negro, 1 = rubro
    private boolean doubleBlack;
   
    public NodeRN(int key, Object element, NodeRN parent) {
        this.key = key;
        this.element = element;
        this.parent = parent;
        this.color = 1;
        this.doubleBlack = false;
    }
    
    public void changeDoubleBlack(boolean db){
        this.doubleBlack = db;
    }
    
    public boolean isDoubleBlack(){
        return this.doubleBlack;
    }

    @Override
    public Object getElement() {
        return this.element;
    }
    
    public void setElement(Object element){
        this.element = element;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public NodeRN getParent() {
        return parent;
    }

    public void setParent(NodeRN parent) {
        this.parent = parent;
    }

    public NodeRN getLeft() {
        return left;
    }

    public void setLeft(NodeRN left) {
        this.left = left;
    }

    public NodeRN getRight() {
        return right;
    }

    public void setRight(NodeRN right) {
        this.right = right;
    }    

    public int getColor() {
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }
}
