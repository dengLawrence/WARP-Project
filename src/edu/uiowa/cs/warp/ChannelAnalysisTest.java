package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;
import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;

class ChannelAnalysisTest {

	/* Helper method that creates a ChannelAnalysis object for testing from an inputFile and specified numFaults and numChannels.
	 * 
	 * @author lldeng
	 */
	private ChannelAnalysis createChannelAnalysis(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelAnalysis channelAnalysis = new ChannelAnalysis(warp);
		return channelAnalysis;
	}

//---------------------------------------Start of getChannelAnalysisTable() Tests-------------------------------------------------------------------//

	/* Tests that the object returned by getChannelAnalysisTable() is indeed the
	 * same object stored in the channelAnalysisTable variable of the ChannelAnalysis object by
	 * running getChannelAnalysisTable() after a table has been created for TextBug.txt.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void getChannelAnalysisTableTestTestBug() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		ProgramSchedule expected = channelAnalysis.channelAnalysisTable;
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		assertSame(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests that the object returned by getChannelAnalysisTable() is indeed the
	 * same object stored in the channelAnalysisTable variable of the ChannelAnalysis object by
	 * running getChannelAnalysisTable() after the channelAnalysisTable variable has been set
	 * to an empty 16x5 table.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void getChannelAnalysisTableTestResetTable() {
		//We take TestBug.txt as input, but we manually set the channelAnalysisTable
		//of the ChannelAnalysis object to a new empty 16x5 ProgramSchedule.
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		channelAnalysis.channelAnalysisTable = new ProgramSchedule(16, 5);
		ProgramSchedule expected = channelAnalysis.channelAnalysisTable;
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		assertSame(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
//-------------------------------------------Start of isChannelConflict() Tests---------------------------------------------------------------------//

	/* Tests that the boolean value returned by isChannelConflict() matches the
	 * boolean value stored in the conflictExists variable of the ChannelAnalysis object by
	 * running isChannelConflict() after buildChannelAnalysisTable() has been run for 
	 * TestBug.txt.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void isChannelConflictTestTestBug() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		boolean expected = channelAnalysis.conflictExists;
		boolean actual = channelAnalysis.isChannelConflict();
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests that the boolean value returned by isChannelConflict() matches the
	 * boolean value stored in the conflictExists variable of the ChannelAnalysis object.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void isChannelConflictTestSetToFalse() {
		//We take TestBug.txt as input, which will set conflictExists to false via the logic
		//of setTableEntry() within buildChannelAnalysisTable(), but we manually set conflictExists
		//to false
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		channelAnalysis.conflictExists = false;
		boolean expected = channelAnalysis.conflictExists;
		boolean actual = channelAnalysis.isChannelConflict();
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
//---------------------------------------Start of buildChannelAnalysisTable() Tests-----------------------------------------------------------------//
	
	/* Tests that the channel analysis table for BasicTest.txt is properly created and set.
	 * Since an empty channel analysis table cannot be created from an input file, BasicTest.txt
	 * represents the simplest case where only one entry is set in the channel analysis table.
	 *
	 * NOTE: BasicTest.txt will be used for later tests where the input file is not relevant to the test.
	 * @author eborchard
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTable_BasicTest() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1, 0, "[A]::F0:(A:A), F0:(A:A)");
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests that the channel analysis table for TestBug.txt is properly created and set.
	 * NOTE: Although our program includes the bug from Program.java, our output for the table
	 * differs from the TestBugPriority-1Faults.ch file from ICON, since we corrected another
	 * error present in that file, where the comma for coordinator [D]'s two flows in cell (2,1)
	 * was replaced by a semicolon.
	 * 
	 * @author lldeng
	 */
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
	
	/* Tests if the channel analysis table for ExampleX is properly created and set.
	 * 
	 * @author dlin4
	 */
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
	
	/* Tests if the .get() function of ChannelAnalysis is functioning properly in ExampleX.
	 * 
	 * @author dlin4
	 */
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
	
