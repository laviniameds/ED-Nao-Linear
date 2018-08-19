package interfaces;

import binary.NodeBT;

public interface BinaryTree extends Tree {
    
    public NodeBT getLeft(NodeBT no) throws InvalidPositionException;
    public NodeBT getRight(NodeBT no) throws InvalidPositionException;
    public NodeBT getSibling(NodeBT no) throws InvalidPositionException;
    public boolean hasLeft(NodeBT no) throws InvalidPositionException;
    public boolean hasRight(NodeBT no) throws InvalidPositionException;
    
    public void insert(int key, Object o)throws InvalidPositionException;
    public Object remove(int key) throws InvalidPositionException;
    public NodeBT find(int key)throws InvalidPositionException;
    
}
