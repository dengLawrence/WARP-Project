package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

class ChannelVisualizationTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	//creates a ChannelVisualization file to be tested according to an input file and other WarpSystem parameters.
	private ChannelVisualization createChannelVisualization(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	
	//Checks that the Description object returned by visualization() matches with the lines of TestBugPriority-1Faults.ch
	//from the ICON Project BugExistsOutputFiles
	@Test
	void visualizationTestBugOutput() {
		ChannelVisualization channelVis = createChannelVisualization(1, "TestBug.txt", 15);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("0 -	-	-	-	-	-	-	-	-	-	-	-	");
		expected.add("1 [A]::F0:(A:B); [C]::F1:(C:D)	-	[E]::F1:(E:F), F1:(D:E)	-	-	-	-	-	-	-	-	-	");
		expected.add("2	-	[A]::F0:(A:B); [D]::F1:(D:E); F1:(C:D)	-	[E]::F1:(E:F)	-	-	-	-	-	-	-	-	");
		for(int i = 3; i <= 15; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-	-	-	", i));
		}
		
		assertTrue(expected.equals(actual));
	}
}
