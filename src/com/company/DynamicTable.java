package com.company;

import java.util.Scanner;

public class DynamicTable {

    public static void main(String[] args) {
        int[] array = new int[1];
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String input = scanner.nextLine();
            if(input.charAt(0) == 'I'){
                insert(Integer.parseInt(input.split(" ")[1]));
            }
            if(input.charAt(0) == 'D'){
                delete();
            }
            if(input.charAt(0) == 'P'){
                print();
            }
            if(input.charAt(0) == 'S'){
                size();
            }
        }
    }
    public static void insert(int x){

    }

    public static void delete(){

    }

    public static void print(){

    }
    public static void size(){

    }
}
