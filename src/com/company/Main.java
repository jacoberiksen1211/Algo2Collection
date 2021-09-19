package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //warmup();
        chocolate_start();
    }

    private static void warmup(){
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = scanner.nextInt();
        }
        for(int i = n - 1; i >= 0; i--){
            System.out.print(array[i] + " ");
        }
    }

    private static void chocolate_start(){
        //getinput
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        buy_s = new int[n];
        sell_b = new int[n];
        for (int i = 0; i < n; i++){
            buy_s[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++){
            sell_b[i] = scanner.nextInt();
        }

        Transaction recursiveResult = chocolate_1(0,n/2, n-1);
        System.out.println("recurs(a, b, profit)=(" + recursiveResult.lowIndex+", "+recursiveResult.highIndex+", "+recursiveResult.profit+")");
        Transaction linearResult = chocolate_slow();
        //System.out.println(linearResult.profit);
        System.out.println("linear(a, b, profit)=(" + linearResult.lowIndex+", "+linearResult.highIndex+", "+linearResult.profit+")");
    }
    static int[] buy_s;
    static int[] sell_b;
    private static Transaction chocolate_1(int lowIndex, int midIndex, int highIndex){
       if(lowIndex == highIndex){
           //when n==1
           return new Transaction(lowIndex, highIndex);
       }

       Transaction left = chocolate_1(lowIndex, (lowIndex+midIndex)/2, midIndex);
       Transaction right = chocolate_1(midIndex+1, (midIndex+highIndex)/2, highIndex);
       int tempLow, tempHigh;
       if(buy_s[left.lowIndex] < buy_s[right.lowIndex] - (right.lowIndex - left.lowIndex)*100){
           tempLow = left.lowIndex;
       }
       else{
           tempLow = right.lowIndex;
       }
       if(sell_b[left.highIndex] > buy_s[right.highIndex] - (right.highIndex - left.highIndex)*100){
           tempHigh = left.highIndex;
       }
       else{
           tempHigh = right.highIndex;
       }
       return new Transaction(tempLow, tempHigh);
       /*
       Transaction crossCheck = new Transaction(left.lowIndex, right.highIndex);

       if(left.profit > right.profit && left.profit > crossCheck.profit){
           return left;
       }
       if(right.profit > left.profit && right.profit > crossCheck.profit){
           return right;
       }
       return crossCheck;*/
    }
    private static Transaction chocolate_slow(){
        Transaction best = new Transaction(0,0);
        Transaction temp;
        int n = buy_s.length;
        for(int i = 0; i < n; i++){
            for (int j = i; j < n; j++) {
                temp = new Transaction(i, j);
                if(temp.profit > best.profit){
                    best = temp;
                }
            }
        }
        return best;
    }
    static class Transaction {
        int lowIndex, highIndex, profit;
        public Transaction(int low, int high){
            lowIndex = low;
            highIndex = high;
            profit = sell_b[high] - buy_s[low] - ((high-low) * 100);
            if(profit<0){
                profit = 0;
            }
        }
    }
}
