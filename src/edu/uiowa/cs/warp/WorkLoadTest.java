package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class WorkLoadTest {

	@Test
	void testSetFlowsInDMorder() {
		//instantiation of a new WorkLoad object using StressTest.txt as test file
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		//create ArrayList of flows in their original order
		ArrayList<String> origOrder = wkld.getFlowNamesInOriginalOrder();
		//create ArrayList of flows after calling setFlowsInDMorder
		wkld.setFlowsInDMorder();
		ArrayList<String> DMorder = wkld.getFlowNamesInPriorityOrder();
		
		//find length of original order flow array
		int origOrderLength = origOrder.size();
		//find length of DM order flow array
		int DMorderLength = DMorder.size();
		//check to see if the two lengths are equivalent after method call
		assertEquals(origOrderLength, DMorderLength, "length after setting flows in DM Order:");
		
		//create a counter and loop to check if every element in the original array list is in the DM order array list
		int containsCntr = 0;
		for (String flowName : origOrder) {
			if (DMorder.contains(flowName)) 
				containsCntr++;
		}
		//check to see if the counter matches the length of the original array list
		assertEquals(origOrderLength, containsCntr, "number of equivalent flows:");
		
		//hard code of the expected array after sorting in DM order
		ArrayList<String> expectedOrder = 
				new ArrayList<String>(Arrays.asList("F1","AF1","F2","AF2","F3","F4","AF4","F5","AF5","F6","F7","F8","F9","F10","AF10"));
		//check if setFlowsInDMorder sorted in the expected order
		assertEquals(expectedOrder, DMorder, "check if correct order:");
	}
	
	@Test
	void testGetFlowNamesInPriorityOrder() {
		//instantiation of a new WorkLoad object using StressTest.txt as test file
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		
		//Create a test array list and set flowNamesInPriorityOrder with it
		ArrayList<String> expectedNotEmpty = new ArrayList<String>(Arrays.asList("This", "is", "a", "test", "."));
		wkld.setFlowNamesInPriorityOrder(expectedNotEmpty);
		//instantiation of actual array list using the proper getter method
		ArrayList<String> actualNotEmpty = wkld.getFlowNamesInPriorityOrder();
		//check if the expected array list and array list created from getter method are equal
		assertEquals(expectedNotEmpty, actualNotEmpty, "Check if getter retrieved the proper Array List:");
		
		//Create an empty array list and set flowNamesInPriorityOrder with it
		ArrayList<String> expectedEmpty = new ArrayList<>();
		wkld.setFlowNamesInPriorityOrder(expectedEmpty);
		//instantiation of actual array list using the proper getter method
		ArrayList<String> actualEmpty = wkld.getFlowNamesInPriorityOrder();
		//check if the expected array list and array list created from getter method are equal
		assertEquals(expectedEmpty, actualEmpty, "Check if getter retrieved the proper Array List:");
	}
	
	@Test
	void testGetNodeChannel() {
		//instantiation of a new WorkLoad object using StressTest.txt as test file
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		
		//The expected value of the node channel before setting to anything should be zero
		int actualWithoutSet = wkld.getNodeChannel("A");
		assertEquals(0, actualWithoutSet, "Node channel prior to setting:");
		
		//check to see if getter retrieves correct new value
		wkld.setNodeChannel("A", 44);
		int actualWithSet = wkld.getNodeChannel("A");
		assertEquals(44, actualWithSet, "Node channel after setting:");
	}
	
	@Test
	public void testGetNodeChannelThrowsNullPointerException() {
	    assertThrows(NullPointerException.class,
	            ()->{
	            	WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
	            	//if given an empty string, should throw NullPointerException
	            	wkld.getNodeChannel("");
	            });
	}
	
	@Test
	void testGetNodesInFlow() {
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		
		//example case of getNodesInFlow with a correct flow name
		String[] actual = wkld.getNodesInFlow("F9");
		String actualWithCorrectInput = Arrays.toString(actual);
		//hard code of the expected string
		String expected = "[A, B, C, D, E, J, K, L]";
		//Test to see if we got the node names in the same order as they exist in the flow specification
		assertEquals(expected, actualWithCorrectInput, "Order of nodes of flow F9 in StressTest.txt:");
		
		//example case of getNodesInFlow with incorrect flow name
		String[] actual2 = wkld.getNodesInFlow("test");
		String actualWithIncorrectInput = Arrays.toString(actual2);
		//hard code of the expected string
		String expectedEmpty = "[]";
		//Test to see if we correctly went to the else case in getNodesInFlow
		assertEquals(expectedEmpty, actualWithIncorrectInput, "Check if else case correctly implemented:");
	}
	
	@Test
	void testSetFlowNamesInOriginalOrder() {
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		
		//Create a test array list for setFlowNamesInOriginalOrder. Test array list is arbitrary
		ArrayList<String> expectedNotEmpty = new ArrayList<String>(Arrays.asList("This", "is", "a", "test", "."));
		wkld.setFlowNamesInOriginalOrder(expectedNotEmpty);
		//instantiation of actual array list using the proper getter method
		ArrayList<String> actualNotEmpty = wkld.getFlowNamesInOriginalOrder();
		//check if the expected array list and array list created from setter method are equal
		assertEquals(expectedNotEmpty, actualNotEmpty, "Check if setter created the proper Array List:");
		
		//Create an empty array list for setFlowNamesInOriginalOrder
		ArrayList<String> expectedEmpty = new ArrayList<>();
		wkld.setFlowNamesInOriginalOrder(expectedEmpty);
		//instantiation of actual array list using the proper getter method
		ArrayList<String> actualEmpty = wkld.getFlowNamesInOriginalOrder();
		//check if the expected array list and array list created from getter method are equal
		assertEquals(expectedEmpty, actualEmpty, "Check if setter created the proper Array List:");
	}
	
	@Test
	void testSetFlowsInPriorityOrder() {
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		
		//create ArrayList of flows in their original order
		ArrayList<String> origOrder = wkld.getFlowNamesInOriginalOrder();
		//create ArrayList of flows after calling setFlowsInPriorityorder
		wkld.setFlowsInPriorityOrder();
		ArrayList<String> priorityOrder = wkld.getFlowNamesInPriorityOrder();
		
		//find length of original order flow array
		int origOrderLength = origOrder.size();
		//find length of priority order flow array
		int priorityOrderLength = priorityOrder.size();
		//check to see if the two lengths are equivalent after method call
		assertEquals(origOrderLength, priorityOrderLength, "length after setting flows in Priority Order:");
		
		//create a counter and loop to check if every element in the original array list is in the priority order array list
		int containsCntr = 0;
		for (String flowName : origOrder) {
			if (priorityOrder.contains(flowName)) 
				containsCntr++;
		}
		//check to see if the counter matches the length of the original array list
		assertEquals(origOrderLength, containsCntr, "number of equivalent flows:");
		
		//hard code of the expected array after sorting in DM order
		ArrayList<String> expectedOrder = 
				new ArrayList<String>(Arrays.asList("F1","AF1","F2","AF2","F3","F4","AF4","F5","AF5","F6","F7","F8","F9","F10","AF10"));
		//check if setFlowsInDMorder sorted in the expected order
		assertEquals(expectedOrder, priorityOrder, "check if correct order:");
	}

	@Test
	void testGetMinPeriod() {
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		//The expected minimum period in StreesTest.txt is 20
		int testPeriod = wkld.getMinPeriod();
		assertEquals(20, testPeriod, "minimum period gotten from StressTest.txt:");
		
		WorkLoad wkld2 = new WorkLoad(1, 0.9, 0.99, "Example.txt");
		//This is a check for the default period which is 100
		int testPeriodDefault = wkld2.getMinPeriod();
		assertEquals(100, testPeriodDefault, "minimum period gotten from Example.txt:");
		}

	@Test
	void testMaxFlowLength() {
		//Test of fairly average case with StressTest.txt
		WorkLoad wkld1 = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		int actual1 = wkld1.maxFlowLength();
		assertEquals(8, actual1, "Max flow length for StressTest.txt");
		
		//Test of different node flow representation case with WARP_MIX_Schedule0-WarpInput.txt
		WorkLoad wkld2 = new WorkLoad(1, 0.9, 0.99, "WARP_MIX_Schedule0-WarpInput.txt");
		int actual2 = wkld2.maxFlowLength();
		assertEquals(5, actual2, "Max flow length for WARP_MIX_Schedule0-WarpInput.txt");		
		
		//Test of variable spacing and long flow length case with LongChain.txt
		WorkLoad wkld3 = new WorkLoad(1, 0.9, 0.99, "LongChain.txt");
		int actual3 = wkld3.maxFlowLength();
		assertEquals(26, actual3, "Max flow length for LongChain.txt");
		}
	
	@Test
	void testGetFlowDeadline() {
		WorkLoad wkld = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		//The expected deadline of flow F9 in StressTest.txt is 100
		int testDeadline = wkld.getFlowDeadline("F9");
		assertEquals(100, testDeadline, "deadline gotten for flow F9 in StressTest.txt:");
		
		WorkLoad wkld2 = new WorkLoad(1, 0.9, 0.99, "Example.txt");
		//This is a check for the default deadline which is 100
		int testDeadlineDefault = wkld2.getFlowDeadline("F0");
		assertEquals(100, testDeadlineDefault, "deadline gotten for flow F0 in Example.txt:");
		
		WorkLoad wkld3 = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		//Test the "Bad situation" where the flow name given does not exist
		//Should give the default of 100
		int testDeadlineFlowNameDNE = wkld3.getFlowDeadline("");
		assertEquals(100, testDeadlineFlowNameDNE, "check for a flow name that does not exist:");
		}
	
	@Test
	void testGetNodeNamesOrderedAlphabetically() {
		//check a fairly average case with StressTest.txt
		WorkLoad wkld1 = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		String[] alphaOrder1 = wkld1.getNodeNamesOrderedAlphabetically();
		String alphaOrderActual1 = Arrays.toString(alphaOrder1); 
		String expected1 = "[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, Y]";
		assertEquals(expected1, alphaOrderActual1, "check if the node names in StressTest.txt were ordered alphabetically:");
		
		//check a slightly more complex case involving a lower case letter with LongChain.txt
		WorkLoad wkld2 = new WorkLoad(1, 0.9, 0.99, "LongChain.txt");
		String[] alphaOrder2 = wkld2.getNodeNamesOrderedAlphabetically();
		String alphaOrderActual2 = Arrays.toString(alphaOrder2);
		String expected2 = "[A, B, C, D, E, F, G, H, I, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, a]";
		assertEquals(expected2, alphaOrderActual2, "check if the node names in LongChain.txt were ordered alphabetically:");
		
		//check a numerical case with WARP_MIX_Schedule0-WarpInput.txt
		WorkLoad wkld3 = new WorkLoad(1, 0.9, 0.99, "WARP_MIX_Schedule0-WarpInput.txt");
		String[] alphaOrder3 = wkld3.getNodeNamesOrderedAlphabetically();
		String alphaOrderActual3 = Arrays.toString(alphaOrder3);
		String expected3 = "[0, 1, 2, 3, 6, 9, 10, 11, 12, 13, 14, 15, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 31, 32, 36, 37, "
				+ "38, 40, 41, 42, 48, 52, 53, 54, 55, 56, 58, 59, 64, 66, 68, 71, 72, 73, 74, 76, 80, 82, 83, 84]";
		assertEquals(expected3, alphaOrderActual3, 
				"check if the node names in WARP_MIX_Schedule0-WarpInput.txt were ordered alphabetically (numerically):");
		
		//check a case where node names consist of both letter and numbers with SeeSpray.txt
		WorkLoad wkld4 = new WorkLoad(1, 0.9, 0.99, "SeeSpray.txt");
		String[] alphaOrder4 = wkld4.getNodeNamesOrderedAlphabetically();
		String alphaOrderActual4 = Arrays.toString(alphaOrder4);
		String expected4 = "[B1, B2, B3, B4, B5, B6, B7, B8, C1, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C2, C20, C21, "
				+ "C22, C23, C24, C25, C26, C27, C28, C29, C3, C30, C4, C5, C6, C7, C8, C9]";
		assertEquals(expected4, alphaOrderActual4, "check if the node names in SeeSpray.txt were ordered alphabetically:");
	}

}
