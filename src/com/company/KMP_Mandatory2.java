package com.company;

import java.util.Scanner;

/*
* Input
Input should be read from standard input (the console) and will come in the following format:

Line 1: "N G". N is the length of the track. G is the number of ground phases during her tricks.
Line 2: N integers separated by space corresponding to the slopes of the track.
Line 1+2i: The slopes of the i'th ground phase separated by space (1 <= i <= G1<=i<=G).
Line 2+2i: "Min Max" the Min and Max length of the i'th air phase. In the mandatory part Min will always be 1 and Max always N. (1 <= i < G1<=i<G).
The absolute value of all integers in the input will be at most 10.000.000 (often much smaller).

Output
Your program must write the following output to standard out (the console).

Line 1: "S L" or "Impossible". S is the location where she should start her tricks (1-indexed), and L is the length of her tricks on the track.
*
* */
public class KMP_Mandatory2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int trackLength = scanner.nextInt();
        int numGroundPhases = scanner.nextInt();
        scanner.nextLine();
        int[] track = new int[trackLength];
        for (int i = 0; i < trackLength; i++) {
            track[i] = scanner.nextInt();
        }
        scanner.nextLine();
        int[][] groundTricks = new int[numGroundPhases][];


        for (int i = 0; i < numGroundPhases; i++) {
            String[] tempGroundTrickString = scanner.nextLine().split(" ");
            int[] tempGroundTrick;
            if (i < numGroundPhases-1) {
                String tempAirTime = scanner.nextLine();
                tempGroundTrick = new int[tempGroundTrickString.length];
                for (int j = 0; j < tempGroundTrick.length; j++) {
                    tempGroundTrick[j] = Integer.parseInt(tempGroundTrickString[j]);
                }
            }
            else {
                tempGroundTrick = new int[tempGroundTrickString.length];
                int k = 0;
                for (int j = tempGroundTrick.length - 1; j >= 0; j--) {
                    tempGroundTrick[j] = Integer.parseInt(tempGroundTrickString[k++]);
                }
            }
            groundTricks[i] = tempGroundTrick;
        }
        /*System.out.println("track:");
        for (int i = 0; i < trackLength; i++) {
            System.out.print(track[i] + " ");
        }
        System.out.println("");*/
        //printArray(groundTricks, "groundtricks:");

        //generate faillinks
        int[][] failLinks = generateFailLinks(groundTricks);
        //print faillinks
        //printArray(failLinks, "failLinks:");
        int[] matchStart_matchLength = kmpAlgorithm1(groundTricks, failLinks, track);
        if(matchStart_matchLength[0] == -1){
            System.out.println("Impossible");
        }
        else{
            int stop = matchStart_matchLength[0] + matchStart_matchLength[1];
            int matchStop = kmpAlgorithm2(groundTricks, failLinks, track, stop);
            if(matchStop == -1){
                System.out.println("Impossible");
            }
            else{
                System.out.println(matchStart_matchLength[0] + " " + (matchStop - matchStart_matchLength[0]));
            }
        }

    }

    private static int kmpAlgorithm2(int[][] groundTricks, int[][] failLinks, int[] track, int stop) {
        //KMP algorithm:
        int textIndex = track.length-1;
        int patternIndex = 0; //final pattern is flipped at input so iterate pattern positively
        int patternToMatch = groundTricks.length-1; //last pattern
        int matchStart = -1;
        //iterate through text
        while(textIndex > stop){
            //if lettermatch increment both text and pattern index
            if(track[textIndex] == groundTricks[patternToMatch][patternIndex]){
                textIndex--;
                patternIndex++;
                //if reached end of final pattern then break
                if(patternIndex == groundTricks[patternToMatch].length){
                    matchStart = groundTricks[patternToMatch].length + textIndex + 2;
                    break;
                }
            }
            else{//mismatch
                //if looking for first patternletter move to next textletter
                if(patternIndex == 0){
                    textIndex--;
                }
                else{//not looking for first letter follow links
                    patternIndex = failLinks[patternToMatch][patternIndex - 1];
                }
            }
        }

        return matchStart;
    }

    private static int[] kmpAlgorithm1(int[][] groundTricks, int[][] failLinks, int[] track) {
        //KMP algorithm:
        int textIndex = 0;
        int patternIndex = 0;
        int patternToMatch = 0;
        int matchStart = 0;
        int matchLength = 0;
        //iterate through text
        while(textIndex < track.length){
            //if lettermatch increment botch text and pattern index
            if(track[textIndex] == groundTricks[patternToMatch][patternIndex]){
                textIndex++;
                patternIndex++;

                //if reached end of pattern a ground trick is done and need airtime atleast 1 and start matching next pattern
                if(patternIndex == groundTricks[patternToMatch].length){
                    //if first pattern found then set matchStart to start of matched pattern
                    if(patternToMatch == 0){
                        matchStart = (textIndex - patternIndex) + 1 ;
                    }
                    //move to next pattern
                    patternToMatch++;
                    //set index to look for start letter (of next pattern)
                    patternIndex = 0;
                    //if all but last pattern found then break
                    if(patternToMatch == groundTricks.length - 1){
                        matchLength = (textIndex - matchStart) + 1;
                        break;
                        //patternToMatch = groundTricks.length - 1;
                        //patternIndex = groundTricks[patternToMatch].length - 1;
                    }
                    else{
                        //skip one textIndex as airtime
                        textIndex++;
                    }

                }
            }
            else{//mismatch
                //if looking for first patternletter move to next textletter
                if(patternIndex == 0){
                    textIndex++;
                }
                else{//not looking for first letter follow links
                    patternIndex = failLinks[patternToMatch][patternIndex - 1];
                }
            }
        }
        if(matchLength == 0){
            //System.out.println("Impossible");
            return new int[]{-1,-1};
        }
        else{
            //System.out.println(matchStart + " " + matchLength);
            return new int[]{matchStart, matchLength};
        }
    }

    private static void printArray(int[][] inputArray, String message) {
        System.out.println(message);
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                System.out.print(inputArray[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static int[][] generateFailLinks(int[][] groundTricks){
        //generate faillinks
        int[][] failLinks = new int[groundTricks.length][];
        //for each pattern
        for (int x = 0; x < groundTricks.length; x++) {
            //create j (left) and i (right) and link array for the pattern. link[0] always = 0;
            int j = 0, i = 1;
            failLinks[x] = new int[groundTricks[x].length];
            failLinks[x][0] = 0;
            //go through the linkarray
            while(i < failLinks[x].length){
                //if letter at i == letter at j, then link[i] = j+1 and increment i and j after
                if(groundTricks[x][j] == groundTricks[x][i]){
                    failLinks[x][i] = j+1;
                    j++;
                    i++;
                }
                else{
                    // letters not match, then j follows failure link back and try matching again
                    //          follow back by setting j = faillink[x][j-1]
                    //if j reaches zero and its a missmatch then set faillink[i] = 0 and increment i
                    if(j == 0){
                        failLinks[x][i] = 0;
                        i++;
                    }
                    else{
                        j = failLinks[x][j-1];
                    }
                }
            }
        }
        return failLinks;
    }
}
/*
test-input:
12 1
-3 -1 2 -5 3 0 2 -3 2 3 -2 -3
-1 -2 -3 -1 -2 -5
1 12
expected links: 0 0 0 1 2 0

12 1
-3 -1 2 -5 3 0 2 -3 2 3 -2 -3
-1 -1 -2 -1 -1 -2 -1 -1 -1
1 12
expected links: 0 1 0 1 2 3 4 5 2
 */