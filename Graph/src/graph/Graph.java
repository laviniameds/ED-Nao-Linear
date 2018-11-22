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

    @Override
    public void insertVertex(Vertex vertex) {
        
    }

    @Override
    public void removeVertex(Vertex vertex) {
        qtdVertices--;
        int indice=achaindice(Vertice.getChave());
        vertices.remove(indice);  // remove o v�rtice do vector    
        // remove linhas e colunas da matriz de adjac�ncia
        Arestas tempMatrizAdj[][]=new Arestas[qtdVertices][qtdVertices];
        int ff=0,gg;
        for(int f=0;f<qtdVertices+1;f++){
            gg=0;
            for(int g=0;g<qtdVertices+1;g++){
                if(f!=indice && g!=indice){
                  tempMatrizAdj[ff][gg]= matrizAdj[f][g];                  
                  if(g!=indice)
                      gg++;                  
                }                
            }
            if(f!=indice)
                ff++;
        }
        matrizAdj=tempMatrizAdj;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, double value) {
        
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2) {
        
    }

    @Override
    public void removeEdge(Edge edge) {
        
    }

    @Override
    public Edge insertArc(Vertex vertex1, Vertex vertex2, double value) {
        
    }

    @Override
    public Edge insertArc(Vertex vertex1, Vertex vertex2) {
        
    }

    @Override
    public void removeArc(Edge edge) {
        
    }

    @Override
    public int grade(Vertex vertex) {
        
    }

    @Override
    public int order() {
        
    }

    @Override
    public Vector vertices() {
        
    }

    @Override
    public Vector edges() {
        
    }

    @Override
    public Vector incidentEdges(Vertex vertex) {
        
    }

    @Override
    public Vector lastVertices(Edge edge) {
        
    }

    @Override
    public Vertex opposite(Vertex vertex, Edge edge) {
        
    }

    @Override
    public boolean isAdjacent(Vertex vertex1, Vertex vertex2) {
        
    }
    
//        
//    
//    public Arestas insereAresta(Vertices VerticeUm, Vertices VerticeDois,
//                             double valor){
//        Arestas A=new Arestas(VerticeUm, VerticeDois, valor);      
//        int ind1=achaindice(VerticeUm.getChave());
//        int ind2=achaindice(VerticeDois.getChave());
//        matrizAdj[ind1][ind2]=matrizAdj[ind2][ind1]=A; // grafo nao orientado
//        return A;
//    }
//
//	public Arestas insereAresta(Vertices VerticeUm, Vertices VerticeDois){		
//	    Arestas A=new Arestas(VerticeUm, VerticeDois);      
//        int ind1=achaindice(VerticeUm.getChave());
//        int ind2=achaindice(VerticeDois.getChave());
//		matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = A; // grafo nao orientado
//		return A;
//	}   
//    
//    public void removeAresta(Arestas Aresta){        
//        int ind1=achaindice(Aresta.getVerticeOrigem().getChave());
//        int ind2=achaindice(Aresta.getVerticeDestino().getChave());
//        matrizAdj[ind1][ind2]=matrizAdj[ind2][ind1]=null; // grafo nao orientado
//    }
//    
//    public Arestas insereArco(Vertices VerticeUm, Vertices VerticeDois,double valor){
//        Arestas A=new Arestas(VerticeUm, VerticeDois,valor,true);         
//        int ind1=achaindice(VerticeUm.getChave());
//        int ind2=achaindice(VerticeDois.getChave());
//        matrizAdj[ind1][ind2]=A; // grafo orientado
//        return A;
//    }
//
//	public Arestas insereArco(Vertices VerticeUm, Vertices VerticeDois){
//	    Arestas A=new Arestas(VerticeUm, VerticeDois,0,true);		
//		int ind1 = achaindice(VerticeUm.getChave());
//		int ind2 = achaindice(VerticeDois.getChave());
//		matrizAdj[ind1][ind2] = A; // grafo orientado
//		return A;
//	}
//    
//    public void removeArco(Arestas Aresta){   // grafo orientado     
//     // m�todo exerc�cio, fique a vontade para implementa-lo coleguinha   
//    }
//    
//    public void mostraVertices(){
//        for(int f=0;f<vertices.size();f++)
//            System.out.print(vertices.elementAt(f)+",");        
//    }
//    
//    public void mostraMatriz(){
//        for(int f=0;f<qtdVertices;f++){
//            for(int g=0;g<qtdVertices;g++)
//               System.out.print(matrizAdj[f][g]+" ");
//            System.out.println();
//        }        
//    }
//    
//    public int grau(Vertices Vertice){
//        // m�todo exerc�cio, fique a vontade para implementa-lo coleguinha     
//        return 0;
//    }
//    
//    public int ordem(){
//        return qtdVertices;
//    }
//    
//    private int achaindice(int chave){
//        Iterator I=vertices.iterator();
//        int ind=0;        
//        while(I.hasNext()){     
//            Vertices v=(Vertices)(I.next());            
//            if(v.getChave()==chave)// achei
//                return ind;
//            ind++;
//        }
//        return -1; // nao achei
//    }
//    
//    public Vector vertices(){
//        return vertices;
//    }
//
//    public Vector arestas(){
//        Vector v=new Vector();
//        for(int l=0;l<qtdVertices;l++)
//            for(int c=0;c<qtdVertices;c++)                
//                v.add(matrizAdj[l][c]);
//        return v;
//    }
//    
//    public Vector arestasIncidentes(Vertices vertice){
//     // m�todo exerc�cio, fique a vontade para implementa-lo coleguinha 
//        return null;
//    }
//    
//    public Vector finalVertices(Arestas a){
//        Vector v=new Vector();
//        v.add(a.getVerticeOrigem());
//        v.add(a.getVerticeDestino());
//        return v;
//    }
//    
//    public Vertices oposto(Vertices v,Arestas a) throws OpostoError {
//     // m�todo exerc�cio, fique a vontade para implementa-lo coleguinha 
//     return null;
//    }
//    
//    public boolean Adjacente(Vertices v, Vertices w){
//        int ind1=achaindice(v.getChave());
//        int ind2=achaindice(w.getChave());
//        return (matrizAdj[ind1][ind2])!=null;
//        
//    }
//    
//    public Arestas getAresta(Vertices v, Vertices w){
//        int ind1=achaindice(v.getChave());
//        int ind2=achaindice(w.getChave());
//        return (matrizAdj[ind1][ind2]);        
//    }
//    
}
