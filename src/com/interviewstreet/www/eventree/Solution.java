/*
 * InterviewStreet - Even Tree
 * Passed 10/10
 * 
 * You are given a tree (a simple connected graph with no cycles).You have to remove as many edges from the tree as 
 * possible to obtain a forest with the condition that : Each connected component of the forest contains even number 
 * of vertices
 * 
 * Your task is to calculate the number of removed edges in such a forest.
 * 
 * Input:
 * The first line of input contains two integers N and M. N is the number of vertices and M is the number of edges. 2 <= N <= 100. 
 * Next M lines contains two integers ui and vi which specifies an edge of the tree. (1-based index)
 * 
 * Output:
 * Print a single integer which is the answer
 * 
 * Sample Input:
 * 10 9
 * 2 1
 * 3 1
 * 4 3
 * 5 2
 * 6 1
 * 7 2
 * 8 6
 * 9 8
 * 10 8
 * 
 * Sample Output :
 * 2
 * 
 * Explanation : On removing the edges (1, 3) and (1, 6), we can get the desired result.
 * Original tree: http://linode.interviewstreet.com/eventree1.png
 * 
 * Decomposed tree: http://linode.interviewstreet.com/eventree2.png
 * 
 * Note: The tree in the input will be such that it can always be decomposed into components containing even number of nodes. 
 * 
 */

package com.interviewstreet.www.eventree;
import java.util.*;

class Solution
{
	public static void main( String args[] )
	{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		
		boolean[][] adj = new boolean[N][N];
		for (int i = 0; i < M; i++)
		{
			int u = in.nextInt() - 1;
			int v = in.nextInt() - 1;
			
			//adj[u][v] = true;
			adj[v][u] = true;
		}

		int removedEdges = 0;		
		while (true)
		{
			boolean hit = false;
			for (int i = 0; i < N; i++)
			{
				int children = getNumChildren(adj, i, true);
				if (children % 2 != 0)
				{
					int parent = getParent(adj, i);
					if (parent < 0) continue;
					
					adj[parent][i] = false;
					//adj[i][parent] = false;
					
					removedEdges++;
					hit = true;

					//System.out.println("Parent: " + (parent + 1) + " Child: " + (i + 1));
					break;
				}
			}
			
			if (!hit) break;
		}
		
		System.out.println(removedEdges);
		in.close();
	}
	
	public static int getParent(boolean[][] adj, int index)
	{
		for (int i = index; i >= 0; i--)
			if (adj[i][index])
				return i;
		
		return -1;
	}
	
	public static int getNumChildren(boolean[][] adj, int index, boolean recursive)
	{
		int count = 0;
		for (int i = 0; i < adj[0].length; i++)
			if (adj[index][i])
			{
				if (recursive)
					count += getNumChildren(adj, i, true);
				
				count++;
			}

		return count;
	}
}