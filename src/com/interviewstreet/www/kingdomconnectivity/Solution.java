/*
 * InterviewStreet - Kingdom Connectivity
 * Passed 10/10
 * 
 * It has been a prosperous year for King Charles and he is rapidly expanding his kingdom.A beautiful new kingdom 
 * has been recently constructed and in this kingdom there are many cities connected by a number of one-way roads.
 * Two cities may be directly connected by more than one roads, this is to ensure high connectivity.
 * 
 * In this new kingdom King Charles has made one of the cities at his financial capital and one as warfare capital 
 * and he wants high connectivity between these two capitals.The connectivity of a pair of cities say city A and 
 * city B is defined as the number of different paths from city A to city B. A path may use a road more than once 
 * if possible. Two paths are considered different if they do not use exactly the same sequence of roads.
 * 
 * There are N cities numbered 1 to N in the new kingdom and M one-way roads  . City 1 is the monetary capital and 
 * city N is the warfare capital.
 * 
 * You being one of the best programmers in new kingdom need to answer the connectivity of financial capital and 
 * warfare capital ,i.e number of different paths from city 1 to city N.
 * 
 * Input Description:
 * 
 * First line contains two integers N and M.
 * 
 * Then follow M lines ,each having two integers say x and y, 1<=x,y<=N , indicating there is a road from city x to city y.
 * 
 * Output Description:
 * 
 * Print the number of different paths from city 1 to city N modulo 1,000,000,000(10^9).If there are infinitely many 
 * different paths print "INFINITE PATHS"(quotes are for clarity).
 * 
 * Sample Input:
 * 5 5
 * 1 2
 * 2 4
 * 2 3
 * 3 4
 * 4 5
 * 
 * Sample Output :
 * 2
 * 
 * Sample Input:
 * 5 5
 * 1 2
 * 4 2
 * 2 3
 * 3 4
 * 4 5
 * 
 * Sample Output :
 * INFINITE PATHS
 * 
 * Constraints:
 * 
 * 2<=N<=10,000(10^4)
 * 
 * 1<=M<=1,00,000(10^5) 
 * 
 * Two cities may be connected by more than two roads and in that case those roads are to be considered different for 
 * counting distinct paths
 * 
 */

package com.interviewstreet.www.kingdomconnectivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Solution 
{
	public static HashMap<Integer, ArrayList<Integer>> children;
	public static HashMap<Integer, Long> solution;
	public static boolean[] hit; 
	public static boolean[] hasLoop;
	
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();	

		children = new HashMap<Integer, ArrayList<Integer>>();
		solution = new HashMap<Integer, Long>();
		hit = new boolean[N];
		hasLoop = new boolean[N];
		hit[0] = true;
		
		for (int i = 0; i < M; i++)
		{
			int u = in.nextInt()-1;
			int v = in.nextInt()-1;
			
			if (!children.containsKey(u))
			{
				ArrayList<Integer> edges = new ArrayList<Integer>();
				edges.add(v);
				
				children.put(u, edges);
			}
			else
			{
				children.get(u).add(v);
			}
		}

		getConnectivityNumber(0, N-1);
		System.out.println(solution.get(0));
		in.close();
	}
	
	public static long getConnectivityNumber(int source, int dest)
	{
		if (source == dest)
			return 1;
		
		if (!children.containsKey(source))
			return 0;
		
		long value = 0L;
		for (Integer edge : children.get(source))
		{
			if (hit[edge])
			{
				hasLoop[edge] = true;
				continue;
			}
			
			hit[edge] = true;
			if (solution.containsKey(edge))
			{
				value = solution.get(edge);
			}
			else
			{
				//System.out.println((source+1)+"->"+(edge+1));
				value = getConnectivityNumber(edge, dest);
			}
			
			if (!solution.containsKey(source))
			{
				solution.put(source, 0L);
			}
			
			if (value > 0)
			{
				long tmp = (solution.get(source) + value) % 1000000000L;
				solution.put(source, tmp);
			}
			
			if (solution.get(source) > 0L && hasLoop[source] == true)
			{
				System.out.println("INFINITE PATHS");
				System.exit(0);
			}
			
			hit[edge] = false;
		}
		
		return solution.get(source);
	}
}
