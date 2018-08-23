package AVL;

import interfaces.BinaryTree;
import interfaces.InvalidPositionException;
import interfaces.Position;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class ClassBT implements BinaryTree {

    private NodeBT root;
    private int size;

    public ClassBT() {
        root = null;
        size = 0;
    }

    /*
    
    Métodos da AVL 
    
     */
    //balancear
    private void balance(NodeBT node) {
        if (node.getFB() > 0) {//se é 2
            if (node.getLeft().getFB() >= 0) //se a subarvore da esquerda é MAIOR ou igual a 0
            {
                RSD(node);
            } else { //se a subarvore da esquerda é MENOR que 0
                RSE(node.getLeft());
                RSD(node);
            }
        } else //se é -2
        if (node.getRight().getFB() <= 0) //se a subarvore da direita é MENOR ou igual a 0
        {
            RSE(node);
        } else { //se a subarvore da direita é MAIOR que 0
            RSD(node.getRight());
            RSE(node);
        }
    }

    //rotacao simples a esquerda   
    private void RSE(NodeBT node) {        
        NodeBT backupR = node.getRight();
        NodeBT backupL = backupR.getLeft();
        
        if(isRoot(node))
            root = backupR;
        
        backupR.setLeft(node);
        node.setParent(backupR);
        
        node.setRight(backupL);
        if(backupL != null) backupL.setParent(node);
        
        node.setFB(node.getFB() +1 - min(backupR.getFB(), 0));
        backupR.setFB(backupR.getFB()+1 - max(node.getFB(), 0));
    }

    //rotacao simples a direita
    private void RSD(NodeBT node) {        
        NodeBT nLeft = node.getLeft();
        NodeBT leftRight = nLeft.getRight();

        if(node == root) 
            root = nLeft;
        nLeft.setRight(node);
        node.setParent(nLeft);
        
        node.setLeft(leftRight);
         if(leftRight != null) leftRight.setParent(node);
        
        node.setFB(node.getFB()-1 - max(nLeft.getFB(), 0));
        nLeft.setFB(nLeft.getFB()-1 - min(node.getFB(), 0));       
    }

    //calcular Fator de Balanceamento (FB)
    private int getCurrentFB(NodeBT node) throws InvalidPositionException {
        return height(node.getLeft()) - height(node.getRight());//nova altura
    }

    //checa se precisa balancear
    private boolean isUnbalanced(NodeBT node) throws InvalidPositionException {
        int currentFB = node.getFB();
        if (currentFB > 1 || currentFB < -1) {
            //node.setFB(currentFB);
            return true;
        }
        return false;
    }

    //sobe pelo nó inserido mudando os FBs
    private void changeFBInsert(NodeBT node) throws InvalidPositionException {
        
        if(node.getParent() == null)
            return;
        
        if (isLeftChild(node)) 
            node.getParent().changeFB(1);
        else 
            node.getParent().changeFB(-1); 
        
        if(node.getParent().getFB() == 0) 
            return;
        
        if (isUnbalanced(node.getParent())){ //se está desbalanceado, balanceia                
            balance(node.getParent()); 
            return;
        }

        //chama recursivamente passando o pai, para ir subindo até o root ou a condição de parada
        changeFBInsert(node.getParent());
    }

    /*
    
    Métodos da arvore de pesquisa
    
     */
    @Override
    public void insert(int key, Object o) throws InvalidPositionException {
        NodeBT node = new NodeBT(key, o, null);
        if (isEmpty()) 
            root = node;
        else 
            insert(root, node);
    }

    private void insert(NodeBT aux, NodeBT node) throws InvalidPositionException {
        if (node.getKey() < aux.getKey()) {
            if (hasLeft(aux)) {
                aux = (NodeBT) getLeft(aux);
                insert(aux, node);
            } else {
                aux.setLeft(node);
                node.setParent(aux);
                changeFBInsert(node);
                size++;
            }
        } else if (node.getKey() > aux.getKey()) {
            if (hasRight(aux)) {
                aux = (NodeBT) getRight(aux);
                insert(aux, node);
            } else {
                aux.setRight(node);
                node.setParent(aux);
                changeFBInsert(node);
                size++;
            }
        } else {
            return;
        }
    }

    private boolean isLeftChild(NodeBT node) {
        return node.getKey() < node.getParent().getKey();
    }

    @Override
    public NodeBT remove(int key) throws InvalidPositionException {
        NodeBT node = find(key);
        return remove(node, node.getElement());
    }

    private NodeBT remove(NodeBT node, Object element) throws InvalidPositionException {
        if (node != null) {
            //o nó não tem filhos
            if (isExternal(node)) {
                if (node.getKey() < node.getParent().getKey()) {
                    node.getParent().setRight(null);
                } else {
                    node.getParent().setLeft(null);
                }

                size--;
                return node;
            }
            //o nó tem 1 filho (esquerdo)
            if (hasLeft(node) && !hasRight(node)) {

                if (isLeftChild(node)) {
                    node.getParent().setLeft(node.getLeft());
                } else {
                    node.getParent().setRight(node.getLeft());
                }

                node.getLeft().setParent(node.getParent());
                size--;
                return node;
            }
            //o nó tem um filho (direito)
            if (!hasLeft(node) && hasRight(node)) {
                if (isLeftChild(node)) {
                    node.getParent().setLeft(node.getRight());
                } else {
                    node.getParent().setRight(node.getRight());
                }

                node.getRight().setParent(node.getParent());
                size--;
                return node;
            }
            //o nó tem dois filhos
            NodeBT aux = node.getRight(); //pega o filho da direita
            while (aux.getLeft() != null) { //pega o ultimo filho da esquerda desse nó ou ele mesmo
                aux = aux.getLeft();
            }
            element = aux.getElement();//pega o conteudo do nó a ser removido
            remove(aux, element);//remove esse nó recursivamente
            node.setElement(element);//restaura o conteudo do nó removido 

            size--;
            return node;
        }
        return null;
    }

    @Override
    public NodeBT find(int key) throws InvalidPositionException {
        NodeBT node = new NodeBT(key, null, null);
        if (!isEmpty()) {
            return find(root, node);
        }

        return null;
    }

    private NodeBT find(NodeBT aux, NodeBT node) throws InvalidPositionException {
        if (node.getKey() < aux.getKey()) {
            if (hasLeft(aux)) {
                aux = (NodeBT) getLeft(aux);
                return find(aux, node);
            } else {
                return null;
            }
        }
        if (node.getKey() > aux.getKey()) {
            if (hasRight(aux)) {
                aux = (NodeBT) getRight(aux);
                return find(aux, node);
            } else {
                return null;
            }
        }
        return aux;
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
        return (no.getLeft() != null);
    }

    @Override
    public boolean hasRight(NodeBT no) throws InvalidPositionException {
        return (no.getRight() != null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height(Position p) {
        NodeBT node = (NodeBT) p;
        if (isExternal(node)) {
            return 0;
        } else {
            Iterator itr = children(node);
            int h = 0;
            while (itr.hasNext()) {
                NodeBT noChild = (NodeBT) itr.next();
                h = Math.max(h, height(noChild));
            }
            return 1 + h;
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
        return (root == null);
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
        NodeBT node = (NodeBT) p;

        if (node.getLeft() != null) {
            vector.add(node.getLeft());
        }
        if (node.getRight() != null) {
            vector.add(node.getRight());
        }

        return vector.iterator();
    }

    @Override
    public boolean isExternal(Position p) {
        NodeBT node = (NodeBT) p;
        return node.getLeft() == null && node.getRight() == null;
    }

    @Override
    public boolean isInternal(Position p) {
        return !isExternal(p);
    }

    @Override
    public boolean isRoot(Position p) {
        NodeBT node = (NodeBT) p;
        return node == root;
    }

    @Override
    public Object replace(Position p, Object o) {
        NodeBT node = (NodeBT) p;
        Object aux = node.getElement();
        node.setElement(o);
        return aux;
    }

    public void mostrar() {
        ArrayList<StringBuffer> a = new ArrayList<>();
        int altura = height(root) + 1;

        for (int i = 0; i < altura; i++) {
            a.add(new StringBuffer());
        }

        mostrarRecursao(root, 0, a);
        for (int i = 0; i < altura; i++) {
            System.out.println(a.get(i));
        }
        System.out.println("\n\n\n\n");
    }

    private void mostrarRecursao(NodeBT node, int profundidade, ArrayList<StringBuffer> a) {
        if (node == null) {
            return;
        }

        mostrarRecursao(node.getLeft(), profundidade + 1, a);
        for (int i = 0; i < height(root) + 1; ++i) {
            if (i == profundidade) {
                a.get(i).append(node.getKey());
            } else {
                a.get(i).append("  ");
            }
        }
        mostrarRecursao(node.getRight(), profundidade + 1, a);
    }

}
