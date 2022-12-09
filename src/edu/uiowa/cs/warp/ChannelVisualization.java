package edu.uiowa.cs.warp;

/**
 * <h1>Implementation of Channel Visualization.</h1>
 * <p>The ChannelVisualization class allows a Warp Interface to be visualized when the -ca flag is
 * requested at runtime. A Description object of the visualization is created from the retrieved channel
 * analysis table. A Description object of the header for file output can also be created.</p>
 * 
 * CS2820 Fall 2022 Project: Implement this class to create the file visualization that is requested in Warp.
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
   * Creates a Description object of the channel analysis visualization. This method is called by
   * VisualizationObject.java to retrieve this Description for display and file visualization.
   * This method parses the channel analysis table created in the constructor. Row and column labels are
   * created and the entries of the channel analysis table are added.
   * 
   * @author eborchard
   * @return Description containing visualization of the channel analysis.
   */
  @Override
  public Description visualization() {
    Description visualization = new Description();
    ProgramSchedule caTable = ca.getChannelAnalysisTable();

    /* The first row in the visualization starts with the axis labels... */
    String columnString = "Channel/Time Slot ";
    /* ...which is followed by the time slot numbers (zero-indexed) as the column headers. */
    for (Integer columnNumber = 0; columnNumber < caTable.getNumColumns(); columnNumber++) {
    	columnString += columnNumber.toString() + "\t";
    }
    /* Start a new line for the body of the visualization table. */
    columnString += "\n";
    visualization.add(columnString);
    
    /* Loop through the rows and columns of the caTable to retrieve each individual table entry. */
    for (Integer rowIndex = 0; rowIndex < caTable.getNumRows(); rowIndex++) {
    	String rowString = rowIndex.toString();
    	for (Integer columnIndex = 0; columnIndex < caTable.getNumColumns(); columnIndex++) {
        	var entry = caTable.get(rowIndex, columnIndex);
        	/* If the caTable entry is null, that channel/time slot is not being used. Append a tab and hyphen. */
    		if (entry == null) {
    			rowString += "\t-";
    		/* Else the channel/time slot is being used. Append a tab and the contents of the caTable entry. */
    		} else {        
    			rowString += "\t" + entry;
    		}
    		/* Append a new line character when the last time slot is reached. */
    		if (columnIndex == caTable.getNumColumns() - 1) {
    			rowString += "\n";
    		}
    	}
    	/* Add each newly created row string to the Description, visualization. */
    	visualization.add(rowString);
      }
    return visualization;
  }

  
  /**
   * Creates a Description object for the header of the channel visualization file output.
   * 
   * @author sgoddard (modified: eborchard)
   * @return Description containing visualization of the header.
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
