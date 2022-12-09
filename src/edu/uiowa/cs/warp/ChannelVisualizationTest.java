package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

class ChannelVisualizationTest {
	
//-------------------------------------------Helper Methods---------------------------------------------------------------------//

	/* Helper method that creates a ChannelVisualization object to be tested according to an input file and other WarpSystem parameters.
	 * 
	 * @author lldeng
	 */
	private ChannelVisualization createChannelVisualization(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	
	/* Helper method that creates a ChannelVisualization object to be tested according to an input file and other WarpSystem parameters.
	 * MODIFIED ChannelAnalysis class (see classes below) to test 0x0 table.
	 * 
	 * @author lldeng
	 */
	private ChannelVisualization createChannelVisualizationModZeroByZero(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystemModZeroByZero(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	
	/* Helper method that creates a ChannelVisualization object to be tested according to an input file and other WarpSystem parameters.
	 * MODIFIED ChannelAnalysis class (see classes below) to test table with certain cells filled.
	 * 
	 * @author lldeng
	 */
	private ChannelVisualization createChannelVisualizationModEmpty(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystemModEmpty(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	/* Helper method that creates a ChannelVisualization object to be tested according to an input file and other WarpSystem parameters.
	 * MODIFIED ChannelAnalysis class (see classes below) to test table with cells fully filled.
	 * 
	 * @author lldeng, dlin4
	 */ 
	private ChannelVisualization createChannelVisualizationModFullyFilled(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystemModFullyFilled(workload, numChannels, choice);
		ChannelVisualization channelVis = new ChannelVisualization(warp);
		return channelVis;
	}
	
	
//-------------------------------------------Start of createHeader() Tests---------------------------------------------------------------------//
	
	/* Tests that the header prints out additional information (other parameters) when numFaults is set to 0.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
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
		assertEquals(expected, actual, "createHeader not correctly showing other parameters when numFaults = 0");
	}

	/*Tests that the header created by createHeader() matches the expected header of ExampleX.txt with numFaults = 1.
	 * 
	 * @author dlin4
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void createHeaderTestExampleX() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 16);
		Description actual = channelVis.createHeader();
		
		Description expected = new Description();
		expected.add("Channel Analysis for graph ExampleX created with the following parameters:\n");
		expected.add("Scheduler Name:	Priority\n");
		expected.add("numFaults:	1\n");
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "createHeader() makes an incorrect header for ExampleX.txt with numFaults = 1");
	}
	
	/* Tests that the header created by createHeader() matches the expected header of Example.txt with numFaults = 2.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void createHeaderTestExample() {
		ChannelVisualization channelVis = createChannelVisualization(2, "Example.txt", 16);
		Description actual = channelVis.createHeader();
		
		Description expected = new Description();
		expected.add("Channel Analysis for graph Example created with the following parameters:\n");
		expected.add("Scheduler Name:	Priority\n");
		expected.add("numFaults:	2\n");
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "createHeader() makes an incorrect header for Example.txt with numFaults = 2");
	}

//-------------------------------------------Start of visualization() Tests------------------------------------------------------------------//
	/*Tests that a visualization table is correctly created for ExampleX.txt.
	 * 
	 * @author dlin4
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestTableExampleX() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", 0));
		expected.add(String.format("%s	[A]::F0:(A:B)	-	-	[C]::F1:(C:B)	-	-	-	-	-	-\n", 1));
		expected.add(String.format("%s	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	-	-	-	-	-\n", 2));
		expected.add(String.format("%s	-	-	[B]::F0:(B:C)	-	-	[A]::F0:(A:B)	-	-	-	-\n", 3));
		expected.add(String.format("%s	-	-	-	-	-	-	[B]::F0:(B:C), F0:(A:B)	-	-	-\n", 4));
		expected.add(String.format("%s	-	-	-	-	-	-	-	[B]::F0:(B:C)	-	-\n", 5));
		expected.add(String.format("%s	-	-	-	-	[B]::F1:(B:A), F1:(C:B)	-	-	-	-	-\n", 6));
		expected.add(String.format("%s	-	-	-	-	-	-	-	-	[B]::F1:(B:A)	-\n", 7));
		for(int i = 8; i < 16; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "visualization() table is incorrect for ExampleX.txt");
	}
	
	/* Tests that a visualization table is correctly created for TestBug.txt.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestNormalTestBug() {
		ChannelVisualization channelVis = createChannelVisualization(1, "TestBug.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	10	11	\n");
		expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-	-	-\n", 0));
		expected.add(String.format("%s	[A]::F0:(A:B); [C]::F1:(C:D)	-	[E]::F1:(E:F), F1:(D:E)	-	-	-	-	-	-	-	-	-\n", 1));
		expected.add(String.format("%s	-	[A]::F0:(A:B); [D]::F1:(D:E), F1:(C:D)	-	[E]::F1:(E:F)	-	-	-	-	-	-	-	-\n", 2));
		for(int i = 3; i < 16; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "visualization() table is incorrect for TestBug.txt");
	}
	
	/* Tests that a table with only one row is correctly created for TestBug.txt with numChannels = 1.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestOneChannelTestBug() {
		ChannelVisualization channelVis = createChannelVisualization(1, "TestBug.txt", 1);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	10	11	\n");
		expected.add(String.format("%s	[A]::F0:(A:B); [C]::F1:(C:D)	-	[A]::F0:(A:B); [D]::F1:(D:E), F1:(C:D)	-	[E]::F1:(E:F), F1:(D:E)	-	[E]::F1:(E:F)	-	-	-	-	-\n", 0));
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "visualization() table is incorrect for TestBug.txt when numChannels = 1");
	}
	
	/* Tests that a table with 25 rows is correctly created for ExampleX.txt with numChannels = 25.
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestTwentyFiveChannelsExampleX() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 25);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", 0));
		expected.add(String.format("%s	[A]::F0:(A:B)	-	-	[C]::F1:(C:B)	-	-	-	-	-	-\n", 1));
		expected.add(String.format("%s	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	-	-	-	-	-\n", 2));
		expected.add(String.format("%s	-	-	[B]::F0:(B:C)	-	-	[A]::F0:(A:B)	-	-	-	-\n", 3));
		expected.add(String.format("%s	-	-	-	-	-	-	[B]::F0:(B:C), F0:(A:B)	-	-	-\n", 4));
		expected.add(String.format("%s	-	-	-	-	-	-	-	[B]::F0:(B:C)	-	-\n", 5));
		expected.add(String.format("%s	-	-	-	-	[B]::F1:(B:A), F1:(C:B)	-	-	-	-	-\n", 6));
		expected.add(String.format("%s	-	-	-	-	-	-	-	-	[B]::F1:(B:A)	-\n", 7));
		for(int i = 8; i < 25; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "visualization() table is incorrect for ExampleX.txt when numChannels = 25");
	}
	
	/* Tests that a table has the correct row index when using the .get() method.
	 * 
	 * @author dlin4
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestTableExampleXGetRowIndex() {
		ChannelVisualization channelVis = createChannelVisualization(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();

		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		for(int i = 0; i < 2; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		expected.add("2	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	-	-	-	-	-\n");
		for(int i = 2; i < 16 ; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		//System.out.print(actual.get(3));
		//System.out.print(expected.get(3));
		assertEquals(expected.get(3), actual.get(3), "Row 3 of visualization() table is incorrect/does not line up");
	}
	
	/* Tests that a table with no rows or columns is made using the modded ChannelAnalysis class(Test file is irrelevant).
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestNoTable() {
		ChannelVisualization channelVis = createChannelVisualizationModZeroByZero(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot \n");
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "0x0 visualization() table not created correctly");
	}
	
	/* Tests that an empty table is correctly created using the modded ChannelAnalysis class (Test file affects only number
	 * of rows and columns).
	 * 
	 * @author lldeng
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestPartiallyFilledTable() {
		ChannelVisualization channelVis = createChannelVisualizationModEmpty(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		for(int i = 0; i < 16; i++) {
			expected.add(String.format("%s	-	-	-	-	-	-	-	-	-	-\n", i));
		}
		
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "Empty visualization() table not correctly created");
	}
	/* Tests that a full table with actual input string values is created using the modded ChannelAnalysis class.
	 * 
	 * @author dlin4
	 */
	@Test
	@Timeout(value = 1, unit = TimeUnit.SECONDS)
	void visualizationTestFullFilledTable() {
		ChannelVisualization channelVis = createChannelVisualizationModFullyFilled(1, "ExampleX.txt", 16);
		Description actual = channelVis.visualization();
		
		Description expected = new Description();
		expected.add("Channel/Time Slot 0	1	2	3	4	5	6	7	8	9	\n");
		for(int i = 0; i < 16; i++) {
			expected.add(String.format("%s	test	test	test	test	test	test	test	test	test	test\n", i));
		}
		//System.out.print(actual);
		//System.out.print(expected);
		assertEquals(expected, actual, "Filled visualization() table with \"test\" in all cells is not correctly created");
	}
}

//-------------------------------------------End of visualization() Tests------------------------------------------------------------------//

//-------------------------------------------Class Overrides of ChannelAnalysis for Testing Purposes---------------------------------------//

/* This class extends the ChannelAnalysis class to override the current implementation in order to test that
 * ChannelVisualization correctly creates a visualization with no rows or columns when the ChannelAnalysis
 * table has no rows or columns.
 * 
 * @author lldeng
 */
class ChannelAnalysisModZeroByZero extends ChannelAnalysis {
	
	ChannelAnalysisModZeroByZero(WarpInterface warp) {
		this(warp.toProgram());
	}

	ChannelAnalysisModZeroByZero(Program program) {
		super(program);
		buildZeroByZeroChannelAnalysisTable();
	}
	
	private void buildZeroByZeroChannelAnalysisTable() {
		var numColumns = 0;
		var numRows = 0;
		channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
	}
}

/* This class extends the WarpSystem class to create a WarpSystem object using the modified ChannelAnalysis
 * class that creates a table with no rows or columns (see class directly above).
 * 
 * @author lldeng
 */
class WarpSystemModZeroByZero extends WarpSystem {

	public WarpSystemModZeroByZero(WorkLoad workLoad, Integer numChannels, ScheduleChoices choice) {
		super(workLoad, numChannels, choice);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ChannelAnalysis toChannelAnalysis() {
		ChannelAnalysis ca = new ChannelAnalysisModZeroByZero(this);
		return ca;
	}
}

/* This class extends the ChannelAnalysis class to override the current implementation in order to test that
 * ChannelVisualization correctly creates a visualization with actual string values when the ChannelAnalysis
 * table has those string values set in certain Channel/Timeslot cells.
 * 
 * @author lldeng
 */
class ChannelAnalysisModEmpty extends ChannelAnalysis {
	
	ChannelAnalysisModEmpty(WarpInterface warp) {
		this(warp.toProgram());
	}

	ChannelAnalysisModEmpty(Program program) {
		super(program);
		buildEmptyChannelAnalysisTable();
	}
	
	private void buildEmptyChannelAnalysisTable() {
		var numColumns = programTable.getNumRows();
		var numRows = program.getNumChannels();
		channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
	}
}

/* This class extends the WarpSystem class to create a WarpSystem object using the modified ChannelAnalysis
 * class that creates a table with input strings at certain table positions (see class directly above).
 * 
 * @author lldeng
 */
class WarpSystemModEmpty extends WarpSystem {

	public WarpSystemModEmpty(WorkLoad workLoad, Integer numChannels, ScheduleChoices choice) {
		super(workLoad, numChannels, choice);
		// TODO Auto-generated constructor stub
	}
	@Override
	public ChannelAnalysis toChannelAnalysis() {
		ChannelAnalysis ca = new ChannelAnalysisModEmpty(this);
		return ca;
	}
}	
/* This class extends the ChannelAnalysis class to override the current implementation in order to test that
 * ChannelVisualization correctly creates a visualization with actual string values when the ChannelAnalysis
 * table has those string values set in all Channel/Timeslot cells.
 * 
 * @author lldeng, dlin4
 */
class ChannelAnalysisModFullyFilled extends ChannelAnalysis {
		
		ChannelAnalysisModFullyFilled(WarpInterface warp) {
			this(warp.toProgram());
		}

		ChannelAnalysisModFullyFilled(Program program) {
			super(program);
			buildFullyFilledChannelAnalysisTable();
		}
		
		private void buildFullyFilledChannelAnalysisTable() {
			var numColumns = programTable.getNumRows();
			var numRows = program.getNumChannels();
			channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numColumns; j++) {
					channelAnalysisTable.set(i, j, "test");
				}
			}
		}
	}
/* This class extends the WarpSystem class to create a WarpSystem object using the modified ChannelAnalysis
 * class that creates a table with input strings at all table positions (see class directly above).
 * 
 * @author lldeng, dlin4
 */
class WarpSystemModFullyFilled extends WarpSystem {

		public WarpSystemModFullyFilled(WorkLoad workLoad, Integer numChannels, ScheduleChoices choice) {
			super(workLoad, numChannels, choice);
			// TODO Auto-generated constructor stub
		}

		@Override
		public ChannelAnalysis toChannelAnalysis() {
			ChannelAnalysis ca = new ChannelAnalysisModFullyFilled(this);
			return ca;
		}
	}
