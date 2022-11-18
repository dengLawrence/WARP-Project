package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

class ChannelVisualizationTest {
	
	//NOTE: Until ChannelAnalysis.java is fully implemented, filled tables with flow instructions cannot be tested.

	//Helper method that creates a ChannelVisualization object to be tested according to an input file and other WarpSystem parameters.
	//@author lldeng
	private ChannelVisualization createChannelVisualization(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	
	//Helper method that creates a ChannelVisualization object to be tested according to an input file and other WarpSystem parameters.
	//MODIFIED ChannelAnalysis class (see classes below) to test 0x0 table.
	//@author lldeng
	private ChannelVisualization createChannelVisualizationModNone(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystemModNone(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	
	//Helper method that creates a ChannelVisualization object to be tested according to an input file and other WarpSystem parameters.
	//MODIFIED ChannelAnalysis class (see classes below) to test table with certain cells filled.
	//@author lldeng
	private ChannelVisualization createChannelVisualizationModFilled(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystemModFilled(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	
	//Tests that the header prints out additional information (other parameters) when numFaults is set to 0
	//@author lldeng
	@Test
	void createHeaderTestNumFaultsEqualsZero() {
		ChannelVisualization channelVis = createChannelVisualization(0, "ExampleX.txt", 16);
		Description actual = channelVis.createHeader();
		
		Description expected = new Description();
		expected.add("Channel Analysis for graph ExampleX created with the following parameters:\n");
		expected.add("Scheduler Name:	Priority\n");
		expected.add("M:\t0.9\n");
	    expected.add("E2E:\t0.99\n");
	    expected.add("nChannels:\t16\n");
		
	    //System.out.print(actual);
	    //System.out.print(expected);
		assertTrue(expected.equals(actual));
	}

	//Tests that the header created by createHeader() matches the expected header of ExampleX.txt with numFaults 1
	//@author dlin4
	@Test
	void createHeaderTestExampleX() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 16);
		Description actual = channelVis.createHeader();
		
		Description expected = new Description();
		expected.add("Channel Analysis for graph ExampleX created with the following parameters:\n");
		expected.add("Scheduler Name:	Priority\n");
		expected.add("numFaults:	1\n");
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	//Tests that the header created by createHeader() matches the expected header of Example.txt with numFaults 2
	//@author lldeng
	@Test
	void createHeaderTestExample() {
		ChannelVisualization channelVis = createChannelVisualization(2, "Example.txt", 16);
		Description actual = channelVis.createHeader();
		
		Description expected = new Description();
		expected.add("Channel Analysis for graph Example created with the following parameters:\n");
		expected.add("Scheduler Name:	Priority\n");
		expected.add("numFaults:	2\n");
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	//Tests that an empty visualization table is correctly initialized for ExampleX.txt
	//@author dlin4
	@Test
	void visualizationTestEmptyTableExampleX() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		for(int i = 0; i < 16; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	//Tests that an empty visualization table is correctly initialized for TestBug.txt
	//@author lldeng
	@Test
	void visualizationTestEmptyTableTestBug() {
		ChannelVisualization channelVis = createChannelVisualization(1, "TestBug.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	10	11	\n");
		for(int i = 0; i < 16; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	//Tests that an empty table with only one row is correctly initialized for TestBug.txt with numChannels = 1
	//@author lldeng
	@Test
	void visualizationTestOneChannelTestBug() {
		ChannelVisualization channelVis = createChannelVisualization(1, "TestBug.txt", 1);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	10	11	\n");
		for(int i = 0; i < 1; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	//Tests that an empty table with 25 rows is correctly initialized for ExampleX.txt with numChannels = 25
	//@author lldeng
	@Test
	void visualizationTestTwentyFiveChannelsExampleX() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 25);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		for(int i = 0; i < 25; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	//Tests that a table with no rows or columns is made using the modded ChannelAnalysis class(Test file is irrelevant)
	//@author lldeng
	@Test
	void visualizationTestNoTable() {
		ChannelVisualization channelVis = createChannelVisualizationModNone(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot \n");
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	//Tests that a table with actual input string values is created using the modded ChannelAnalysis class
	//@author lldeng
	@Test
	void visualizationTestPartiallyFilledTable() {
		ChannelVisualization channelVis = createChannelVisualizationModFilled(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		for(int i = 0; i < 8; i++) {
			expected.add(String.format("%s	test	test	test	test	test	test	test	test	test	test\n", i));
		}
		for(int i = 8; i < 16; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}
	
	/* Currently not in use. Will be uncommented when ChannelAnalysis is fully implemented.
	//CHECKS THE TABSSSS!!!!!!!
	//DO NOT TOUCH UNTIL CODE IS FULLY IMPLEMENTED!!!
	//Checks that the Description object returned by visualization() matches with the lines of ExampleXPriority-1Faults.ch
	//from the ICON Project BugExistsOutputFiles
	@Test
	void visualizationExampleXOutput() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 16);
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
	} */

	/*@Test
	void setAndGetTest() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 15);
		Description actual =channelVis.visualization();
		
		Description expected = new Description();
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertTrue(expected.equals(actual));
	}*/
}

//This class extends the ChannelAnalysis class to override the current implementation in order to test that
//ChannelVisualization correctly creates a visualization with no rows or columns when the ChannelAnalysis
//table has no rows or columns.
//@author lldeng
class ChannelAnalysisModNone extends ChannelAnalysis {
	
	ChannelAnalysisModNone(WarpInterface warp) {
		this(warp.toProgram());
	}

	ChannelAnalysisModNone(Program program) {
		super(program);
		buildEmptyChannelAnalysisTable();
	}
	
	private void buildEmptyChannelAnalysisTable() {
		var numColumns = 0;
		var numRows = 0;
		channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
	}
}

//This class extends the WarpSystem class to create a WarpSystem object using the modified ChannelAnalysis
//class that creates a table with no rows or columns (see class directly above)
//@author lldeng
class WarpSystemModNone extends WarpSystem {

	public WarpSystemModNone(WorkLoad workLoad, Integer numChannels, ScheduleChoices choice) {
		super(workLoad, numChannels, choice);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ChannelAnalysis toChannelAnalysis() {
		ChannelAnalysis ca = new ChannelAnalysisModNone(this);
		return ca;
	}
}

//This class extends the ChannelAnalysis class to override the current implementation in order to test that
//ChannelVisualization correctly creates a visualization with actual string values when the ChannelAnalysis
//table has those string values set in certain Channel/Timeslot cells.
//@author lldeng
class ChannelAnalysisModFilled extends ChannelAnalysis {
	
	ChannelAnalysisModFilled(WarpInterface warp) {
		this(warp.toProgram());
	}

	ChannelAnalysisModFilled(Program program) {
		super(program);
		buildFilledChannelAnalysisTable();
	}
	
	private void buildFilledChannelAnalysisTable() {
		var numColumns = programTable.getNumRows();
		var numRows = program.getNumChannels();
		channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
		for(int i = 0; i < numRows/2; i++) {
			for(int j = 0; j < numColumns; j++) {
				channelAnalysisTable.set(i, j, "test");
			}
		}
	}
}

//This class extends the WarpSystem class to create a WarpSystem object using the modified ChannelAnalysis
//class that creates a table with input strings at certain table positions (see class directly above)
//@author lldeng
class WarpSystemModFilled extends WarpSystem {

	public WarpSystemModFilled(WorkLoad workLoad, Integer numChannels, ScheduleChoices choice) {
		super(workLoad, numChannels, choice);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ChannelAnalysis toChannelAnalysis() {
		ChannelAnalysis ca = new ChannelAnalysisModFilled(this);
		return ca;
	}
}