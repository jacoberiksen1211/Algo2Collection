package com.company;

import java.util.Scanner;

public class SequenceAllignment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();
        int gapCost = 1;
        int misMatchCost = 1;
        int m = input1.length()+1;
        int n = input2.length()+1;
        char[][] directionArray = new char[m][n];
        int[][] costArray = new int[m][n];
        costArray[0][0] = 0;
        for (int i = 0; i < m; i++) {
            costArray[i][0] = i*gapCost;
            directionArray[i][0] = 'a';
        }
        for(int j = 0; j < n; j++){
            costArray[0][j] = j*gapCost;
            directionArray[0][j] = 'b';
        }
        directionArray[0][0] = 's';
        StringBuilder output1 = new StringBuilder(m+n);
        output1.append(input1);
        StringBuilder output2 = new StringBuilder(m+n);
        output2.append(input2);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int value;

                if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                    directionArray[i][j] = 'h';
                    value = costArray[i - 1][j - 1];
                } else {
                    directionArray[i][j] = 'm';
                    value = misMatchCost + costArray[i - 1][j - 1];
                }

                if (gapCost + costArray[i - 1][j] < value) {
                    directionArray[i][j] = 'a';
                    value = gapCost + costArray[i - 1][j];
                }
                if (gapCost + costArray[i][j - 1] < value) {
                    directionArray[i][j] = 'b';
                    value = gapCost + costArray[i][j - 1];
                }
                costArray[i][j] = value;
            }
        }

        int i = m-1;
        int j = n-1;
        while(i >= 0 && j >= 0){
            char temp = directionArray[i][j];
            if(temp == 'h' || temp == 'm'){
                i--;
                j--;
            }
            else if(temp == 'b'){
                output1.insert(i, '-');
                j--;
            }
            else if(temp == 'a'){
                output2.insert(j, '-');
                i--;
            }
            else if(temp == 's'){
                break;
            }
        }

        System.out.println(output1.toString());
        System.out.println(output2.toString());
/*
        System.out.println("cost:" + costArray[m - 1][n - 1]);
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(directionArray[i][j] + "\t");
            }
            System.out.println("");
        }
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                System.out.print(costArray[i][j] + "\t");
            }
            System.out.println("");
        }*/
    }

}
