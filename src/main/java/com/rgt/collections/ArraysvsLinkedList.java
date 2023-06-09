package com.rgt.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArraysvsLinkedList {
	public static long getMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.totalMemory() - runtime.freeMemory();
	}

	public static void main(String[] args) {
		/**
		 * ArrayList Insertion ,Deletion Time and Space Complexity
		 */
		List<Integer> arrayList = new ArrayList<Integer>();
		long startTime, endTime;
		long memoryBefore, memoryAfter;

		startTime = System.nanoTime();
		memoryBefore = getMemoryUsage();
		for (int i = 0; i < 100000; i++) {
			arrayList.add(i);
		}
		memoryAfter = getMemoryUsage();
		long SpaceComplexityInsertion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long ArrayListTimeComplexityInsertion = endTime - startTime;

		arrayList.remove(90000);
		memoryAfter = getMemoryUsage();
		long SpaceComplexityDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long ArrayListTimeComplexityDeletion = endTime - startTime;
		System.out
				.println("------------------------------------------------------------------------------------------");
		System.out.println("********ARRAY LIST***********");
		System.out.println("ArrayList Time Complexity for Insertion		:" + ArrayListTimeComplexityInsertion + " ns ");
		System.out.println("ArrayList Space Complexity for Insertion	:" + SpaceComplexityInsertion + " bytes ");
		System.out.println("ArrayList Time Complexity for Deletion		:" + ArrayListTimeComplexityDeletion + " ns");
		System.out.println("ArrayList Space Complexity for Deletion		:" + SpaceComplexityDeletion + " bytes");
		System.out.println(
				"--------------------------------------------------------------------------------------------");

		/**
		 * For Linked List Insertion ,Deletion Time and Space Complexity
		 */

		List<Integer> linkedList = new LinkedList<Integer>();

		for (int i = 0; i < 100000; i++) {
			linkedList.add(i);
		}
		long SpaceComplexitylinkedlistInsertion = memoryAfter - memoryBefore;
		long TimeComplexitylinkedlistInsertion = endTime - startTime;

		linkedList.remove(1000);
		memoryAfter = getMemoryUsage();
		long SpaceComplexitylinkedlistDeletion = memoryAfter - memoryBefore;
		endTime = System.nanoTime();
		long TimeComplexitylinkedlistDeletion = endTime - startTime;
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("********LinkedList LIST***********");
		System.out.println("LinkedList Time Complexity for Insertion	:" + TimeComplexitylinkedlistInsertion + " ns");
		System.out.println("LinkedList Space Complexity for Insertion	:" + SpaceComplexitylinkedlistInsertion + " bytes");
		System.out.println("LinkedList Time Complexity for Deletion		:" + TimeComplexitylinkedlistDeletion + " ns");
		System.out.println("LinkedList Space Complexity for Deletion	:" + SpaceComplexitylinkedlistDeletion + " bytes");
		System.out.println("--------------------------------------------------------------------------------------------");
	}
}