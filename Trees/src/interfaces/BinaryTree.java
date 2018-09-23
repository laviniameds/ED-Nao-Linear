package interfaces;

public interface BinaryTree extends Tree {
    
    public Position getLeft(Position no) throws InvalidPositionException;
    public Position getRight(Position no) throws InvalidPositionException;
    public Position getSibling(Position no) throws InvalidPositionException;
    public boolean hasLeft(Position no) throws InvalidPositionException;
    public boolean hasRight(Position no) throws InvalidPositionException;
    
    public void insert(int key, Object o)throws InvalidPositionException;
    public Position remove(int key) throws InvalidPositionException;
    public Position find(int key)throws InvalidPositionException;
    
}
