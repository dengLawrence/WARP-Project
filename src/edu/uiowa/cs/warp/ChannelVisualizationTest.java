package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

class ChannelVisualizationTest {


	//creates a ChannelVisualization file to be tested according to an input file and other WarpSystem parameters.
	private ChannelVisualization createChannelVisualization(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	// CHECKS THE TABSSSS!!!!!!!
	// DO NOT TOUCH UNTIL CODE IS FULLY IMPLEMENTED!!!
	//Checks that the Description object returned by visualization() matches with the lines of ExampleXPriority-1Faults.ch
	//from the ICON Project BugExistsOutputFiles
	@Test
	void visualizationExampleXOutput() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 15);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9\n");
		expected.add("0	-	-	-	-	-	-	-	-	-	-	\n");
		expected.add("1	[A]::F0:(A:B)	-	-	[C]::F1:(C:B)	-	-	-	-	-	-	\n");
		expected.add("2	-	-	-	-	-	-	-	-	-	-	\n");
		expected.add("3	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	[A]::F0:(A:B)	-	-	-	-	\n");
		expected.add("4	-	-	[B]::F0:(B:C)	-	-	-	-	-	-	-	\n");
		expected.add("5	-	-	-	-	-	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	\n");
		expected.add("6	-	-	-	-	-	-	-	[B]::F0:(B:C)	-	-	\n");
		expected.add("7	-	-	-	-	[B]::F1:(B:A), F1:(C:B)	-	-	-	-	-	\n");
		expected.add("8	-	-	-	-	-	-	-	-	[B]::F1:(B:A)	-	\n");
		for(int i = 9; i <= 15; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-	\n", i));
		}
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
 
	@Test
	void HeaderTest() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 15);
		Description actual = channelVis.createHeader();
		
		Description expected = new Description();
		expected.add("Channel Analysisfor graph ExampleX created with the following parameters:\n");
		expected.add("Scheduler Name:	Priority\n");
		expected.add("numFaults:	1\n");
		
		System.out.print(expected.get(2));
		System.out.print(actual.get(2));
		assertTrue(expected.get(2).equals(actual.get(2)));
	}
}