package edu.uiowa.cs.warp;

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

  /**
   * Source file extension format
   */
  private static final String SOURCE_SUFFIX = ".ch";
  
  /**
   * Object name for formatting
   */
  private static final String OBJECT_NAME = "Channel Analysis";
  
  /**
   * Reference to the warp, set in constructor
   */
  private WarpInterface warp;
  
  /**
   * Reference to ChannelAnalysis, set in constructor
   */
  private ChannelAnalysis ca;
  
  /**
   * Constructor converts WarpInterface to ChannelAnalysis for visualization.
   * 
   * @author sgoddard
   * @param warp WarpInterface to convert to ChannelAnalysis for visualization
   */
  ChannelVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.warp = warp;
    this.ca = warp.toChannelAnalysis();
  }
  
  /**
   * Creates a Description object for channel visualization by formatting the columns with the time slot numbers and
   * adding rows by parsing individual table entries of the ProgramSchedule retrieved from getChannelAnalysisTable().
   * 
   * @author eborchard
   * @return Description containing visualization of the channel analysis
   */
  @Override
  public Description visualization() {
    Description content = new Description();
    ProgramSchedule sourceCode = ca.getChannelAnalysisTable();

    /* create schedule output header of column time slots in order (zero-indexed), with \t as separator */
    String columnString = "Channel/Time Slot ";
    /* loop through the time slots adding the number and tab */
    for (Integer columnNumber = 0; columnNumber < sourceCode.getNumColumns(); columnNumber++) {
    	columnString += columnNumber.toString() + "\t";
    }
    /* add \n at the end */
    columnString += "\n";
    content.add(columnString);
    
    /* loop through each index of the program schedule table */
    for (Integer rowIndex = 0; rowIndex < sourceCode.getNumRows(); rowIndex++) {
    	/* create new row string staring with the channel number */
    	String rowString = rowIndex.toString();
    	for (Integer columnIndex = 0; columnIndex < sourceCode.getNumColumns(); columnIndex++) {
        	var entry = sourceCode.get(rowIndex, columnIndex);
        	/* if entry is null, append a tab and hyphen */
    		if (entry == null) {
    			rowString += "\t-";
    		/* else append a tab and the contents of the entry */
    		} else {        
    			rowString += "\t" + entry;
    		}
    		/* append new line character when last column index is reached */
    		if (columnIndex == sourceCode.getNumColumns() - 1) {
    			rowString += "\n";
    		}
    	}
    	/* add each row to content */
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

    header.add(String.format(OBJECT_NAME + " for graph %s created with the following parameters:\n",
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
