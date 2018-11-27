/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
public class View {
    public static void main(String[] args) {
        String map = null;
        try {
            map = new String(Files.readAllBytes(Paths.get("map.dat")));
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] maze = map.split("\n");
        Graph s = new Graph();
        int n = 1;
        int h=maze.length;
        int w=maze[0].length();
        Vertex vertices[][] = new Vertex[h][w];
        Vertex start = null;
        Vector<Vertex> outs = new  Vector();
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
               Vertex v = new Vertex(n++,maze[i].charAt(j), i,j,0,0,0);

               s.insertVertex(v);
               vertices[i][j] = v;
               start = v.getC() == '2'?v:start;
               if(v.getC()=='3') outs.add(v);
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
               if(i-1 > -1){
                   s.insertEdge(vertices[i][j], vertices[i-1][j]);
               }
               if(i+1 < h){
                   s.insertEdge(vertices[i][j], vertices[i+1][j]);
               }
               if(j-1 > -1){
                   s.insertEdge(vertices[i][j], vertices[i][j-1]);
               }
               if(j+1 < w){
                   s.insertEdge(vertices[i][j], vertices[i][j+1]);
               }

//               //Horizontal
//               if(i-1 > -1 && j-1 > -1){
//                   s.insertEdge(vertices[i][j], vertices[i-1][j-1]);
//               }
//               if(i-1 > -1 && j+1 <w){
//                   s.insertEdge(vertices[i][j], vertices[i-1][j+1]);
//               }
//               if(i+1 < h && j+1 < w){
//                   s.insertEdge(vertices[i][j], vertices[i+1][j+1]);
//               }
//               if(i+1 < h && j-1 < -1){
//                   s.insertEdge(vertices[i][j], vertices[i+1][j-1]);
//               }
            }
        }
        int path[] = new int[n];
        Vector<Vertex> openSet = new Vector<>();
        Vector<Vertex> closedSet = new Vector<>();
        openSet.add(start);
       
         
        while(openSet.size()> 0) {
            Vertex current = openSet.get(0);
            for (Vertex vertex : openSet) {
                if(vertex.getF() < current.getF())
                    current = vertex;
            }

            if(current.getC() == '3'){
                System.out.println("Finish");
                int now = current.getKey();
                Vector<Vertex> vv = s.vertices();
                while(now != start.getKey()){
                    System.out.print(now+" ");
                    now = path[now];
                    vv.get(now-1).setC('#');
                }
                vv.get(now-1).setC('2');
                System.out.print("\n");
                
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        Vertex v = vertices[i][j];
                        System.out.print(v.getC());
                    }
                    System.out.print('\n');
                }
                break;
           }

           openSet.remove(current);
           closedSet.add(current);

           Vector<Vertex> neighbors = s.neighbors(current);
            for (Vertex neighbor : neighbors) {
                if(!closedSet.contains(neighbor) && neighbor.getC() != '1'){
                    int tempG = current.getG()+1;

                    boolean newPath = false;
                    if(openSet.contains(neighbor)){
                        if (tempG < neighbor.getG()) {
                            neighbor.setG(tempG);
                            newPath = true;
                        }
                    } else {
                        neighbor.setG(tempG);
                        openSet.add(neighbor);
                        newPath = true;
                    }
                    if(newPath){
                      neighbor.setH(heuristc(neighbor, outs));
                      neighbor.setF(neighbor.getG()+neighbor.getH());
                      path[neighbor.getKey()] = current.getKey();
                    }
                }
            }
        }
    }
    public static int heuristc(Vertex neighbor,Vector<Vertex> outs) {
        int minDist = Integer.MAX_VALUE;
        for (Vertex vertex : outs) {
            // Manhattan
            int dist = Math.abs(neighbor.getX() - vertex.getX()) + Math.abs(neighbor.getY() - vertex.getY());
            // Euclidian
            /*float xdif = Math.abs(neighbor.getX() - vertex.getX());
            float ydif = Math.abs(neighbor.getY() - vertex.getY());
            int dist = (int) Math.sqrt((xdif)*(xdif) +(ydif)*(ydif));*/
            if(dist < minDist){
                minDist = dist;
            }
        }
        return minDist;
    }
}
