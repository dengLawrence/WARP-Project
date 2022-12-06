package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

class ChannelAnalysisTest {

	private ChannelAnalysis createChannelAnalysis(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelAnalysis channelAnalysis = new ChannelAnalysis(warp);
		return channelAnalysis;
	}
	
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableTestEmpty() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "Empty.txt", 16);
		ProgramSchedule expected = new ProgramSchedule(16, 5);
		expected.set(1, 0, "[A]::F0:(A:B)");
		expected.set(2, 1, "[A]::F0:(A:B)");
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		assertEquals(actual, expected);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	// Tests if ExampleX is properly created and set.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableExampleXTest() {
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
		
		assertEquals(actual, expected);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	// Tests if the .get() function is functioning properly in ExampleX.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableExampleXGetTest() {
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
			
		assertEquals(actual.get(3, 5), expected.get(3, 5));
		//System.out.println(actual.get(3, 5));
		//System.out.println(expected.get(3, 5));
	}
	// Tests if ExampleX has  conflicts. It should be false.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableExampleXConflictExistsTest() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "ExampleX.txt", 16);
		Boolean actual = channelAnalysis.conflictExists;
		Boolean expected = false;
		assertEquals(actual, expected);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	// Tests if TestBug has conflicts. It should be true.
	// @author dlin4
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableExampleConflictExistsTest() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		Boolean actual = channelAnalysis.conflictExists;
		Boolean expected = true;
		assertEquals(actual, expected);
		//System.out.println(actual);
		//System.out.println(expected);
	}
}
