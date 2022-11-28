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
   * Necessary for the current implementation of buildChannelAnalysisTable().
   * May be removed with later refactoring of this method.
   */
  private String tableEntry;

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
   * @author eborchard
   */
  public void buildChannelAnalysisTable() {
	  //TODO This current implementation needs to be refactored, but it currently 
	  //handles everything expect channel conflicts.
	  
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
			  for (InstructionParameters instr : instructionParametersArray) {
				  String name = instr.getName();
				  //If it is a push or pull instruction, call setTableEntry to create the correct formatting
				  if (name.equals("push") | name.equals("pull")) {
					  String src = instr.getSrc();
					  String snk = instr.getSnk();
					  String flow = instr.getFlow();
					  setTableEntry(name, src, snk, flow);
					  String output = getTableEntry();
					  //Get the channel and set the new entry of the channel analysis table
					  int channel = Integer.parseInt(instr.getChannel());
					  channelAnalysisTable.set(channel, i, output);
				  }
			  }
		  }
	  }
   }
  
  /**
   * Private helper method that takes in an instruction's parameters and updates the global 
   * String variable, tableEntry, with the requested formatting based on if it is a push or pull instruction.
   * 
   * @author eborchard
   * @param name, a String of the current instruction name (push or pull)
   * @param src, a String of the source node for the given instruction
   * @param snk, a String of the sink node for the given instruction
   * @param flow, a String of the flow name for the given instruction
   */
  private void setTableEntry(String name, String src, String snk, String flow) {
	  if (name.equals("push")) {
		  tableEntry = String.format("[%s]::%s:(%s:%s)", src, flow, src, snk);
	  } else {
		  tableEntry = String.format("%s, %s:(%s:%s)", tableEntry, flow, src, snk);
	  }
  }
  
  /**
   * Private helper method to get tableEntry.
   * 
   * @author eborchard
   * @return tableEntry, String with formatted channel analysis table output
   */
  private String getTableEntry() {
	  return tableEntry;
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
