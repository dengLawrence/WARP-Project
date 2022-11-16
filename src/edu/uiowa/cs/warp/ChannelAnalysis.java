package edu.uiowa.cs.warp;

import java.util.ArrayList;

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
   * Reference to the channel analysis ProgramSchedule, built in constructor.
   */
  private ProgramSchedule channelAnalysisTable;

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
   * 
   * @author eborchard
   */
  public void buildChannelAnalysisTable() {
	  //TODO Implement this method to create proper visualization of channel allocations (See MethodIdeas.md).
	  var numRows = programTable.getNumRows();
	  var numColumns = program.getNumChannels();
	  channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
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
