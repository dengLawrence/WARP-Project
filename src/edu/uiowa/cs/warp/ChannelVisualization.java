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
    String columnString = "Channel/Time Slot ";
    /* loop through the time slots, stopping just before last time slot */
    for (Integer columnNumber = 0; columnNumber < sourceCode.getNumColumns() - 1; columnNumber++) {
    	columnString += columnNumber.toString() + "\t";
    }
    /* add the last time slot with \n instead of \t at the end */
    columnString += (sourceCode.getNumColumns() - 1) + "\n";
    content.add(columnString);
    
    
    for (Integer rowIndex = 0; rowIndex < sourceCode.getNumRows(); rowIndex++) {
    	String rowString = rowIndex.toString();
    	for (Integer columnIndex = 0; columnIndex < sourceCode.getNumColumns(); columnIndex++) {
        	var entry = sourceCode.get(rowIndex, columnIndex);
    		if (entry == null) {
    			rowString += "\t-";
    		}
    		else {
    			rowString += "\t" + entry;
    		}
    		
    		if (columnIndex == sourceCode.getNumColumns() - 1) {
    			rowString += "\n";
    		}
    	}
    	content.add(rowString);
      }
    
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
