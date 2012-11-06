/*
 * InterviewStreet - MeetingPoint
 * Passed 13/13
 * 
 * There is an infinite integer grid at which N people have their houses on. They decide to unite at a common meeting place,  
 * which is someone's house.
 * From any given cell, all 8 adjacent cells are reachable in 1 unit of time. 
 * eg: (x,y) can be reached from (x-1,y+1) in a single unit of time.
 * Find a common meeting place which minimises the sum of the travel times of all the persons.
 * 
 * Input Format:
 * N
 * The following N lines will contain two integers saying the x & y coordinate of the i-th person.
 * 
 * Output Format:
 * M M = min sum of all travel times; 
 * 
 * 
 * Constraints:
 * N <= 10^5
 * The absolute value of each co-ordinate in the input will be atmost 10^9
 * 
 * HINT: Please use long long 64-bit integers;
 * 
 * Input #00:
 * 4
 * 0 1
 * 2 5
 * 3 1
 * 4 0
 * 
 * Output #00:
 * 8
 * 
 * Explanation: Sums of travel times of the houses are 11, 13, 8 and 10. 8 is the minimum.
 * 
 * Input #01:
 * 6
 * 12 -14
 * -3 3
 * -14 7
 * -14 -3
 * 2 -12
 * -1 -6
 * 
 * Output #01:
 * 54
 * 
 */

package com.interviewstreet.www.meetingpoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Solution 
{
	public static void main(String[] args) throws IOException      
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numPoints = Integer.parseInt(br.readLine());

		long[][] points = new long[numPoints][3];
		BigInteger cumX = new BigInteger("0");
		BigInteger cumY = new BigInteger("0");
		for (int i = 0; i < numPoints; i++)
		{
			String[] line = br.readLine().split(" ");
			points[i][0] = Long.parseLong(line[0]);
			points[i][1] = Long.parseLong(line[1]);
			
			cumX = cumX.add(new BigInteger(line[0]));
			cumY = cumY.add(new BigInteger(line[1]));
		}
		
		if (numPoints <= 1)
		{
			System.out.println("0");
			return;
		}
		else if (numPoints == 2)
		{
			System.out.println(getDistance(points[0][0], points[0][1], points[1][0], points[1][1]));
			return;
		}
		
		BigInteger[] tempAvgX = cumX.divideAndRemainder(new BigInteger(Integer.toString(numPoints)));
		BigInteger[] tempAvgY = cumY.divideAndRemainder(new BigInteger(Integer.toString(numPoints)));
		
		double avgX = (tempAvgX[0].doubleValue() + (tempAvgX[1].doubleValue() / (numPoints * 1.0d)));
		double avgY = (tempAvgY[0].doubleValue() + (tempAvgY[1].doubleValue() / (numPoints * 1.0d)));
		
		long wholeX = (long)avgX, wholeY = (long)avgY;
		long remX = ((avgX % ((wholeX == 0) ? 1 : wholeX * 1.0f)) >= .5f) ? 1L : 0L;
		long remY = ((avgY % ((wholeY == 0) ? 1 : wholeY * 1.0f)) >= .5f) ? 1L : 0L;
		
		long avgWholeX = wholeX + remX;
		long avgWholeY = wholeY + remY;
		
		boolean pointOnOrigin = false;
		long[] quadCount = new long[4];
		for (int i = 0; i < numPoints; i++)
		{
			if ((avgWholeX == points[i][0]) && (avgWholeY == points[i][1]))
			{
				pointOnOrigin = true;
				points[i][2] = -1L;
				continue;
			}
			
			double x = points[i][0] * 1.0d;
			double y = points[i][1] * 1.0d;
			
			if (x <= avgX)
			{
				if (y <= avgY)
				{
					points[i][2] = 2;
					quadCount[2]++;
				}
				else
				{
					points[i][2] = 3;
					quadCount[3]++;
				}
			}
			else
			{
				if (y <= avgY)
				{
					points[i][2] = 1;
					quadCount[1]++;
				}
				else
				{
					points[i][2] = 0;
					quadCount[0]++;
				}
			}
		}

		long closestDistance[] = new long[] {Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE};
		int closestIndex[] = new int[] {-1, -1, -1, -1};
		for (int i = 0; i < numPoints; i++)
		{
			if (points[i][2] < 0)
				continue;
			
			long distance = getDistance(points[i][0], points[i][1], avgWholeX, avgWholeY);
			if (distance <= closestDistance[(int) points[i][2]])
			{
				closestDistance[(int) points[i][2]] = distance;
				closestIndex[(int) points[i][2]] = i;
			}
		}

		long sum[] = new long[(pointOnOrigin) ? 5 : 4];
		for (int i = 0; i < 4; i++)
		{
			if (closestIndex[i] < 0)
			{
				sum[i] = Long.MAX_VALUE;
				continue;
			}
			
			sum[i] = 0;
			for (int j = 0; j < numPoints; j++)
				sum[i] += getDistance(points[j][0], points[j][1], points[closestIndex[i]][0], points[closestIndex[i]][1]);
		}
		
		if (pointOnOrigin)
			for (int i = 0; i < numPoints; i++)
				sum[4] += getDistance(points[i][0], points[i][1], avgWholeX, avgWholeY);
		
		long finalSum = Long.MAX_VALUE;
		for (int i = 0; i < sum.length; i++)
			if (finalSum > sum[i])
				finalSum = sum[i];
		
		System.out.println(finalSum);
	}
	
	public static long getDistance(long fromX, long fromY, long toX, long toY)
	{
		long diffX = Math.abs(fromX - toX);
		long diffY = Math.abs(fromY - toY);

		if (diffX > diffY) 
			return diffX;
		
		return diffY;
	}
}