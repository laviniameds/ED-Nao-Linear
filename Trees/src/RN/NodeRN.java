/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import AVL.NodeBT;
import interfaces.Position;

/**
 *
 * @author lavinia
 */
public class NodeRN implements Position{
    private int key;
    private Object element;
    private NodeBT parent,left,right;
    private int color; // 0 = negro, 1 = rubro
   
    public NodeRN(int key, Object element, NodeBT parent) {
        this.key = key;
        this.element = element;
        this.parent = parent;
        this.color = 0;
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

    public NodeBT getParent() {
        return parent;
    }

    public void setParent(NodeBT parent) {
        this.parent = parent;
    }

    public NodeBT getLeft() {
        return left;
    }

    public void setLeft(NodeBT left) {
        this.left = left;
    }

    public NodeBT getRight() {
        return right;
    }

    public void setRight(NodeBT right) {
        this.right = right;
    }    

    public int getColor() {
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }
}
