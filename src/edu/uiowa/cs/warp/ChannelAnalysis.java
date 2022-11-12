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
      // TODO temporarily hard code the output from a *.ch file to return to the Visualization() method.
      throw new UnsupportedOperationException("not implemented");
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
