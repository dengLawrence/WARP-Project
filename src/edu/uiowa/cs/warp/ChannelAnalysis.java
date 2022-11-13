package edu.uiowa.cs.warp;

/**
 * <h1>Implementation of the ChannelAnalysis class</h1>
 * Used to analyze either Program or WarpInterface by converting to program.
 * Can be used to detect channel conflicts.
 * 
 * @author sgoddard
 * @version 1.4
 *
 */
public class ChannelAnalysis {

  /**
   * Reference to the program, set in constructor.
   */
  private Program program;

  /**
   * Reference to ProgramSchedule within the Program class, set in constructor.
   */
  private ProgramSchedule programTable;

  /**
   * Channel conflict exists flag
   */
  private Boolean conflictExists;

  /**
   * Constructor that takes in a WarpInterface and converts it to a a program, calls the other 
   * class constructor with the Program object parameter then sets the program and it's schedule.
   * 
   * @author sgoddard
   * @param warp WarpInterface to analyze
   */
  ChannelAnalysis(WarpInterface warp) {
    this(warp.toProgram());
  }

  /**
   * Constructor to set the program and it's schedule.
   * 
   * @author sgoddard
   * @param program The program to analyze
   */
  ChannelAnalysis(Program program) {
    this.program = program;
    this.programTable = program.getSchedule();
    conflictExists = false;
  }
  
  /**
   * Method that retrieves the channel analysis table created in the createChannelAnalysisTable() method.
   * 
   * @author eborchard
   * @return ProgramSchedule
   */
  public ProgramSchedule getChannelAnalysisTable() {
	  return programTable;
   }
  
  public ProgramSchedule getDummyChannelAnalysisTable() {
	  ProgramSchedule dummyTable = new ProgramSchedule();
	  
	  InstructionTimeSlot channel0 = new InstructionTimeSlot();
	  channel0.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(0,channel0);
	  
	  InstructionTimeSlot channel1 = new InstructionTimeSlot();
	  channel1.add("	[A]::F0:(A:B)	-	-	[C]::F1:(C:B)	-	-	-	-	-	-	");
	  dummyTable.add(1,channel1);

	  InstructionTimeSlot channel2 = new InstructionTimeSlot();
	  channel2.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(2,channel2);
	  
	  InstructionTimeSlot channel3 = new InstructionTimeSlot();
	  channel3.add("	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	[A]::F0:(A:B)	-	-	-	-	");
	  dummyTable.add(3,channel3);

	  InstructionTimeSlot channel4 = new InstructionTimeSlot();
	  channel4.add("	-	-	[B]::F0:(B:C)	-	-	-	-	-	-	-	");
	  dummyTable.add(4,channel4);
	  
	  InstructionTimeSlot channel5 = new InstructionTimeSlot();
	  channel5.add("	-	-	-	-	-	-	[B]::F0:(B:C), F0:(A:B)	-	-	-	");
	  dummyTable.add(5,channel5);

	  InstructionTimeSlot channel6 = new InstructionTimeSlot();
	  channel6.add("	-	-	-	-	-	-	-	[B]::F0:(B:C)	-	-	");
	  dummyTable.add(6,channel6);
	  
	  InstructionTimeSlot channel7 = new InstructionTimeSlot();
	  channel7.add("	-	-	-	-	[B]::F1:(B:A), F1:(C:B)	-	-	-	-	-	");
	  dummyTable.add(7,channel7);
	  
	  InstructionTimeSlot channel8 = new InstructionTimeSlot();
	  channel8.add("	-	-	-	-	-	-	-	-	[B]::F1:(B:A)	-	");
	  dummyTable.add(8,channel8);
	  
	  InstructionTimeSlot channel9 = new InstructionTimeSlot();
	  channel9.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(9,channel9);

	  InstructionTimeSlot channel10 = new InstructionTimeSlot();
	  channel10.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(10,channel10);
	  
	  InstructionTimeSlot channel11 = new InstructionTimeSlot();
	  channel11.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(11,channel11);

	  InstructionTimeSlot channel12 = new InstructionTimeSlot();
	  channel12.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(12,channel12);
	  
	  InstructionTimeSlot channel13 = new InstructionTimeSlot();
	  channel13.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(13,channel13);

	  InstructionTimeSlot channel14 = new InstructionTimeSlot();
	  channel14.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(14,channel14);
	  
	  InstructionTimeSlot channel15 = new InstructionTimeSlot();
	  channel15.add("	-	-	-	-	-	-	-	-	-	-	");
	  dummyTable.add(15,channel15);
	  
	  
	  return dummyTable;
   }
  
  /**
   * This method parses the programSchedule object, programTable, to create a channel analysis table.
   * The Visualization() method within ChannelVisualization will then use this analysis table to
   * create the desired visualization (similar to how ProgramVisualization uses the "sourceCode"
   * variable to create the *dsl file visualization.
   * 
   * @author eborchard
   */
  public void createChannelAnalysisTable() {
      System.out.println(this.programTable);
   }

  /**
   * Determines whether or not there is a channel conflict.
   * 
   * @author sgoddard
   * @return boolean based on if there is a channel conflict or not
   */
  public Boolean isChannelConflict() {
    return conflictExists;
  }
}
