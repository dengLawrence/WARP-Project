package edu.uiowa.cs.warp;

import java.util.HashMap;

/**
 * @author sgoddard
 * @version 1.4
 *
 */
public class ChannelAnalysis {

  private Program program;
  private WorkLoad workload;
  private ProgramSchedule programTable;
  private HashMap<String, Integer> nodeIndex;

  ChannelAnalysis(WarpInterface warp) {
    this.program = warp.toProgram();
    this.workload = program.toWorkLoad();
    this.programTable = program.getSchedule();
    this.nodeIndex = program.getNodeMapIndex();
  }

  ChannelAnalysis(Program program) {
    this.program = program;
    this.workload = program.toWorkLoad();
    this.programTable = program.getSchedule();
    this.nodeIndex = program.getNodeMapIndex();
  }

  public Boolean isChannelConflict() {
    // TODO Auto-generated method stub
    return false;
  }
}