	/* Tests if ExampleX has channel conflicts. It should be false.
	 * 
	 * @author dlin4
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableTestExampleXConflict() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "ExampleX.txt", 16);
		Boolean actual = channelAnalysis.isChannelConflict();
		Boolean expected = false;
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests if TestBug has channel conflicts. It should be true.
	 * 
	 * @author dlin4
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void buildChannelAnalysisTableTestTestBugConflict() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "TestBug.txt", 16);
		Boolean actual = channelAnalysis.isChannelConflict();
		Boolean expected = true;
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	
	/* Tests the input file "ConflictTest.txt" that replicates the known conditions to create a channel conflict.
	 * Conflicts occur when there is no overlap of nodes between flows. This can also be seen in TestBug.txt.
	 * While the bug does not need to be fixed for this project, its replicability in the channel analysis output
	 * should be tested.
	 * 
	 * @author eborchard
	 */
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
	
//---------------------------------------Start of setTableEntry() Tests-----------------------------------------------------------------//

	/* Tests setting a null entry in a channel analysis table to a push instruction.
	 * 
	 * @author eborchard
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void setTableEntryTestPush() {
		// Test file is irrelevant. BasicTest.txt is used for simplicity.
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		
		var dsl = new WarpDSL();
		// currInstr is set to an arbitrary push instruction and nextInstr is set to null.
		String instruction = "if has(F0) push(F0: A -> B, #2)";
		var instructionParametersArray = dsl.getInstructionParameters(instruction);
		InstructionParameters currInstr = instructionParametersArray.get(0);
		InstructionParameters nextInstr = null;
		// setTableEntry requires a time slot number, current instruction and next instruction. 
		channelAnalysis.setTableEntry(1, currInstr, nextInstr);
		
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1,  0, "[A]::F0:(A:A), F0:(A:A)"); //this table entry comes from BasicTest.txt
		expected.set(2, 1, "[A]::F0:(A:B)"); //this is the table entry that is being set
			
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests setting a null entry in a channel analysis table to a push-pull instruction.
	 * 
	 * @author eborchard
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void setTableEntryTestPushPull() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		
		var dsl = new WarpDSL();
		// currInstr is set to an arbitrary push instruction & nextInstr is set to the associated pull instruction.
		String instruction = "if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)";
		var instructionParametersArray = dsl.getInstructionParameters(instruction);
		InstructionParameters currInstr = instructionParametersArray.get(0);
		InstructionParameters nextInstr = instructionParametersArray.get(1);
		channelAnalysis.setTableEntry(1, currInstr, nextInstr);
		
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1,  0, "[A]::F0:(A:A), F0:(A:A)");
		expected.set(2, 1, "[B]::F0:(B:C), F0:(A:B)");
			
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests setting a null entry in a channel analysis table to a push instruction and then
	 * causing a channel conflict with another push instruction.
	 * 
	 * @author eborchard
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void setTableEntryTestPushPushConflict() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		
		var dsl = new WarpDSL();
		// currInstr1 is set to an arbitrary push instruction and nextInstr1 is set to null.
		String instruction1 = "if has(F0) push(F0: A -> B, #2)";
		var instructionParametersArray1 = dsl.getInstructionParameters(instruction1);
		InstructionParameters currInstr1 = instructionParametersArray1.get(0);
		InstructionParameters nextInstr1 = null;
		// setTableEntry requires a time slot number, current instruction and next instruction. 
		channelAnalysis.setTableEntry(1, currInstr1, nextInstr1);
		
		// currInstr2 is set to an arbitrary push instruction and nextInstr2 is set to null.
		// currInstr2 is set to the same time slot/channel as currInstr1 so we expect a channel conflict.
		String instruction2 = "if has(F1) push(F1: B -> C, #2)";
		var instructionParametersArray2 = dsl.getInstructionParameters(instruction2);
		InstructionParameters currInstr2 = instructionParametersArray2.get(0);
		InstructionParameters nextInstr2 = null;
		channelAnalysis.setTableEntry(1, currInstr2, nextInstr2);
		
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1,  0, "[A]::F0:(A:A), F0:(A:A)");
		expected.set(2, 1, "[A]::F0:(A:B); [B]::F1:(B:C)");
			
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests setting a null entry in a channel analysis table to a push-pull instruction and then
	 * causing a channel conflict with another push instruction.
	 * 
	 * @author eborchard
	 */ 
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void setTableEntryTestPushPullPushConflict() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		
		var dsl = new WarpDSL();
		// currInstr1 is set to an arbitrary push instruction & nextInstr1 is set to the associated pull instruction.
		String instruction1 = "if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)";
		var instructionParametersArray1 = dsl.getInstructionParameters(instruction1);
		InstructionParameters currInstr1 = instructionParametersArray1.get(0);
		InstructionParameters nextInstr1 = instructionParametersArray1.get(1);
		channelAnalysis.setTableEntry(1, currInstr1, nextInstr1);
		
