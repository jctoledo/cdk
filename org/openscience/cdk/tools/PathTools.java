/* PathTools.java
 *
 * $RCSfile$    $Author$    $Date$    $Revision$
 * 
 * Copyright (C) 1997-2000  The JChemPaint project
 * 
 * Contact: steinbeck@ice.mpg.de
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All I ask is that proper credit is given for my work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. 
 * 
 */

package org.openscience.cdk.tools;

import java.util.*;
import java.io.*;

public class PathTools
{
	 public static boolean  debug = false; // minimum details

	/**
	 * All-Pairs-Shortest-Path computation based on Floyds algorithm
	 * Takes an nxn matrix C of edge costs and produces
	* an nxn matrix A of lengths of shortest paths.
	*
	 * @param   A  nxn matrix A of lengths of shortest paths
	 */
	public static int[][] computeFloydAPSP(int C[][])
	{
		int i,j,k;
		int n = C.length;
		int[][] A = new int[n][n]; 
		//System.out.println("Matrix size: " + n);
		for (i=0; i<n; i++)
		{
			for (j=0; j<n; j++)
			{
				if (C[i][j] == 0)
				{
				 A[i][j] = 999999999;
				}
				else
				{
				 A[i][j] = 1;
				}
			}
		}
		for (i=0; i<n; i++)
		{
			A[i][i] = 0;              // no self cycle
		}
		for (k=0; k<n; k++)
		{
			for (i=0; i<n; i++)
			{
				for (j=0; j<n; j++)
				{
					if (A[i][k]+A[k][j] < A[i][j])
					{
						A[i][j] = A[i][k] + A[k][j];
						//P[i][j] = k;        // k is included in the shortest path
					}
				}
			}
		}
		return A;
	}

	public static int[] getInt2DColumnSum(int[][]apsp)
	{
		int[] colSum = new int[apsp.length];
		int sum = 0;
		for (int i = 0; i < apsp.length; i++)
		{
			sum = 0;
			for (int j = 0; j < apsp.length; j++)
			{
				sum += apsp[i][j];
			}
			colSum[i] = sum;
		}
		return colSum;
	}
	 
}








