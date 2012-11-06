/*
 * InterviewStreet - Flowers
 * Passed 10/10
 * 
 * You and your K-1 friends want to buy N flowers. Flower number i has host ci. Unfortunately the seller does not like
 * a customer to buy a lot of flowers, so he tries to change the price of flowers for customer who had bought flowers
 * before. More precisely if a customer has already bought x flowers, he should pay (x+1)*ci dollars to buy flower number i.
 * 
 * You and your K-1 firends want to buy all N flowers in such a way that you spend the as few money as possible.
 * 
 * Input:
 * 
 * The first line of input contains two integers N and K.
 * next line contains N positive integers c1,c2,...,cN respectively.
 * 
 * Output:
 * 
 * Print the minimum amount of money you (and your friends) have to pay in order to buy all n flowers.
 * 
 * Sample onput :
 * 
 * 3 3
 * 2 5 6
 * 
 * Sample output :
 * 
 * 13
 * 
 * Explanation :
 * In the example each of you and your friends should buy one flower. in this case you have to pay 13 dollars.
 * 
 * Constraint :
 * 
 * 1 <= N, K  <= 100
 * Each ci is not more than 1000,000
 * 
 */

package com.interviewstreet.www.flowers;
import java.util.*;

class Solution
{
	public static void main( String args[] )
	{
		Scanner in = new Scanner(System.in);
		
		int N, K;
		N = in.nextInt();
		K = in.nextInt();
		
		int C[] = new int[N];
		for(int i=0; i<N; i++){
			C[i] = in.nextInt();
		}
		
		Arrays.sort(C);
		long result = 0;
		int mult = 1;
		for(int i=1; i<=N; i++){
			result += C[N-i] * mult;
			if ((i%K)==0) {
				mult++;
			}
		}

		System.out.println( result );
		in.close();
	}
}