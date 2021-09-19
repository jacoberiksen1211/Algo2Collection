package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPathManhattan {
    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        Node[][] array = new Node[n][n];
        ArrayList<Node> pQueue = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            

        }
    }
}
class Node {
    ArrayList<Node> vertices;

    public Node() {
        this.vertices = new ArrayList<>();
    }
}