		// currInstr2 is set to an arbitrary push instruction and nextInstr2 is set to null.
		// currInstr2 is set to the same time slot/channel as currInstr1 so we expect a channel conflict.
		String instruction2 = "if has(F1) push(F1: C -> D, #2)";
		var instructionParametersArray2 = dsl.getInstructionParameters(instruction2);
		InstructionParameters currInstr2 = instructionParametersArray2.get(0);
		InstructionParameters nextInstr2 = null;
		channelAnalysis.setTableEntry(1, currInstr2, nextInstr2);
		
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1,  0, "[A]::F0:(A:A), F0:(A:A)");
		expected.set(2, 1, "[B]::F0:(B:C), F0:(A:B); [C]::F1:(C:D)");
			
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests setting a null entry in a channel analysis table to a push instruction and then
	 * causing a channel conflict with a push-pull instruction.
	 * 
	 * @author eborchard
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void setTableEntryTestPushPushPullConflict() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		
		var dsl = new WarpDSL();
		// currInstr1 is set to an arbitrary push instruction & nextInstr1 is set to null.
		String instruction1 = "if has(F0) push(F0: A -> B, #2)";
		var instructionParametersArray1 = dsl.getInstructionParameters(instruction1);
		InstructionParameters currInstr1 = instructionParametersArray1.get(0);
		InstructionParameters nextInstr1 = null;
		channelAnalysis.setTableEntry(1, currInstr1, nextInstr1);
		
		// currInstr2 is set to an arbitrary push instruction and nextInstr2 is set to the associated pull instruction.
		// currInstr2 is set to the same time slot/channel as currInstr1 so we expect a channel conflict.
		String instruction2 = "if has(F1) push(F1: D -> E, #2) else pull(F1: C -> D, #2)";
		var instructionParametersArray2 = dsl.getInstructionParameters(instruction2);
		InstructionParameters currInstr2 = instructionParametersArray2.get(0);
		InstructionParameters nextInstr2 = instructionParametersArray2.get(1);
		channelAnalysis.setTableEntry(1, currInstr2, nextInstr2);
		
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1,  0, "[A]::F0:(A:A), F0:(A:A)");
		expected.set(2, 1, "[A]::F0:(A:B); [D]::F1:(D:E), F1:(C:D)");
			
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
	
	/* Tests setting a null entry in a channel analysis table to a push-pull instruction and then
	 * causing a channel conflict with another push-pull instruction.
	 * 
	 * @author eborchard 
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void setTableEntryTestPushPullPushPullConflict() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "BasicTest.txt", 16);
		
		var dsl = new WarpDSL();
		// currInstr1 is set to an arbitrary push instruction & nextInstr1 is set to the associated pull instruction.
		String instruction1 = "if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)";
		var instructionParametersArray1 = dsl.getInstructionParameters(instruction1);
		InstructionParameters currInstr1 = instructionParametersArray1.get(0);
		InstructionParameters nextInstr1 = instructionParametersArray1.get(1);
		channelAnalysis.setTableEntry(1, currInstr1, nextInstr1);
		
		// currInstr2 is set to an arbitrary push instruction nextInstr2 is set to the associated pull instruction.
		// currInstr2 is set to the same time slot/channel as currInstr1 so we expect a channel conflict.
		String instruction2 = "if has(F1) push(F1: D -> E, #2) else pull(F1: C -> D, #2)";
		var instructionParametersArray2 = dsl.getInstructionParameters(instruction2);
		InstructionParameters currInstr2 = instructionParametersArray2.get(0);
		InstructionParameters nextInstr2 = instructionParametersArray2.get(1);
		channelAnalysis.setTableEntry(1, currInstr2, nextInstr2);
		
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		ProgramSchedule expected = new ProgramSchedule(16, 2);
		expected.set(1,  0, "[A]::F0:(A:A), F0:(A:A)");
		expected.set(2, 1, "[B]::F0:(B:C), F0:(A:B); [D]::F1:(D:E), F1:(C:D)");
			
		assertEquals(expected, actual);
		//System.out.println(actual);
		//System.out.println(expected);
	}
}
