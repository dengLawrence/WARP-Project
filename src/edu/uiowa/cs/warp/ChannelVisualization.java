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

  ChannelVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.warp = warp;
    this.ca = warp.toChannelAnalysis();
  }
  
  /**
   * Creates a Description object for channel visualization.
   * 
   * @author eborchard
   * @return Description containing visualization of the channel analysis
   */
  @Override
  public Description visualization() {
    Description content = new Description();
    ProgramSchedule sourceCode = ca.getChannelAnalysisTable();

    /* create schedule output header of column time slots in order, with \t as separator */
    String timeSlotString = "Channel/Time Slot ";
    /* loop through the time slots, stopping just before last time slot */
    for (Integer timeSlotNumber = 0; timeSlotNumber < sourceCode.size() - 1; timeSlotNumber++) {
    	timeSlotString += timeSlotNumber.toString() + "\t";
    }
    /* add the last time slot with \n instead of \t at the end */
    timeSlotString += (sourceCode.size() - 1) + "\n";
    content.add(timeSlotString);
    
    ProgramSchedule dummyTable = ca.getDummyChannelAnalysisTable();

    for (int rowIndex = 0; rowIndex < dummyTable.size(); rowIndex++) {
        ArrayList<String> rowArrayList = dummyTable.get(rowIndex);
        var row = rowArrayList.toArray(new String[rowArrayList.size()]);
        String rowString = String.format("%d\t", rowIndex) + String.join("\t", row) + "\n";
        content.add(rowString);
      }  
    
//    content.add("\n\n");
//    
//    for (int rowIndex = 0; rowIndex < sourceCode.size(); rowIndex++) {
//        ArrayList<String> rowArrayList = sourceCode.get(rowIndex);
//        var row = rowArrayList.toString();
//        content.add(row + "\n");
//      }
    
    return content;
  }

  
  /**
   * Creates a Description object for the header of the channel visualization output.
   * 
   * @author sgoddard (modified: eborchard)
   * @return Description containing visualization of the header
   */
  @Override
  public Description createHeader() {
    Description header = new Description();

    header.add(String.format(OBJECT_NAME + "for graph %s created with the following parameters:\n",
        warp.getName()));
    header.add(String.format("Scheduler Name:\t%s\n", warp.getSchedulerName()));

    /* The following parameters are output based on a special schedule or the fault model */
    if (warp.getNumTransmissions() > 0) { // only specify when NumTransmissions is fixed
      header.add(String.format("nTransmissions:\t%d\n", warp.getNumTransmissions()));
    } else if (warp.getNumFaults() > 0) {
      header.add(String.format("numFaults:\t%d\n", warp.getNumFaults()));
    } else {
      header.add(String.format("M:\t%s\n", String.valueOf(warp.getMinPacketReceptionRate())));
      header.add(String.format("E2E:\t%s\n", String.valueOf(warp.getE2e())));
      header.add(String.format("nChannels:\t%d\n", warp.getNumChannels()));
    }
    if (!warp.getOptimizationFlag()) { // only specify when optimization not requested
      header.add("Optimization Requested:\tFalse\n");
    }
    return header;
  }
  
}
