package binary;

import interfaces.BinaryTree;
import interfaces.InvalidPositionException;
import interfaces.Position;
import java.util.Iterator;
import java.util.Vector;

public class ClassBT implements BinaryTree {

    private NodeBT root;
    private int size;

    public ClassBT() {
        root = null;
        size = 0;
    }

    @Override
    public void insert(int key, Object o) throws InvalidPositionException {
        
    }

    @Override
    public Object remove(int key) throws InvalidPositionException {

    }

    @Override
    public Object search(int key, Object o) {

    }

    @Override
    public NodeBT getLeft(NodeBT no) throws InvalidPositionException {
        return no.getLeft();
    }

    @Override
    public NodeBT getRight(NodeBT no) throws InvalidPositionException {
        return no.getRight();
    }

    @Override
    public NodeBT getSibling(NodeBT no) throws InvalidPositionException {
        NodeBT father = no.getParent();
        if (father.getLeft().getElement().equals(no.getElement())) {
            return father.getRight();
        } else {
            return father.getLeft();
        }
    }

    @Override
    public boolean hasLeft(NodeBT no) throws InvalidPositionException {
        return (no.getLeft() == null);
    }

    @Override
    public boolean hasRight(NodeBT no) throws InvalidPositionException {
        return (no.getRight() == null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height(Position p) {
        NodeBT node = (NodeBT) p;
        int l = height(node.getLeft());
        int r = height(node.getRight());

        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    @Override
    public int depth(Position p) {

        NodeBT node = (NodeBT) p;

        if (isRoot(node)) {
            return 0;
        } else {
            return 1 + depth(node.getParent());
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator elements() {
        if (isEmpty()) {
            return null;
        } else {
            return inOrderElements(root).iterator();
        }
    }

    private Vector<Object> inOrderElements(Position p) {
        Vector<Object> vector = new Vector();
        NodeBT node = (NodeBT) p;
        Iterator<NodeBT> children = children(node);
        while (children.hasNext()) {
            NodeBT n = (NodeBT) children.next();
            vector.addAll(inOrderElements(n));
        }
        vector.add(node.getElement());
        return vector;
    }

    @Override
    public Iterator nos() {
        if (isEmpty()) {
            return null;
        } else {
            return inOrderNos(root).iterator();
        }
    }

    private Vector<NodeBT> inOrderNos(Position p) {
        Vector<NodeBT> vector = new Vector();
        NodeBT node = (NodeBT) p;
        Iterator<NodeBT> children = children(node);
        while (children.hasNext()) {
            NodeBT n = (NodeBT) children.next();
            vector.addAll(inOrderNos(n));
        }
        vector.add(node);
        return vector;
    }

    @Override
    public Position root() {
        return root;
    }

    @Override
    public Position parent(Position p) {
        NodeBT no = (NodeBT) p;
        return no.getParent();
    }

    @Override
    public Iterator children(Position p) {      
        Vector<NodeBT> vector = new Vector<>();
        NodeBT node = (NodeBT)p;
        
        if (node.getLeft()!= null) 
            vector.add(node.getLeft());       
        if (node.getRight()!= null) 
            vector.add(node.getRight());
        
        return vector.iterator();
    }

    @Override
    public boolean isExternal(Position p) {
        NodeBT node = (NodeBT)p;
        return
            node.getLeft() == null && node.getRight() == null;        
    }

    @Override
    public boolean isInternal(Position p) {
        return !isExternal(p);
    }

    @Override
    public boolean isRoot(Position p) {
        NodeBT node = (NodeBT)p;
        return node == root;
    }

    @Override
    public Object replace(Position p, Object o) {
        NodeBT node = (NodeBT) p;
        Object aux = node.getElement();
        node.setElement(o);
        return aux;
    }

}
