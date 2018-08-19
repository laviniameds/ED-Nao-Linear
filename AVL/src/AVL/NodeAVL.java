package AVL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lavinia
 */
public class NodeAVL {
    
    private int key;
    private Object element;
    private NodeAVL parent,left,right;
   
    public NodeAVL(int key, Object element, NodeAVL parent) {
        this.key = key;
        this.element = element;
        this.parent = parent;      
    }

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

    public NodeAVL getParent() {
        return parent;
    }

    public void setParent(NodeAVL parent) {
        this.parent = parent;
    }

    public NodeAVL getLeft() {
        return left;
    }

    public void setLeft(NodeAVL left) {
        this.left = left;
    }

    public NodeAVL getRight() {
        return right;
    }

    public void setRight(NodeAVL right) {
        this.right = right;
    }
}
