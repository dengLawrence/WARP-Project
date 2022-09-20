package edu.uiowa.cs.warp;

/**
 * <h1>Implementation of the ChannelAnalysis class</h1>
 * Used to analyze either Program or WarpInterface by converting to program.
 * Can be used to detect channel conflicts.
 * @author sgoddard
 * @version 1.4
 *
 */
public class ChannelAnalysis {

  /**
   * Reference to the program, set in constructor
   */
  private Program program;

  /**
   * Reference to ProgramSchedule withing the Program class, set in constructor
   */
  private ProgramSchedule programTable;

  /**
   * Channel conflict exists flag
   */
  private Boolean conflictExists;

  /**
   * Constructor that takes in a WarpInterface and converts it to a a program then sets the program and it's schedule
   * @param warp WarpInterface to analyze
   */
  ChannelAnalysis(WarpInterface warp) {
    this.program = warp.toProgram();
    this.programTable = program.getSchedule();
    conflictExists = false;
  }

  /**
   * Constructor to set the program and it's schedule
   * @param program The program to analyze
   */
  ChannelAnalysis(Program program) {
    this.program = program;
    this.programTable = program.getSchedule();
    conflictExists = false;
  }

  /**
   * Determines whether or not there is a channel conflict
   * @return boolean based on if there is a channel conflict or not
   */
  public Boolean isChannelConflict() {
    return conflictExists;
  }
}
