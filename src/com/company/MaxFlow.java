package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class MaxFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = Integer.parseInt(scanner.nextLine());
        int e = Integer.parseInt(scanner.nextLine());
        ArrayList<Vertice> graph = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            graph.add(new Vertice(i));
        }
        graph.get(0).isStart = true;
        graph.get(1).isEnd = true;
        int from = 0, to = 0, c = 0;
        for (int i = 0; i < e; i++) {
            from = scanner.nextInt();
            to = scanner.nextInt();
            c = scanner.nextInt();
            graph.get(from).edges.add(new Edge(graph.get(to), c));
        }
        bfs(graph, 0);
    }

    private static void bfs(ArrayList<Vertice> graph, int s){
        ArrayList<Vertice> queue = new ArrayList<>();
        queue.add(graph.get(s));
        int x = 0;

        while(x < queue.size()){
            Vertice temp = queue.get(x);
            if(temp.visited == false){
                System.out.println("currentnode =" + temp.id);

                temp.visited = true;
                for (int i = 0; i < temp.edges.size(); i++) {
                    queue.add(temp.edges.get(i).direction);
                }
            }
            x++;
        }
    }
}

class Vertice{
    boolean visited = false;
    int id;
    boolean isStart = false;
    boolean isEnd = false;
    ArrayList<Edge> edges = new ArrayList<>();
    public Vertice(int id){
        this.id = id;
    }
}
class Edge{
    Vertice direction;
    int capacity, flow;
    public Edge(Vertice to, int c){
        this.capacity = c;
        this.direction = to;
        flow = 0;
    }
}