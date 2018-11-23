/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import graph.interfaces.IGraph;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author lavinia
 */
public class Graph implements IGraph{
    
    private int qtdVertices;
    private Vector vertices;
    private Edge matrixAjd[][];
    
    public Graph(){
        this.qtdVertices = 0;
        this.vertices = new Vector();
    }
    
    public int findIndex(int key){
        Iterator I = vertices.iterator();
        int index = 0;        
        while(I.hasNext()){     
            Vertex v = (Vertex)(I.next());            
            if(v.getKey()== key)// achei
                return index;
            index++;
        }
        return -1; // nao achei
    }

    /*
    TO DO
    */ 
    @Override
    public void insertVertex(Vertex vertex) {
        
    }

    @Override
    public void removeVertex(Vertex vertex) {
       qtdVertices--;
       int index = findIndex(vertex.getKey());
       vertices.remove(index);  // remove o vertice do vector    
       // remove linhas e colunas da matriz de adjac�ncia
       Edge tempMatrixAdj[][] = new Edge[qtdVertices][qtdVertices];
       int ff = 0,gg;
       for(int f=0;f<qtdVertices+1;f++){
           gg=0;
           for(int g=0;g<qtdVertices+1;g++){
               if(f!=index && g!=index){
                 tempMatrixAdj[ff][gg] = this.matrixAjd[f][g];                  
                 if(g!=index)
                     gg++;                  
               }                
           }
           if(f!=index)
               ff++;
       }
       this.matrixAjd=tempMatrixAdj;       
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, double value) {
        Edge edge = new Edge(vertex1, vertex2, value); 
        
        int index1 = findIndex(vertex1.getKey());
        int index2 = findIndex(vertex2.getKey());
        
        matrixAjd[index1][index2] = matrixAjd[index2][index1]= edge; // grafo nao orientado
        return edge;        
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2) {
        Edge edge = new Edge(vertex1, vertex2); 
        
        int index1 = findIndex(vertex1.getKey());
        int index2 = findIndex(vertex2.getKey());
        
        matrixAjd[index1][index2] = matrixAjd[index2][index1]= edge; // grafo nao orientado
        return edge;          
    }

    @Override
    public void removeEdge(Edge edge) {
        int index1 = findIndex(edge.getVertexFrom().getKey());
        int index2 = findIndex(edge.getVertexTo().getKey());
        
        matrixAjd[index1][index2] = matrixAjd[index2][index1] = null; // grafo nao orientado       
    }

    @Override
    public Edge insertArc(Vertex vertex1, Vertex vertex2, double value) {
       Edge edge = new Edge(vertex1, vertex2, value); 

       int index1 = findIndex(vertex1.getKey());
       int index2 = findIndex(vertex2.getKey());
       
       matrixAjd[index1][index2] = edge; // grafo orientado
       return edge;       
    }

    @Override
    public Edge insertArc(Vertex vertex1, Vertex vertex2) {
       Edge edge = new Edge(vertex1, vertex2, 0, true); 

       int index1 = findIndex(vertex1.getKey());
       int index2 = findIndex(vertex2.getKey());
       
       matrixAjd[index1][index2] = edge; // grafo orientado
       return edge;       
    }

    /*
    TO DO
    */  
    @Override
    public void removeArc(Edge edge) {
        
    }
    
    /*
    TO DO
    */ 
    @Override
    public int grade(Vertex vertex) {
        return 0;
    }

    @Override
    public int order() {
        return qtdVertices;
    }

    @Override
    public Vector vertices() {
        return vertices;
    }

    @Override
    public Vector edges() {
        Vector v = new Vector();
        for(int l=0;l<qtdVertices;l++)
            for(int c=0;c<qtdVertices;c++)                
                v.add(matrixAjd[l][c]);
        return v;       
    }
    
    public Edge getEdge(Vertex vertex1, Vertex vertex2){
        int index1 = findIndex(vertex1.getKey());
        int index2 = findIndex(vertex2.getKey());
        
        return (matrixAjd[index1][index2]);        
    }  
    
    @Override
    public Vector incidentEdges(Vertex vertex) {
        Iterator I = edges().iterator();
        Vector<Edge> vectorEdges = new Vector<>();
        
        while(I.hasNext()){     
            Edge edge = (Edge)(I.next());            
            if(edge.getVertexTo().getKey() == vertex.getKey())
                vectorEdges.add(edge);
        }
        
        return vectorEdges;
    }

    @Override
    public Vector lastVertices(Edge edge) {
        Vector v=new Vector();
        v.add(edge.getVertexFrom());
        v.add(edge.getVertexTo());
        return v;        
    }
    
    @Override
    public Vertex opposite(Vertex vertex, Edge edge) {
        Vertex from = edge.getVertexFrom();
        Vertex to = edge.getVertexTo();
        
        if(vertex.getKey() == from.getKey()){
            if(isAdjacent(vertex, to))
                return to;
        }
        else if(vertex.getKey() == to.getKey()){
            if(isAdjacent(vertex, from))
                return from;            
        }
        
        return null;
    }

    @Override
    public boolean isAdjacent(Vertex vertex1, Vertex vertex2) {
        int index1 = findIndex(vertex1.getKey());
        int index2 = findIndex(vertex2.getKey());
        
        return (matrixAjd[index1][index2])!= null;       
    }
    
    public void showVertices(){
        for(int f=0;f<vertices.size();f++)
        System.out.print(vertices.elementAt(f)+","); 
    }
  
    public void showMatrix(){
        for(int f=0;f<qtdVertices;f++){
            for(int g=0;g<qtdVertices;g++)
               System.out.print(matrixAjd[f][g]+" ");
            System.out.println();
        }        
    }
}
