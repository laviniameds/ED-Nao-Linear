/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import interfaces.BinaryTree;
import interfaces.InvalidPositionException;
import interfaces.Position;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author lavinia
 */
public class ClassRN implements BinaryTree{
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_DB = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001b[1m";
    
    private NodeRN root;
    private int size;

    public ClassRN() {
        root = null;
        size = 0;
    }
    
    
    /*
    
    Métodos da árvore Rubro-Negra
    
    */
    
    //rotacao simples a esquerda   
    private void RSE(NodeRN node) {                      
        NodeRN backupRightSubTree = node.getRight();
        
        if(backupRightSubTree.getLeft() != null){
            node.setRight(backupRightSubTree.getLeft());
            backupRightSubTree.getLeft().setParent(node);
        }
        else
            node.setRight(null);
        
        backupRightSubTree.setLeft(node);
        
        if(node.getParent() != null){
            backupRightSubTree.setParent(node.getParent());
            
            if(isLeftChild(node))
                node.getParent().setLeft(backupRightSubTree);
            else
                node.getParent().setRight(backupRightSubTree);
        }
        
        node.setParent(backupRightSubTree);
        
        if(isRoot(node)){
            root = backupRightSubTree;
            root.setParent(null);
        }       
    }

    //rotacao simples a direita
    private void RSD(NodeRN node) {  
        NodeRN backupLeftSubTree = node.getLeft();
        
        if(backupLeftSubTree.getRight()!= null){
            node.setLeft(backupLeftSubTree.getRight());
            backupLeftSubTree.getRight().setParent(node);
        }
        else
            node.setLeft(null);
        
        backupLeftSubTree.setRight(node);
        
        if(node.getParent() != null){
            backupLeftSubTree.setParent(node.getParent());
            
            if(isLeftChild(node))            
                node.getParent().setLeft(backupLeftSubTree);
            else
                node.getParent().setRight(backupLeftSubTree);
        }
        
        node.setParent(backupLeftSubTree);
        
        if(isRoot(node)){
            root = backupLeftSubTree;
            root.setParent(null);
        }
    }
    
    //inverter coloração
    private void changeColor(NodeRN node){
        if(node.getColor() == 0)
            node.setColor(1);
        else
            node.setColor(0);
    }
    
    //so nó tem 2 filhos?
    private boolean hasBothChildren(NodeRN node) throws InvalidPositionException{
        if(hasLeft(node) && hasRight(node))
            return true;
        return false;
    }
    
    //ambos os filhos sao negros
    private boolean childrenAreBlack(NodeRN node) throws InvalidPositionException{
        
        //if(hasBothChildren(node))
        if((node.getLeft() == null || node.getLeft().getColor() == 0) &&
                (node.getRight() == null || node.getRight().getColor() == 0))
            return true;
        
        return false;
    }
    
    //balanceia na inserção
    private void balance(NodeRN node) throws InvalidPositionException{
        
        //se o nó nao tem pai, retorna
        if(node.getParent() == null)
            return;
        
        //pega o pai do nó
        NodeRN father = node.getParent();
        
        //pega o tio do nó
        NodeRN uncle = getSibling(father);
        
        //pai é rubro
        if(father.getColor() == 1){
            //tio não é nulo e é rubro
            if(uncle != null && uncle.getColor() == 1)
                case2(node); 
            //se não
            else{
                //se o pai é filho esquerdo
                if(isLeftChild(father)){
                    //se o nó é filho esquerdo
                    if(isLeftChild(node))
                        case3a(father.getParent());                    
                    //se o nó é filho direito
                    else                     
                        case3d(father.getParent());
                }
                //se o pai é filho direito
                else{
                    //se o nó é filho esquerdo
                    if(isLeftChild(node))
                        case3c(father.getParent());                   
                    //se o nó é filho direito
                    else
                        case3b(father.getParent());
                }
            }                           
        }
        //força o root a ser negro ao final de qualquer operação
        root.setColor(0);
    }

    //balanceia na remoção
    private void balanceRemove(NodeRN node) throws InvalidPositionException{
        //pega o pai do nó
        NodeRN father = node.getParent();
        
        //se o pai for nulo
        if(father == null)
            return;
        
        NodeRN sibiling = getSibling(node);
        
        //se nó é rubro
        if(node.getColor() == 1){
            //se o irmão é negro ou nulo
            if(sibiling == null || sibiling.getColor() == 0){
                //pinta nó de negro
                node.setColor(0);
                
            }                          
        }
        //nó é negro
        else{
            //irmão é rubro e pai é negro
            if(sibiling != null && sibiling.getColor() == 1 && father.getColor() == 0){
                node.changeDoubleBlack(true);
                RSE(father);
                sibiling.setColor(0);
                father.setColor(1);
            }
            else if((sibiling != null || sibiling.getColor() == 0) && childrenAreBlack(sibiling) && father.getColor() == 0){
                sibiling.setColor(1);
                node.changeDoubleBlack(false);
                father.changeDoubleBlack(true);
            }
            else if((sibiling != null || sibiling.getColor() == 0) && childrenAreBlack(sibiling) && father.getColor() == 1){
                sibiling.setColor(1);
                father.setColor(0);
                node.changeDoubleBlack(false);
            }
        }
        balanceRemove(father);
    }
    
    //caso 2
    private void case2(NodeRN node) throws InvalidPositionException{
        NodeRN father = node.getParent();
        NodeRN uncle = getSibling(father);
        NodeRN grandpa = father.getParent();
        
        changeColor(father);
        changeColor(uncle);
        changeColor(grandpa); 
        
        //chama recursivamente o balance passando o avô
        balance(grandpa);
    }    
    
    //caso 3a
    private void case3a(NodeRN node) throws InvalidPositionException{
        //faz uma rotação no nó
        RSD(node);
        
        //muda a cor do nó pra rubro
        node.setColor(1);
        //muda a cor do pai do nó pra negro
        node.getParent().setColor(0);         
    }
    
    //caso 3b
     private void case3b(NodeRN node) throws InvalidPositionException{
        //faz uma rotação no nó
        RSE(node);
        
        //muda a cor do nó pra rubro
        node.setColor(1);
        //muda a cor do pai do nó pra negro
        node.getParent().setColor(0);   
    }   
    
    //caso 3c
     private void case3c(NodeRN node) throws InvalidPositionException{
        RSD(node.getRight());
        RSE(node);
        
        //muda a cor do nó pra rubro
        node.setColor(1);
        //muda a cor do pai do nó pra negro
        node.getParent().setColor(0);    
    }   
    
    //caso 3d
    private void case3d(NodeRN node) throws InvalidPositionException{
        RSE(node.getLeft());
        RSD(node);
        
        //muda a cor do nó pra rubro
        node.setColor(1);
        //muda a cor do pai do nó pra negro
        node.getParent().setColor(0);
    }
    
    /*
    
    Métodos da arvore de pesquisa
    
     */

    @Override
    public void insert(int key, Object o) throws InvalidPositionException {
        NodeRN node = new NodeRN(key, o, null);
        if (isEmpty()) {
            root = node;
            changeColor(root);
        }
        else 
            insert(root, node);
    }

    private void insert(NodeRN aux, NodeRN node) throws InvalidPositionException {
        if (node.getKey() < aux.getKey()) {
            if (hasLeft(aux)) {
                aux = (NodeRN) getLeft(aux);
                insert(aux, node);
            } else {
                aux.setLeft(node);
                node.setParent(aux);
                balance(node);
                size++;
            }
        } else if (node.getKey() > aux.getKey()) {
            if (hasRight(aux)) {
                aux = (NodeRN) getRight(aux);
                insert(aux, node);
            } else {
                aux.setRight(node);
                node.setParent(aux);
                balance(node);
                size++;
            }
        } else {
            return;
        }
    }

    private boolean isLeftChild(NodeRN node) {
        if(node.getParent() == null)
            return false;
        return node.getKey() < node.getParent().getKey();
    }

    @Override
    public NodeRN remove(int key) throws InvalidPositionException {
        NodeRN node = find(key);
        NodeRN r = remove(node);
        
        if(r == null)
            return null;
        if(r.getParent() == null)
            root = r;
        
        balanceRemove(r);
        
        return r;
    }

    private NodeRN remove(NodeRN node) throws InvalidPositionException {
        if (node != null) {         
            
            //o nó não tem filhos
            if (isExternal(node)) {
                if (isLeftChild(node)) 
                    node.getParent().setLeft(null);
                else 
                    node.getParent().setRight(null);
                
                size--;
                return node;
            }
            //o nó tem 1 filho (esquerdo)
            if (hasLeft(node) && !hasRight(node)) {

                if (isLeftChild(node)) 
                    node.getParent().setLeft(node.getLeft());
                else 
                    node.getParent().setRight(node.getLeft());
                

                node.getLeft().setParent(node.getParent());
                size--;
                return node;
            }
            //o nó tem um filho (direito)
            if (!hasLeft(node) && hasRight(node)) {
                if (isLeftChild(node))
                    node.getParent().setLeft(node.getRight());
                else 
                    node.getParent().setRight(node.getRight());
                

                node.getRight().setParent(node.getParent());
                size--;
                return node;
            }
            
            //o nó tem dois filhos
            NodeRN aux = node.getRight(); //pega o filho da direita
            while (aux.getLeft() != null){
                //pega o ultimo filho da esquerda desse nó ou ele mesmo
                aux = aux.getLeft();
            }
            
            Object element = aux.getElement();//pega o conteudo do nó a ser removido
            int key = aux.getKey();
            remove(aux);//remove esse nó recursivamente
            node.setElement(element);//restaura o conteudo do nó removido 
            node.setKey(key);//restaura a key do nó

            size--;
            return node;
        }
        return null;
    }

    @Override
    public NodeRN find(int key) throws InvalidPositionException {
        if (!isEmpty()) 
            return find(key, root);
        
        return null;
    }

    private NodeRN find(int key, NodeRN node) throws InvalidPositionException { 
        if(node == null)
            return null;
        
        if(key == node.getKey())
            return node;
        
        if (key < node.getKey()) 
            return find(key, node.getLeft());                  
        else
            return find(key, node.getRight());               
    }

    @Override
    public NodeRN getLeft(Position p) throws InvalidPositionException {
        NodeRN no = (NodeRN) p;
        return no.getLeft();
    }

    @Override
    public NodeRN getRight(Position p) throws InvalidPositionException {
        NodeRN no = (NodeRN) p;
        return no.getRight();
    }

    @Override
    public NodeRN getSibling(Position p) throws InvalidPositionException {
        NodeRN no = (NodeRN) p;
        NodeRN father = no.getParent();
        
        if(father == null)
            return null;
      
        if(isLeftChild(no))
            return father.getRight();
        else
            return father.getLeft();
        
    }

    @Override
    public boolean hasLeft(Position p) throws InvalidPositionException {
        NodeRN no = (NodeRN) p;
        return (no.getLeft() != null);
    }

    @Override
    public boolean hasRight(Position p) throws InvalidPositionException {
        NodeRN no = (NodeRN) p;
        return (no.getRight() != null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height(Position p) {
        NodeRN node = (NodeRN) p;
        if (isExternal(node)) {
            return 0;
        } else {
            Iterator itr = children(node);
            int h = 0;
            while (itr.hasNext()) {
                NodeRN noChild = (NodeRN) itr.next();
                h = Math.max(h, height(noChild));
            }
            return 1 + h;
        }
    }

    @Override
    public int depth(Position p) {

        NodeRN node = (NodeRN) p;

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
        NodeRN node = (NodeRN) p;
        Iterator<NodeRN> children = children(node);
        while (children.hasNext()) {
            NodeRN n = (NodeRN) children.next();
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

    private Vector<NodeRN> inOrderNos(Position p) {
        Vector<NodeRN> vector = new Vector();
        NodeRN node = (NodeRN) p;
        Iterator<NodeRN> children = children(node);
        while (children.hasNext()) {
            NodeRN n = (NodeRN) children.next();
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
        NodeRN no = (NodeRN) p;
        return no.getParent();
    }

    @Override
    public Iterator children(Position p) {
        Vector<NodeRN> vector = new Vector<>();
        NodeRN node = (NodeRN) p;

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
        NodeRN node = (NodeRN) p;
        return node.getLeft() == null && node.getRight() == null;
    }

    @Override
    public boolean isInternal(Position p) {
        return !isExternal(p);
    }

    @Override
    public boolean isRoot(Position p) {
        NodeRN node = (NodeRN) p;
        return node == root;
    }

    @Override
    public Object replace(Position p, Object o) {
        NodeRN node = (NodeRN) p;
        Object aux = node.getElement();
        node.setElement(o);
        return aux;
    }

    public void mostrar() {
        System.out.println("\n\n\n\n");
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

    private void mostrarRecursao(NodeRN node, int profundidade, ArrayList<StringBuffer> a) {
        if (node == null) {
            return;
        }

        mostrarRecursao(node.getLeft(), profundidade + 1, a);
        for (int i = 0; i < height(root) + 1; ++i) {
            if (i == profundidade) {
                String color = ANSI_RED;
                if(node.getColor() == 0)
                    color = ANSI_BLACK;
                if(node.isDoubleBlack())
                    color = ANSI_DB;
                a.get(i).append(color + node.getKey() + ANSI_RESET);
            } else {
                a.get(i).append("  ");
            }
        }
        mostrarRecursao(node.getRight(), profundidade + 1, a);
    }

}