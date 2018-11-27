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
        Vertex[][] vertices = new Vertex[height][width];
        
        //cria uma instancia da classe Graph
        Graph graph = new Graph();
        
        //cria um contador para as chaves
        int count = 0;
                      
        //cria o primeiro e o ultimo vertice (node) como nulo
        Vertex start = null;
        Vertex end = null;
        
        //popula a matriz de vertices de acordo com o grid de chars
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++){
                //cria o vertice passando o char correspondente
                Vertex vertex = new Vertex(++count, grid[i].charAt(j), 0, 0, 0, 0, 0);
                
                //insere o vertice no graph e na matriz de vertices
                graph.insertVertex(vertex);
                vertices[i][j] = vertex;
                
                //grava o vertice inicial e o final
                if(vertex.getC() == 's')
                    start = vertex;
                if(vertex.getC() == 'e')
                    end = vertex;
            }
        }
        
        //cria o openSet e o closedSet         
        Vector<Vertex> openSet = new Vector<>();
        Vector<Vertex> closedSet = new Vector<>(); 
        //adiciona o primeiro vertice no openSet
        openSet.add(start);
        
        //enquanto houver vertices a seres visitados
        while(!openSet.isEmpty()){
            
            //percorre todo o openSet procurando o menor indice, o menor é o "winner"
            Vertex winner = openSet.get(0);
            for (Vertex vertex : openSet) {
                if(vertex.getF() < winner.getF())
                    winner = vertex;
            }
            
            //se o "winner" for igual ao vertice final, o algoritmo acaba pois achou seu fim
            if(winner.getC() == end.getC()){
                System.err.println("Done!");
                break;
            }
        }
        //não tem solução
        
    }
    
}
