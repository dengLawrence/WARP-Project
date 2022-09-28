package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class WorkLoadTest {

	@Test
	void testGetMinPeriod() {
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "Example1a.txt");
		int testPeriod = wkld.getMinPeriod();
		assertEquals(10, testPeriod, "minimum period gotten.");
		}

	@Test
	void testSetFlowNamesInOriginalOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testSetFlowNamesInPriorityOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNodeChannel() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFlowDeadline() {
		fail("Not yet implemented");
	}

	@Test
	void testSetFlowsInPriorityOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testSetFlowsInDMorder() {
		//instantiation of a new WorkLoad object using StressTest.txt as test file
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		//create ArrayList of flows in their original order
		ArrayList<String> origOrder = wkld.getFlowNamesInOriginalOrder();
		System.out.println(origOrder);
		//create ArrayList of flows after calling setFlowsInDMorder
		wkld.setFlowsInDMorder();
		ArrayList<String> DMorder = wkld.getFlowNamesInPriorityOrder();
		System.out.println(DMorder);
		
		//find length of original order flow array
		int origOrderLength = origOrder.size();
		System.out.println(origOrderLength);
		//find length of DM order flow array
		int DMorderLength = DMorder.size();
		System.out.println(DMorderLength);
		//check to see if the two lengths are equivalent after method call
		assertEquals(origOrderLength, DMorderLength, "length after setting flows in DM Order:");
		
		//create a counter and loop to check if every element in the original array list is in the DM order array list
		int containsCntr = 0;
		for (String flowName : origOrder) {
			if (DMorder.contains(flowName)) 
				containsCntr++;
		}
		System.out.println(containsCntr);
		//check to see if the counter matches the length of the original array list
		assertEquals(origOrderLength, containsCntr, "number of equivalent flows:");
		
		//hard code of the expected array after sorting in DM order
		ArrayList<String> expectedOrder = 
				new ArrayList<String>(Arrays.asList("F1","AF1","F2","AF2","F3","F4","AF4","F5","AF5","F6","F7","F8","F9","F10","AF10"));
		//check if setFlowsInDMorder sorted in the expected order
		assertEquals(expectedOrder, DMorder, "check if correct order:");
	}

	@Test
	void testGetNodeNamesOrderedAlphabetically() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNodesInFlow() {
		fail("Not yet implemented");
	}

	@Test
	void testMaxFlowLength() {
		fail("Not yet implemented");
	}

}
