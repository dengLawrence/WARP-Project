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
  
// Method stub for the visualization method that parses the ProgramSchedule object from
// getChannelAnalysisTable() to add the necessary strings to the Description variable, content,
// to create the output specified in the project pdf file. 
//  @Override
//  public Description visualization() {
//    Description content = new Description();
//    content.add("Test2\n");
//    
//    ca.getChannelAnalysisTable();
//  
//    return content;
//  }

  
// Method stub that creates the header strings and adds them to the Description object, header.
//  @Override
//  public Description createHeader() {
//    Description header = new Description();
//    header.add("Test1\n");
//    
//    return header;
//  }
  
}
