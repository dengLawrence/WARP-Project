package edu.uiowa.cs.warp;

import java.util.ArrayList;

/**
 * ChannelVisualization creates the visualizations for the channel analysis of the WARP program.
 * <p>
 * 
 * CS2820 Fall 2022 Project: Implement this class to create the file visualization that is requested
 * in Warp.
 * 
 * @author sgoddard
 * @version 1.4
 *
 */
public class ChannelVisualization extends VisualizationObject {

  private static final String SOURCE_SUFFIX = ".ch";
  private static final String OBJECT_NAME = "Channel Analysis";
  private WarpInterface warp;
  private ChannelAnalysis ca;
  
  /**
   * Reference to ProgramSchedule, set in constructor
   */
  private ProgramSchedule sourceCode;

  /**
   * Reference to the program, set in constructor
   */
  private Program program;

  ChannelVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.warp = warp;
    this.program = warp.toProgram();
    this.ca = warp.toChannelAnalysis();
    //this.sourceCode = ca.getChannelAnalysisTable();
  }
  
// Method stub for the visualization method that parses the ProgramSchedule object from
// getChannelAnalysisTable() to add the necessary strings to the Description variable, content,
// to create the output specified in the project pdf file. 
  @Override
  public Description visualization() {
    Description content = new Description();

    String nodeString = "Channel/Time Slot\n";
    
    //TODO: code the formatting for the column and row labels
    
    content.add(nodeString);
    
    //TODO: add the rows of sourceCode to content
    
    return content;
  }

  
  /**
   * Creates a Description object for the header of the visualization output.
   * 
   * @author sgoddard
   * @return Description containing visualization of the header
   */
  @Override
  public Description createHeader() {
    Description header = new Description();

    header.add(String.format("Channel Analysis for graph %s created with the following parameters:\n",
        program.getName()));
    header.add(String.format("Scheduler Name:\t%s\n", program.getSchedulerName()));

    /* The following parameters are output based on a special schedule or the fault model */
    if (program.getNumTransmissions() > 0) { // only specify when NumTransmissions is fixed
      header.add(String.format("nTransmissions:\t%d\n", program.getNumTransmissions()));
    } else if (program.getNumFaults() > 0) {
      header.add(String.format("numFaults:\t%d\n", program.getNumFaults()));
    } else {
      header.add(String.format("M:\t%s\n", String.valueOf(program.getMinPacketReceptionRate())));
      header.add(String.format("E2E:\t%s\n", String.valueOf(program.getE2e())));
      header.add(String.format("nChannels:\t%d\n", program.getNumChannels()));
    }
    if (!program.getOptimizationFlag()) { // only specify when optimization not requested
      header.add("Optimization Requested:\tFalse\n");
    }
    return header;
  }
  
}
