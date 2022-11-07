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
  
  private Program program;
  
  private ProgramSchedule sourceCode;

  ChannelVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.warp = warp;
    this.program = warp.toProgram();
    this.sourceCode = program.getSchedule();
    this.ca = warp.toChannelAnalysis();
  }
  
  
  @Override
  public Description visualization() {
    Description content = new Description();
    
    content.add("Test2\n");
    for (int rowIndex = 0; rowIndex < sourceCode.size(); rowIndex++) {
        ArrayList<String> rowArrayList = sourceCode.get(rowIndex);
        var row = rowArrayList.toArray(new String[rowArrayList.size()]);
        String rowString = String.format("%d\t", rowIndex) + String.join("\t", row) + "\n";
        content.add(rowString);
      }
      return content;
  }
  
  @Override
  public Description createHeader() {
    Description header = new Description();
    
    header.add("Test1\n");
    header.add(String.format("WARP system for graph %s created with the following parameters:\n",
        program.getName()));
    
    return header;
  }
  
}
