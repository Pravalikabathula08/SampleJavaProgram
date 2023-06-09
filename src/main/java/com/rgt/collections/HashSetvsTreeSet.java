package com.rgt.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashSetvsTreeSet {
	public static long getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static void main(String[] args) 
	{
		/**
		 * HashSet Insertion ,Deletion Time and Space Complexity
		 */
		Set<Integer> hashset = new HashSet<Integer>();
		long startTime, endTime;
		long memoryBefore, memoryAfter;

		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();

		for (int i = 0; i < 100000; i++) {
			hashset.add(i);
		}
		memoryAfter = getMemoryUsage();
		long SpaceComplexityInsertion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long TimeComplexityInsertion = endTime - startTime;
	
		hashset.remove(1000);
		memoryAfter = getMemoryUsage();
		long SpaceComplexityDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long TimeComplexityDeletion = endTime - startTime;

		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("********HashSet***********");
		System.out.println("HashSet Time Complexity for Insertion	:" +TimeComplexityInsertion + " ns ");
		System.out.println("HashSet Space Complexity for Insertion	:" +SpaceComplexityInsertion + " bytes ");
		System.out.println("HashSet Time Complexity for Deletion	:" +TimeComplexityDeletion + " ns");
		System.out.println("HashSet Space Complexity for Deletion	:" +SpaceComplexityDeletion + " bytes");
		System.out.println("--------------------------------------------------------------------------------------------");
				
		/**
		 * For TreeSet Insertion ,Deletion Time and Space Complexity
		 */

		Set<Integer> treeSet = new TreeSet<Integer>();
		
		for (int i = 0; i < 100000; i++) {
			treeSet.add(i);
		}
		long SpaceComplexityTreeSetInsertion = memoryAfter - memoryBefore;
		long TimeComplexityTreeSetInsertion = endTime - startTime;
		
		treeSet.remove(1000);
		memoryAfter = getMemoryUsage();
		long SpaceComplexityTreeSetDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long TimeComplexityTreeSetDeletion = endTime - startTime;
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("********TreeSet***********");
		System.out.println("TreeSet Time Complexity for Insertion	:" +TimeComplexityTreeSetInsertion + " ns");
		System.out.println("TreeSet Space Complexity for Insertion	:" +SpaceComplexityTreeSetInsertion + " bytes");
		System.out.println("TreeSet Time Complexity for Deletion	:" +TimeComplexityTreeSetDeletion + " ns");
		System.out.println("TreeSet Space Complexity for Deletion	:" +SpaceComplexityTreeSetDeletion + " bytes");
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}