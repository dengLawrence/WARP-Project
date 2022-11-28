package edu.uiowa.cs.warp;

import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;

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
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected Program program;

  /**
   * Reference to ProgramSchedule within the Program class, set in constructor.
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected ProgramSchedule programTable;

  /**
   * Channel conflict exists flag
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected Boolean conflictExists;
  
  /**
   * Reference to the channel analysis ProgramSchedule, built in constructor.
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected ProgramSchedule channelAnalysisTable;

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
   * Constructor to set the program and it's schedule. Builds channel analysis table.
   * 
   * @author sgoddard
   * @param program The program to analyze
   */
  ChannelAnalysis(Program program) {
    this.program = program;
    this.programTable = program.getSchedule();
    conflictExists = false;
	buildChannelAnalysisTable();
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
  
  /**
   * This method parses the programSchedule object, programTable, to create a channel analysis table.
   * The Visualization() method within ChannelVisualization will then use this analysis table to
   * create the desired visualization (similar to how ProgramVisualization uses the "sourceCode"
   * variable to create the *dsl file visualization.
   * Should be private.
   * 
   * @author eborchard, lldeng
   */
  public void buildChannelAnalysisTable() {
	  //TODO Needs refactoring.
	  
	  //Create a new channel analysis table with the correct rows and columns based on input file
	  var numColumns = programTable.getNumRows();
	  var numRows = program.getNumChannels();
	  channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
	  
	  //Create a Warp instruction parser object to get the instruction parameters
	  var dsl = new WarpDSL();
	  
	  //Loop through each entry of programTable
	  for (int i = 0; i < numColumns; i++) {
		  for (int j = 0; j < programTable.getNumColumns(); j++) {
			  //Retrieve individual table entries
			  String instruction = programTable.get(i, j);
			  //Create an array of the instruction parameters of each entry
			  var instructionParametersArray = dsl.getInstructionParameters(instruction);
			  //Loop through these instruction parameters
			  for (int k = 0; k < instructionParametersArray.size(); k++) {
				  InstructionParameters instr = instructionParametersArray.get(k);
				  InstructionParameters nextInstr = null;
				  if(k < instructionParametersArray.size() - 1) {
					  nextInstr = instructionParametersArray.get(k+1);
				  }
				  String name = instr.getName();
				  if (name.equals("push")) {
					  String src1 = instr.getSrc();
					  String snk1 = instr.getSnk();
					  String flow1 = instr.getFlow();
					  String output = String.format("[%s]::%s:(%s:%s)", src1, flow1, src1, snk1);
					  if (nextInstr != null && nextInstr.getName().equals("pull")) {
						  String src2 = nextInstr.getSrc();
						  String snk2 = nextInstr.getSnk();
						  String flow2 = nextInstr.getFlow();
						  output += String.format(", %s:(%s:%s)", flow2, src2, snk2);
					  }
					  int channel = Integer.parseInt(instr.getChannel());
					  if(channelAnalysisTable.get(channel, i) != null) {
						  String tableString = channelAnalysisTable.get(channel, i);
						  tableString += String.format("; %s", output);
						  output = tableString;
						  conflictExists = true;
					  }
					  channelAnalysisTable.set(channel, i, output);
				  }
			  }
		  }
	  }
   }
  
  /**
   * Method that retrieves the channel analysis table created in the buildChannelAnalysisTable() method.
   * 
   * @author eborchard
   * @return ProgramSchedule The completed channel analysis table.
   */
  public ProgramSchedule getChannelAnalysisTable() {
	  return channelAnalysisTable;
   }
}
