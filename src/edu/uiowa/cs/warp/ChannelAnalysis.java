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
   * @author eborchard
   */
  public void buildChannelAnalysisTable() {
	  //TODO Implement this method to create proper visualization of channel allocations (See MethodIdeas.md).
	  var numColumns = programTable.getNumRows();
	  var numRows = program.getNumChannels();
	  channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
	  
	  var dsl = new WarpDSL();
	  
	  for (int i = 0; i < numColumns; i++) {
		  for (int j = 0; j < programTable.getNumColumns(); j++) {
			  String instr = programTable.get(i, j);
			  var instructionParametersArray = dsl.getInstructionParameters(instr);
			  for (InstructionParameters entry : instructionParametersArray) {
				  String name = entry.getName();
				  if (name.equals("push")) {
					  String src = entry.getSrc();
					  String snk = entry.getSnk();
					  String flow = entry.getFlow();
					  String output = String.format("[%s]::%s:(%s:%s)", src, flow, src, snk);
					  int channel = Integer.parseInt(entry.getChannel());
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
