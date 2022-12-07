package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;
import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;

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
	void buildChannelAnalysisTableTestBasic() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1, 0, "[A]::F0:(A:A), F0:(A:A)");
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
	
	// Tests the input file "ConflictTest.txt" that replicates the known conditions to create a channel conflict.
	// Conflicts occur when there is no overlap of nodes between flows. This can also be seen in TestBug.txt.
	// While the bug does not need to be fixed for this project, its replicability in the channel analysis output
	// should be tested.
	// @author eborchard
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void channelConflictReplicationTest() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "ConflictTest.txt", 16);
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 10);
		expected.set(1,  0, "[A]::F0:(A:B); [C]::F1:(C:D)");
		expected.set(2, 1, "[A]::F0:(A:B); [C]::F1:(C:D)");
			
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	// @author eborchard
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void setTableEntryTest() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		var dsl = new WarpDSL();
		String instruction = "if has(F0) push(F0: A -> B, #1)";
		var instructionParametersArray = dsl.getInstructionParameters(instruction);
		InstructionParameters currInstr = instructionParametersArray.get(0);
		InstructionParameters nextInstr = null;
		channelAnalysis.setTableEntry(1, currInstr, nextInstr);
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		//System.out.println(actual);
	}
}
