package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FoodTrucksM1 {
    //public static int counter;
    public static long[][] input;
    public static int numPos, numDays;
    public static HashMap<Integer, Long>[] dynamicData;
    public static void main(String[] args) {
        //counter=0;
        Scanner scanner = new Scanner(System.in);
        numPos = scanner.nextInt(); // = n
        numDays = scanner.nextInt(); // = m
        input = new long[numDays][numPos];
        dynamicData = new HashMap[numDays];
        for (int i = 0; i < numDays; i++) {
            dynamicData[i] = new HashMap<Integer, Long>();
        }
        // special cases
        if(numPos <= 3){
            //only 3 positions so sum all
            long sum = 0;
            for (int i = 0; i < numDays; i++) {
                for (int j = 0; j < numPos; j++) {
                    sum += scanner.nextLong();
                }
            }
            System.out.println(sum);
        }
        else if(numDays == 1){
            long top1 = 0, top2 = 0, top3 = 0;
            //only 1 day so grab top 3 numbers and sum
            for (int i = 0; i < numPos; i++) {
                long temp = scanner.nextLong();
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
        //normal
        else{
            //take input
            for (int i = 0; i < numDays; i++) {
                for (int j = 0; j < numPos; j++) {
                    input[i][j] = scanner.nextLong();
                }
            }
            //create start combinations
            ArrayList<int[]> combinations = createStartPos(numPos);
            long bestsum = 0;
            long tempsum = 0;
            int[] tempComb;
            //for each combination start recursion. Retrieve best sum.
            for (int i = 0; i < combinations.size(); i++) {
                tempComb = combinations.get(i);
                tempsum = optimal(tempComb[0], tempComb[1], tempComb[2], numDays-1);
                if(tempsum > bestsum){
                    bestsum = tempsum;
                }
            }
            System.out.println(bestsum);
            //System.out.println("counter" + counter);

            /*for (int i = 0; i < numDays; i++) {
                for (int t : dynamicData[i].keySet()){
                    System.out.println("day:" + i + " key:" + t + " val:" + dynamicData[i].get(t));
                }
            }*/
        }
    }

    public static long optimal(int a, int b, int c, int d){

        /*
        * a b c, 2, 5, 7
        * a*100 = 200
        * b*10 = [0-9] 0
        * c = 7 [0-9]
        * 257
        */

        //if already calculated subproblem return found value
        Long tempData = dynamicData[d].get(a*100+b*10+c);
        if(tempData != null){
            return tempData;
        }

        //if first day (leafnode) return its value
        if(d == 0){
            //counter++;
            long callSum = input[d][a] + input[d][b] + input[d][c];
            dynamicData[d].put(a*100+b*10+c, callSum);
            return callSum;
        }

        //for each legally available state call recursively with d-1 and return best subsum + callsum
        long bestSubSum = 0;
        long temp;
        for (int i = 0; i < numPos; i++) {
            //go through indexes
            if(i != a && i != b && i != c){
                //if the index does not have a truck creates 3 moves (one for each truck that can move there)
                //also 1 move is where nothing is moved
                //move truck1
                temp = optimal(i, b, c, d-1);
                if(temp > bestSubSum){
                    bestSubSum = temp;
                }
                //move truck2
                temp = optimal(a, i, c, d-1);
                if(temp > bestSubSum){
                    bestSubSum = temp;
                }
                //move truck3
                temp = optimal(a, b, i, d-1);
                if(temp > bestSubSum){
                    bestSubSum = temp;
                }
                //no trucks moved
                temp = optimal(a, b, c, d-1);
                if(temp > bestSubSum){
                    bestSubSum = temp;
                }
            }
        }
        //save optimal data and return
        long callSum = input[d][a] + input[d][b] + input[d][c];
        dynamicData[d].put(a*100+b*10+c, callSum + bestSubSum);
        return callSum + bestSubSum;
    }

    //working
    public static ArrayList<int[]> createStartPos(int numPos){
        ArrayList<int[]> combinations = new ArrayList<>();
        int length = numPos;
        int[] temp = new int[3];
        for (int a = 0; a < numPos; a++) {
            temp[0] = a;
            for (int b = a+1; b < numPos; b++) {
                temp[1] = b;
                for (int c = b+1; c < numPos ; c++) {
                    temp[2] = c;
                    combinations.add(temp.clone());
                }
            }
        }

        //print combinations
        /*for (int i = 0; i < combinations.size(); i++) {
            temp = combinations.get(i);
            System.out.println(i + ": [" + temp[0] + ", " + temp[1] + ", " + temp[2] + "]");

        }*/


        return combinations;
    }


}
