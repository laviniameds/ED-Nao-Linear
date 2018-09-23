package AVL;


import interfaces.Position;
import java.util.Iterator;
import java.util.Vector;


public class NodeBT implements Position{
    private int key;
    private Object element;
    private NodeBT parent,left,right;
    private int FB;
   
    public NodeBT(int key, Object element, NodeBT parent) {
        this.key = key;
        this.element = element;
        this.parent = parent;
        this.FB = 0;
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

    public int getFB() {
        return FB;
    }

    public void setFB(int FB){
        this.FB = FB;
    }
    
    public void changeFB(int value){
        this.FB += value;
    }
    
}
