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

    /*
    eulerian path method

    grau=0; soma=0; matadj[][]; N=numero de linhas da matriz; f=0;//linha atual;
    Enquanto(soma<=2)e(f<=N) {
        grau=0;
        para(g=0;g<N;g++){
        grau+=matadj[f][g];
        }
        se grau mod 2 == 1 // ímpar
        soma++
        f++;
    }
    Se (soma>2) NÃO EXISTE CAMINHO
    Senao EXISTE CAMINHO

    */
    public boolean isEulerian(){
        int grade = 0;
        int sum = 0;
        int f = 0;
        while(sum <= 2 && f<= qtdVertices){
           grade = 0;

           for(int g=0; g < qtdVertices; g++)   
            grade += matrixAjd[f][g].getValue(); 
           
           if((grade % 2) == 1)
            sum++;
           
           f++;    
        }
        if(sum > 2)
            return false;
        
        return true; 
    }

    /*
        DFS
        
        Para cada vértice v pertencente a V faça
        v.marcar=0;//não visitado
        d(v)=0; t=0;
        Para cada vértice v pertencente a V faça
        se(v.marcar==0)
         DFS-VISITA(v);
        DFS-VISITA(v){
        v.marcar=-1;// visitado
        d(v)=++t;
        Para cada vértice w pertencente a ADJ[v] faça
         se (w.marcar==0)
         DFS-VISITA(w);
        v.marcar=1; // marcado
        s(v)=++t;
        }  
    
    */


    /*
        BFS

        Para cada vértice v pertencente a V –{S} faça
        v.marcar=0;//não visitado
        v.marcar=-1;// marcado
        d(s)=0;
        Q={}
        Q.enqueue(S);
        Enquanto(Q<>vazio){
        v=Q.dequeue();
        Para cada vértice w adjacente a ADJ[v] faça
         se(w.marcar==0){
         d(w)=d(v)+1;
         w.marcar=-1;
         Q.enqueue(w);
         }
        v.marcar=1; // marcado
        }
    */
    
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
 
    @Override
    public void insertVertex(Vertex vertex) {
        qtdVertices++;
        vertices.add(vertex);
        Edge tempMatrixAdj[][] = new Edge[qtdVertices][qtdVertices];
        
        for(int f = 0; f < qtdVertices - 1; f++){
            for(int g = 0; g < qtdVertices - 1; g++)
                  tempMatrixAdj[f][g] = matrixAjd[f][g];   
        }
                   
        for(int g = 0;g <qtdVertices-1;g++)
            tempMatrixAdj[qtdVertices-1][g] = tempMatrixAdj[g][qtdVertices-1] = null;          
                
        matrixAjd = tempMatrixAdj;        
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

    @Override
    public void removeArc(Edge edge) {
        int index1 = findIndex(edge.getVertexFrom().getKey());
        int index2 = findIndex(edge.getVertexTo().getKey());
        matrixAjd[index1][index2] = null;      
    }
    
    @Override
    public int grade(Vertex vertex) {
        return incidentEdges(vertex).size();
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
    
    public Vector<Vertex> getNeighbors(Vertex vertex){
        Vector<Vertex> v = new Vector();
        int index = findIndex(vertex.getKey());
        for (int i = 0; i < qtdVertices; i++) {
            if(matrixAjd[index][i] != null)
                v.add((Vertex)vertices.get(i));          
        }
        return v;
    } 
    
    public void showVertices(){
        for(int f=0;f<vertices.size();f++)
        System.out.print(vertices.elementAt(f)+","); 
    }
  
    public void showMatrix(){
        for(int f=0;f<qtdVertices;f++){
            for(int g=0;g<qtdVertices;g++){
                Edge edge = matrixAjd[f][g];
                if(edge != null)
                    System.out.print(matrixAjd[f][g].getVertexFrom().getKey()+
                            "-"+matrixAjd[f][g].getVertexTo().getKey()+" ");
                else
                    System.out.print("null ");
            }
            System.out.println();
        }        
    }
}
