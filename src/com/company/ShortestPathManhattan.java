package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPathManhattan {
    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        array = new Node[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = new Node();
            }
        }
        //setup right
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                array[i][j].right = scanner.nextInt();
            }
        }

        //setup left
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j].down = scanner.nextInt();
            }
        }
        int weight = 0;
        int i = 0;
        int j = 0;

        /*while(i < n-1 || j < n-1){
            if(j == n-1){
                // if on right edge
                i++;
                weight += current.down;

            }
            else if(i == n-1){
                //if on bottom edge
                j++;
                weight += current.right;
            }
            else{
                if(current.right < current.down){
                    i++;
                    weight += current.down;
                }
                else{
                    j++;
                    weight += current.right;
                }
            }*/
            //current = array[i][j];
        }
        System.out.println("path done: " + weight);

    }
    public static Node[][] array;
    public static ArrayList<Node> pQueue = new ArrayList<>();
    public static int longestPath(int i, int j) {
        Node current = array[i][j];


    }
}
class Node {
    int right, down;
    Node father;
    int weight;
    public Node() {
        weight = 0;
        father = null;
    }
}