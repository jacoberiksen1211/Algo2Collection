package com.company;

import java.util.Scanner;

public class FoodTrucksM1 {
    public static void main(String[] args) {
        int numPos, numDays;
        Scanner scanner = new Scanner(System.in);
        numPos = scanner.nextInt(); // = n
        numDays = scanner.nextInt(); // = m
        int[][] input = new int[numDays][numPos];

        if(numPos <= 3){
            //only 3 positions so sum all
            long sum = 0;
            for (int i = 0; i < numDays; i++) {
                for (int j = 0; j < numPos; j++) {
                    sum += scanner.nextInt();
                }
            }
            System.out.println(sum);
        }
        else if(numDays == 1){
            int top1 = 0, top2 = 0, top3 = 0;
            //only 1 day so grab top 3 numbers and sum
            for (int i = 0; i < numPos; i++) {
                int temp = scanner.nextInt();
                if(temp > top1){
                    top3 = top2;
                    top2 = top1;
                    top1 = temp;
                }
                else if(temp > top2){
                    top3 = top2;
                    top2 = temp;
                }
                else if(temp > top3){
                    top3 = temp;
                }
            }
            long sum = top1+top2+top3;
            System.out.println(sum);
        }
        else{
            //take input
            for (int i = 0; i < numDays; i++) {
                for (int j = 0; j < numPos; j++) {
                    input[i][j] = scanner.nextInt();
                }
            }
            long sum = 0;
        }
    }


}
