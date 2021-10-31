package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxFlow {
    public static Vertice start, end;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //take input / build graph:
        int v = Integer.parseInt(scanner.nextLine());
        int e = Integer.parseInt(scanner.nextLine());
        ArrayList<Vertice> graph = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {// initiate vertices
            graph.add(new Vertice(i));
        }
        // initiate edges
        int from = 0, to = 0, c = 0;
        for (int i = 0; i < e; i++) {
            from = scanner.nextInt();
            to = scanner.nextInt();
            c = scanner.nextInt();
            //add edge
            graph.get(from).edges.add(new Edge(graph.get(to), c, true));
            //add backwards edge
            graph.get(to).edges.add(new Edge(graph.get(from), 0, false));
        }
        
        int maxflow = 0;
        while(true){
            //run bfs to find path
            bfs(graph, 0);
            for(Vertice temp : graph){
                temp.visited = false;
            }
        }

    }
    public static int maxflow = 0;
    private static boolean bfs(ArrayList<Vertice> graph, int s){
        ArrayList<Vertice> queue = new ArrayList<>();
        queue.add(graph.get(s));
        int x = 0;
        boolean pathfound = false;
        // while still nodes reachable
        while1:
        while(x < queue.size()){
            Vertice temp = queue.get(x);
            if(temp.visited == false){
                System.out.println("currentnode =" + temp.id);

                temp.visited = true;
                //add edges to path
                Edge tempEdge = null;
                for (int i = 0; i < temp.edges.size(); i++) {
                    tempEdge = temp.edges.get(i);
                    if(tempEdge.flow < tempEdge.capacity) {
                        queue.add(tempEdge.direction);
                        tempEdge.direction.father = tempEdge;
                        if (temp.edges.get(i).direction.father.direction == end) {
                            pathfound = true;
                            break while1;
                        }
                    }
                }
            }
            x++;
        }
        if(!pathfound){
            return false;
        }
        ArrayList<Edge> path = new ArrayList<>();
        Vertice temp = end;
        int pathLimit = Integer.MAX_VALUE;
        while(temp != start){
            path.add(temp.father);
            //go through path and find limit and add that flow
            if (temp.father.capacity < pathLimit) {
                pathLimit = temp.father.capacity;
            }
        }
        for(Edge e: path){
            e.capacity -= pathLimit;
        }
        maxflow += pathLimit;
        return true;
    }
}

class Vertice{
    boolean visited = false;
    int id;
    Edge father = null;
    ArrayList<Edge> edges = new ArrayList<>();
    public Vertice(int id){
        this.id = id;
    }
}
class Edge{
    boolean forward;
    Vertice direction;
    Edge bro;
    int capacity, flow;
    public Edge(Vertice to, int c, boolean forward){
        this.capacity = c;
        this.direction = to;
        flow = 0;
        this.forward = forward;
    }
}