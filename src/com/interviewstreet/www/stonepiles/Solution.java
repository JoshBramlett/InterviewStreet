/*
 * InterviewStreet - Stone Piles
 * Passed 11/11
 * 
 * There are N piles of stones where the ith pile has xi stones in it. Alice and Bob play the following game:
 * 
 * 	a. Alice starts, and they alternate turns.
 * 
 * 	b. In a turn, a player can choose any one of the piles of stones and divide the stones in it into any number 
 * 	of unequal piles such that no two of the piles you create should have the same number of stones. For example, 
 * 	if there 8 stones in a pile, it can be divided into one of these set of piles: (1,2,5), (1,3,4), (1,7), (2,6) 
 * 	or (3,5). 
 * 
 * 	c. The player who cannot make a move (because all the remaining piles are indivisible) loses the game.
 * 
 * Given the starting set of piles, who wins the game assuming both players play optimally?
 * 
 * Input:
 * The first line contains the number of test cases T. T test cases follow. The first line for each test case  
 * contains N, the number of piles initially. The next line contains N space delimited numbers, the number of stones 
 * in each of the piles.
 * 
 * Output:
 * Output T lines, one corresponding to each test case containing "ALICE" if Alice wins the game and "BOB" otherwise.
 * 
 * Constraints:
 * 1 <= T <= 50
 * 1 <= N <= 50
 * 1 <= xi <= 50
 * 
 * Sample Input:
 * 4
 * 1
 * 4
 * 2
 * 1 2
 * 3
 * 1 3 4
 * 1
 * 8
 * 
 * Sample Output:
 * BOB
 * BOB
 * ALICE
 * BOB
 * 
 * Explanation:
 * For the first case, the only possible move for Alice is (4) -> (1,3). Now Bob breaks up the pile with 3 stones into 
 * (1,2). At this point Alice cannot make any move and has lost.
 * 
 */

package com.interviewstreet.www.stonepiles;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// IS_StonePiles
public class Solution 
{
	static String[] PLAYERS = new String[] { "ALICE", "BOB" };
	static int[] NIMBERS = new int[] { 0, 0, 0, 1, 0, 2, 3, 4, 0, 5, 
									   6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 
									   16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 
									   26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 
									   36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46 };

	public static void main(String[] args) throws IOException  
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numTestCases = Integer.parseInt(br.readLine());

		for (int testCase = 0; testCase < numTestCases; testCase++)
		{
			int numPiles = Integer.parseInt(br.readLine());
			String line = br.readLine();
			String[] sizeInputs = line.split(" ");
			
			int[] sizes = new int[numPiles];
			for (int i = 0; i < numPiles; i++)
				sizes[i] = NIMBERS[Integer.parseInt(sizeInputs[i])];
			
			int nim_sum = 0;
			for (int i = 0; i < numPiles; i++)
				nim_sum ^= sizes[i];
			
			if (nim_sum == 0)
				System.out.println("BOB");
			else
				System.out.println("ALICE");
		}
	}
}
