/**
 * 
 */
package edu.uiowa.cs.warp;

import java.util.ArrayList;

/**
 * <h1>Implementation of Program Visualization.</h1>
 * <p>The ProgramVisualization class allows a warp Interface to be converted to program and visualized.</p>
 *
 * @author sgoddard
 * @version 1.2
 */
public class ProgramVisualization extends VisualizationObject {

  /**
   * Source file extension format
   */
  private static final String SOURCE_SUFFIX = ".dsl";

  /**
   * Reference to ProgramSchedule, set in constructor
   */
  private ProgramSchedule sourceCode;

  /**
   * Reference to the program, set in constructor
   */
  private Program program;

  /**
   * Deadlines met flag, set in constructor
   */
  private Boolean deadlinesMet;

  /**
   * Constructor converts WarpInterface to Program for visualization.
   * 
   * @author sgoddard
   * @param warp WarpInterface to convert to program for visualization
   */
  ProgramVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.program = warp.toProgram();
    this.sourceCode = program.getSchedule();
    this.deadlinesMet = warp.deadlinesMet();
  }

  /**
   * Creates a Description object for work flow visualization.
   * 
   * @author sgoddard
   * @return Description containing visualization of the work flow
   */
  @Override
  public Description visualization() {
    Description content = new Description();
    var orderedNodes = program.toWorkLoad().getNodeNamesOrderedAlphabetically();

    /* create schedule output header of column node names in order, with \t as separator */
    String nodeString = "Time Slot\t";
    /* loop through the node names, stopping just before last name */
    for (int i = 0; i < orderedNodes.length - 1; i++) {
      /* add each name to the string, with \t between each name */
      nodeString += String.format("%s\t", orderedNodes[i]);
    }
    /* add the last name with \n instead of \t at the end */
    nodeString += String.format("%s\n", orderedNodes[orderedNodes.length - 1]);
    content.add(nodeString);

    for (int rowIndex = 0; rowIndex < sourceCode.size(); rowIndex++) {
      ArrayList<String> rowArrayList = sourceCode.get(rowIndex);
      var row = rowArrayList.toArray(new String[rowArrayList.size()]);
      String rowString = String.format("%d\t", rowIndex) + String.join("\t", row) + "\n";
      content.add(rowString);
    }
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

    header.add(String.format("WARP system for graph %s created with the following parameters:\n",
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

  /**
   * Creates a Description object for the footer of the visualization output.
   * 
   * @author sgoddard
   * @return Description containing visualization of the footer
   */
  @Override
  public Description createFooter() {
    Description footer = new Description();
    String deadlineMsg = null;

    if (deadlinesMet) {
      deadlineMsg = "All flows meet their deadlines\n";
    } else {
      deadlineMsg = "WARNING: NOT all flows meet their deadlines. See deadline analysis report.\n";
    }
    footer.add(String.format("// %s", deadlineMsg));
    return footer;
  }

}
