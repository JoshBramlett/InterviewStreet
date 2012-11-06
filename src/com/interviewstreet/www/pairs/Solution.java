/*
 * InterviewStreet - Pairs/KDifference
 * Passed 15/15
 * 
 * Given N numbers , [N<=10^5] we need to count the total pairs of numbers that have a difference of K. [K>0 and K<1e9]
 * 
 * Input Format:
 * 1st line contains N & K (integers).
 * 2nd line contains N numbers of the set. All the N numbers are assured to be distinct.
 * 
 * Output Format:
 * One integer saying the no of pairs of numbers that have a diff K.
 * 
 * Sample Input #00:
 * 5 2
 * 1 5 3 4 2
 * 
 * Sample Output #00:
 * 3
 * 
 * 
 * Sample Input #01:
 * 10 1
 * 363374326 364147530 61825163 1073065718 1281246024 1399469912 428047635 491595254 879792181 1069262793 
 * 
 * Sample Output #01:
 * 0
 * 
 */

package com.interviewstreet.www.pairs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// IS_KDifference
public class Solution 
{
	public static void main(String[] args) throws IOException      
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] n_and_k = br.readLine().split(" ");
		int numNumbers = Integer.parseInt(n_and_k[0]);
		int difference = Integer.parseInt(n_and_k[1]);
		
		String[] numbers = br.readLine().split(" ");
		HashMap<Integer, Void> numberHash = new HashMap<Integer, Void>(numNumbers);
		for (int i = 0; i < numNumbers; i++)
			numberHash.put(Integer.parseInt(numbers[i]), null);

		int counter = 0;
		for (int i = 0; i < numNumbers; i++)
		{
			int number = Integer.parseInt(numbers[i]);
			
			int testUpper = number + difference;
			if (numberHash.containsKey(testUpper))
				counter++;
		}
		
		System.out.println(counter);
	}
}
