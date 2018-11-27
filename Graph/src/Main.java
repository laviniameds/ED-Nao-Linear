
import graph.Graph;
import graph.Vertex;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lavinia
 */
public class Main {
    public static void main(String args[]){
        Graph g = new Graph();
        Vertex v1 = new Vertex(1, 25);
        Vertex v2 = new Vertex(2, 40);
        Vertex v3 = new Vertex(3, 90);
        Vertex v4 = new Vertex(4, 80);
        
        g.insertVertex(v1);
        g.insertVertex(v2);
        g.insertVertex(v3);
        g.insertVertex(v4);

        
        g.insertEdge(v1, v2);
        g.insertEdge(v2, v3);
        g.insertEdge(v3, v4);
        g.insertEdge(v4, v1);
        
        g.showMatrix();
        
        //show graph
        
        JFrame f = new JFrame("GraphPanel");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(800, 400));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        //Graphics draw = new Graphics();
        //draw.drawLine(10, 20, 50, 30);
    }
}
