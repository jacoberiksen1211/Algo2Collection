package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPathManhattan {
    public static void main(String[] args) {
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
        weight = longestPath(0, 0);
        System.out.println(weight);

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

    public static Node[][] array;
    public static ArrayList<Node> pQueue = new ArrayList<>();
    public static int n;
    public static int longestPath(int i, int j) {
        Node current = array[i][j];
        if(i == n-1 && j == n-1) {
            //at the END return weight
            return current.weight;
        }
        else if(i == n-1){
            //if on bottom edge attempt to go right
            if(current.weight + current.right > array[i][j+1].weight){
                // if current path to right is heavier than others then continue
                array[i][j+1].father = current;
                array[i][j+1].weight = current.weight + current.right;
                return longestPath(i, j+1);
            }
            else return 0;
        }
        else if(j == n-1){
            //if on right edge attempt go down
            if(current.weight + current.down > array[i+1][j].weight){
                // if current path to right is heavier than others then continue
                array[i+1][j].father = current;
                array[i+1][j].weight = current.weight + current.down;
                return longestPath(i+1, j);
            }
           else return 0;
        }
        else {
            //if down and right is possible
            int right = 0;
            int down = 0;
            if(current.weight + current.down > array[i+1][j].weight){
                // if current path down is heavier than others then continue
                array[i+1][j].father = current;
                array[i+1][j].weight = current.weight + current.down;
                down = longestPath(i+1, j);
            }
            if(current.weight + current.right > array[i][j+1].weight){
                // if current path to right is heavier than others then continue
                array[i][j+1].father = current;
                array[i][j+1].weight = current.weight + current.right;
                right = longestPath(i, j+1);
            }
            return Integer.max(down, right);
            }
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