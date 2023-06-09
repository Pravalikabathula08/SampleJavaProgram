package com.rgt.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HashMapvsTreeMap 
{
	public static long getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}
	
	
	public static void main(String[] args) 
	{
		/**
		 * HashMap Insertion ,Deletion Time and Space Complexity
		 */
		Map<Integer,String> hashMap = new HashMap<Integer, String>();
		long startTime, endTime;
		long memoryBefore, memoryAfter;

		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();

		for (int i = 0; i < 100000; i++) {
			hashMap.put(i, "value"+i);
		}
		memoryAfter = getMemoryUsage();
		long SpaceComplexityInsertion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long TimeComplexityInsertion = endTime - startTime;
		
		hashMap.remove(1000);
		memoryAfter = getMemoryUsage();
		long SpaceComplexityDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long TimeComplexityDeletion = endTime - startTime;

		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("********HashMap***********");
		System.out.println("HashMap Time Complexity for Insertion	:" +TimeComplexityInsertion + " ns ");
		System.out.println("HashMap Space Complexity for Insertion	:" +SpaceComplexityInsertion + " bytes ");
		System.out.println("HashMap Time Complexity for Deletion	:" +TimeComplexityDeletion + " ns");
		System.out.println("HashMap Space Complexity for Deletion	:" +SpaceComplexityDeletion + " bytes");
		System.out.println("--------------------------------------------------------------------------------------------");
	
		/**
		 * For TreeMap Insertion ,Deletion Time and Space Complexity
		 */

		Map<Integer,String> treeMap = new TreeMap<Integer, String>();
		
		for (int i = 0; i < 100000; i++) {
			treeMap.put(i,"value"+i);
		}
		long SpaceComplexityTreeMapInsertion = memoryAfter - memoryBefore;
		long TimeComplexityTreeMapInsertion = endTime - startTime;
		
		treeMap.remove(1000);
		memoryAfter = getMemoryUsage();
		long SpaceComplexityTreeMapDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long TimeComplexityTreeMapDeletion = endTime - startTime;
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("********TreeMap***********");
		System.out.println("TreeMap Time Complexity for Insertion	:" +TimeComplexityTreeMapInsertion + " ns");
		System.out.println("TreeMap Space Complexity for Insertion	:" +SpaceComplexityTreeMapInsertion + " bytes");
		System.out.println("TreeMap Time Complexity for Deletion	:" +TimeComplexityTreeMapDeletion + " ns");
		System.out.println("TreeMap Space Complexity for Deletion	:" +SpaceComplexityTreeMapDeletion + " bytes");
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}