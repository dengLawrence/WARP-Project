package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

class ChannelAnalysisTest {

	//Helper method that creates a ChannelAnalysis object for testing from an inputFile and specified numFaults and numChannels.
	//@author lldeng
	private ChannelAnalysis createChannelAnalysis(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelAnalysis channelAnalysis = new ChannelAnalysis(warp);
		return channelAnalysis;
	}
	
	//@author lldeng
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableTestEmpty() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "Empty.txt", 16);
		ProgramSchedule expected = new ProgramSchedule(16, 5);
		expected.set(1, 0, "[A]::F0:(A:B)");
		expected.set(2, 1, "[A]::F0:(A:B)");
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	//Tests that the channel analysis table for TestBug.txt is properly created and set.
	//NOTE: Although our program includes the bug from Program.java, our output for the table
	//differs from the TestBugPriority-1Faults.ch file from ICON, since we corrected another
	//error present in that file, where the comma for coordinator [D]'s two flows in cell (2,1)
	//was replaced by a semicolon.
	//@author lldeng
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableTestTestBug() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 12);
		expected.set(1,  0, "[A]::F0:(A:B); [C]::F1:(C:D)");
		expected.set(1, 2, "[E]::F1:(E:F), F1:(D:E)");
		//Corrected to comma instead of semicolon found on ICON file (see above test method comment)
		expected.set(2, 1, "[A]::F0:(A:B); [D]::F1:(D:E), F1:(C:D)");
		expected.set(2, 3, "[E]::F1:(E:F)");
		
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	// Tests if the channel analysis table for ExampleX is properly created and set.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableTestExampleX() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "ExampleX.txt", 16);
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 10);
		expected.set(1,  0, "[A]::F0:(A:B)");
		expected.set(1, 3, "[C]::F1:(C:B)");
		expected.set(2, 1, "[B]::F0:(B:C), F0:(A:B)");
		expected.set(3, 2, "[B]::F0:(B:C)");
		expected.set(3, 5, "[A]::F0:(A:B)");
		expected.set(4, 6, "[B]::F0:(B:C), F0:(A:B)");
		expected.set(5, 7, "[B]::F0:(B:C)");
		expected.set(6, 4, "[B]::F1:(B:A), F1:(C:B)");
		expected.set(7, 8, "[B]::F1:(B:A)");
		
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	// Tests if the .get() function of ChannelAnalysis is functioning properly in ExampleX.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void getChannelAnalysisTableTestExampleX() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "ExampleX.txt", 16);
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 10);
		expected.set(1,  0, "[A]::F0:(A:B)");
		expected.set(1, 3, "[C]::F1:(C:B)");
		expected.set(2, 1, "[B]::F0:(B:C), F0:(A:B)");
		expected.set(3, 2, "[B]::F0:(B:C)");
		expected.set(3, 5, "[A]::F0:(A:B)");
		expected.set(4, 6, "[B]::F0:(B:C), F0:(A:B)");
		expected.set(5, 7, "[B]::F0:(B:C)");
		expected.set(6, 4, "[B]::F1:(B:A), F1:(C:B)");
		expected.set(7, 8, "[B]::F1:(B:A)");
			
		assertEquals(expected.get(3, 5), actual.get(3, 5));
		//System.out.println(actual.get(3, 5));
		//System.out.println(expected.get(3, 5));
	}
	
	// Tests if ExampleX has channel conflicts. It should be false.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void isChannelConflictTestExampleX() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "ExampleX.txt", 16);
		Boolean actual = channelAnalysis.isChannelConflict();
		Boolean expected = false;
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	// Tests if TestBug has channel conflicts. It should be true.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void isChannelConflictTestTestBug() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		Boolean actual = channelAnalysis.isChannelConflict();
		Boolean expected = true;
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
}
