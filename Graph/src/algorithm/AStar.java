/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import graph.Graph;
import graph.Vertex;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lavinia
 */
public class AStar {
    
    //f(n) = g(n)                              +    h(n)
    //     = actual cost from begining to end       heuristic
    
    public static void main(String[] args) throws IOException{
            
        //modelo de grid onde o algoritmo vai rodar
        String model = new String(Files.readAllBytes(Paths.get("model.txt")));
        
        //interpreta o modelo como um grid e guarda sua altura e largura
        String[] grid = model.split("\n");
        int height = grid.length;
        int width = grid[0].length();
        
        //cria uma matriz de vertices
        Vertex[][] matrixVertices = new Vertex[height][width];
        
        //cria uma instancia da classe Graph
        Graph graph = new Graph();
        
        //cria um contador para as chaves
        int countVertices = 1;
                      
        //cria o primeiro e o ultimo vertice (node) como nulo
        Vertex start = null;
        Vertex end = null;
        
        //popula a matriz de vertices de acordo com o grid de chars
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++){
                //cria o vertice passando o char correspondente
                Vertex vertex = new Vertex(countVertices++, grid[i].charAt(j), 0, 0, 0, 0, 0);
                
                //insere o vertice no graph e na matriz de vertices
                graph.insertVertex(vertex);
                matrixVertices[i][j] = vertex;
                
                //grava o vertice inicial e o final
                if(vertex.getC() == 's')
                    start = vertex;
                if(vertex.getC() == 'e')
                    end = vertex;
            }
        }
        
        //adiciona as arestas (vizinhos)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
//               if(i > height-1){
//                   graph.insertEdge(matrixVertices[i][j], matrixVertices[i+1][j]);
//               }
//               if(i > 0){
//                   graph.insertEdge(matrixVertices[i][j], matrixVertices[i-1][j]);
//               }
//               if(j < width-1){
//                   graph.insertEdge(matrixVertices[i][j], matrixVertices[i][j+1]);
//               }
//               if(j > 0){
//                   graph.insertEdge(matrixVertices[i][j], matrixVertices[i][j-1]);
//               }
               
//               //Diagonal
               if(i > 0 && j > 0){
                   graph.insertEdge(matrixVertices[i][j], matrixVertices[i-1][j-1]);
               }
               if(i < height-1 && j > 0){
                   graph.insertEdge(matrixVertices[i][j], matrixVertices[i+1][j-1]);
               }
               if(i > 0 && j < width - 1){
                    graph.insertEdge(matrixVertices[i][j], matrixVertices[i-1][j+1]); 
               }
               if(i < height - 1 && j < width - 1){
                    graph.insertEdge(matrixVertices[i][j], matrixVertices[i+1][j+1]); 
               }
            }
        }
        
        //cria o openSet e o closedSet         
        Vector<Vertex> openSet = new Vector<>();
        Vector<Vertex> closedSet = new Vector<>(); 
        //adiciona o primeiro vertice no openSet
        openSet.add(start);
        
        //cria um vetor para armazenas as chaves do caminho final
        int path[] = new int[countVertices];
        
        //enquanto houver vertices a seres visitados
        while(!openSet.isEmpty()){
            
            //percorre todo o openSet procurando o menor indice, armazena ele em "current" (atual)
            Vertex current = openSet.get(0);
            for (Vertex vertex : openSet) {
                if(vertex.getF() < current.getF())
                    current = vertex;
            }
            
            //se o atual for igual ao vertice final, o algoritmo acaba pois achou seu fim
            if(current.getC() == end.getC()){
                System.err.println("Done!");
                
                //atualiza o path final                
                //pega o vertice atual (ultimo do path)
                int temp = current.getKey();
                //pega os vertices adicionados ao grafo
                Vector<Vertex> vertices = graph.vertices();
                //enquanto temp não for o início
                while(temp != start.getKey()){
                    //pega o a key do vertice do path
                    temp = path[temp];
                    //muda o char do vertice anterior para identificá-lo como path
                    vertices.get(temp-1).setC('#');
                }
                //coloca a # no final tbm
                vertices.get(path[end.getKey()]).setC('#');
                
                //imprime o a matriz com o path final
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        Vertex v = matrixVertices[i][j];
                        System.out.print(v.getC());
                    }
                    System.out.print('\n');
                }

                break;
            }
            
            //se ainda nao terminou
            //remove o vertice atual do openSet
            openSet.remove(current);
            //adiciona o vertice atual no closedSet
            closedSet.add(current);
            
            //cria um vetor com os vizinhos do vertice
            Vector<Vertex> neighbors = graph.getNeighbors(current);
            //para cada vizinho
            for (Vertex neighbor : neighbors) {
                //se o vizinho nao esta no closedSet ou é um obstáculo (wall 'w')
                if(!closedSet.contains(neighbor) && neighbor.getC() != 'w'){
                    //armazena o G temporario + 1
                    int tempG = current.getG()+1;
                    
                    //cria uma variavel para identificar se existe um novo caminho
                    boolean newPath = false;
                            
                    //se o vertice esta no openSet (esta sendo revisitado)
                    if(openSet.contains(neighbor)){
                        //se o G temporario atual é menor q o G do vizinho, atualiza o G
                        if(tempG < neighbor.getG()){
                            neighbor.setG(tempG);
                            newPath = true;
                        }
                    }
                    //se não
                    else {
                        //atualiza o G e adiciona no openSet
                        neighbor.setG(tempG);
                        openSet.add(neighbor);
                        newPath = true;
                    }
                    
                    if(newPath){
                        //atualiza o H (calculando a heuristica) e o F (somando G e H)
                        neighbor.setH(getHeuristic(0, neighbor, end));
                        neighbor.setF(neighbor.getG()+neighbor.getH());
                        path[neighbor.getKey()] = current.getKey(); 
                    }
                }
            }
        } 
        //no solution
    }
    
    //tipo '0' para euclides e '1' para manhattan
    public static int getHeuristic(int kind, Vertex v1, Vertex v2){
        
        //distancia de euclides
        if(kind == 0){
            return (int)Math.hypot(v1.getX() - v2.getX(), v1.getY()-v2.getY());
        }
        //distancia de manhattan
        if(kind == 1){
            return Math.abs(v1.getX() - v2.getX()) + Math.abs(v1.getY() - v2.getY());
        }
        
        return 1;
    }
    
}
