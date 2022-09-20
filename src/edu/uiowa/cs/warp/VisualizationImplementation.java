/**
 * 
 */
package edu.uiowa.cs.warp;

import java.io.File;

/**
 * <h1>Implementation of the Visualization Interface.</h1>
 * <p>The VisualizationImplementation class allows the user to request visualizations of various aspects of the given file content in Description object.
 * Methods to print Workload Descriptions to the console, write to a file, and create string are defined.
 * Visualizations are created based on system choices of SOURCE, RELIABILITIES, SIMULATOR_INPUT, LATENCY, LATENCY_REPORT, 
 * DEADLINE_REPORT, and CHANNEL. Workload choices include INPUT_GRAPH, COMUNICATION_GRAPH, GRAPHVIZ.</p>
 * 
 * @author sgoddard
 * @version 1.4
 *
 */
public class VisualizationImplementation implements Visualization {
  
  /**
   * Description object created from visualization() method of VisualizationObject class.
   */
  private Description visualization;
  
  /**
   * Description object of formatted input file content created from fileVisualization() method of VisualizationObject class.
   */
  private Description fileContent;
  
  /**
   * Description object created from displayVisualization() method of VisualizationObject class.
   */
  private Description displayContent;
  
  /**
   * Name of file being written to in toFile() method.
   */
  private String fileName;
  
  /**
   * Name of file from WorkLoad object.
   */
  private String inputFileName;
  
  /**
   * Created using full output path and input filename.
   */
  private String fileNameTemplate;
  
  /**
   * FileManager object used to get base directory and create new directory for file writing.
   */
  private FileManager fm = null;
  
  /**
   * WarpInterface object containing the necessary methods to create the different visualizations.
   */
  private WarpInterface warp = null;
  
  /**
   * Workload object passed into the constructor to have visualization created.
   */
  private WorkLoad workLoad = null;


  /**
   * The first constructor method sets up the inputFileName and fileNameTemplate based on the WarpInterface
   * object passed in. It then calls createVisualization using the given choice enumeration value passed in.
   * 
   * @author sgoddard
   * @param warp The interface to be visualized.
   * @param outputDirectory The file to be written to.
   * @param choice The system visualization choice.
   */
  public VisualizationImplementation(WarpInterface warp, String outputDirectory,
      SystemChoices choice) {
    this.fm = new FileManager();
    this.warp = warp;
    inputFileName = warp.toWorkload().getInputFileName();
    this.fileNameTemplate = createFileNameTemplate(outputDirectory);
    createVisualization(choice);
  }
  
  /**
   * The second constructor method sets up the inputFileName and fileNameTemplate based on the Workload
   * object passed in. It then calls createVisualization using the given choice enumeration value passed in.
   * 
   * @author sgoddard
   * @param workLoad The work load to be visualized.
   * @param outputDirectory The file to be written to.
   * @param choice The work load visualization choice.
   */
  public VisualizationImplementation(WorkLoad workLoad, String outputDirectory,
      WorkLoadChoices choice) {
    this.fm = new FileManager();
    this.workLoad = workLoad;
    inputFileName = workLoad.getInputFileName();
    this.fileNameTemplate = createFileNameTemplate(outputDirectory);
    createVisualization(choice);
  }

  /**
   * toDisplay simply prints the displayContent field.
   * 
   * @author sgoddard
   */
  @Override
  public void toDisplay() {
    System.out.println(displayContent.toString());
  }

  /**
   * toFile uses the global variables fileName and fileContent to write to a new file.
   * 
   * @author sgoddard
   */
  @Override
  public void toFile() {
    fm.writeFile(fileName, fileContent.toString());
  }

  /**
   * toString returns a String version of the visualization object.
   * 
   * @author sgoddard
   * @return visualization String
   */
  @Override
  public String toString() {
    return visualization.toString();
  }

  /**
   * Given the SystemChoices enum value passed in, creates the requested visualization.
   * 
   * @author sgoddard
   * @param choice The system visualization choice.
   */
  private void createVisualization(SystemChoices choice) {
    switch (choice) { // select the requested visualization
      case SOURCE:
        createVisualization(new ProgramVisualization(warp));
        break;

      case RELIABILITIES:
        // TODO Implement Reliability Analysis Visualization
        createVisualization(new ReliabilityVisualization(warp));
        break;

      case SIMULATOR_INPUT:
        // TODO Implement Simulator Input Visualization
        createVisualization(new NotImplentedVisualization("SimInputNotImplemented"));
        break;

      case LATENCY:
        // TODO Implement Latency Analysis Visualization
        createVisualization(new LatencyVisualization(warp));
        break;

      case CHANNEL:
        // TODO Implement Channel Analysis Visualization
        createVisualization(new ChannelVisualization(warp));
        break;

      case LATENCY_REPORT:
        createVisualization(new ReportVisualization(fm, warp,
            new LatencyAnalysis(warp).latencyReport(), "Latency"));
        break;

      case DEADLINE_REPORT:
        createVisualization(
            new ReportVisualization(fm, warp, warp.toProgram().deadlineMisses(), "DeadlineMisses"));
        break;

      default:
        createVisualization(new NotImplentedVisualization("UnexpectedChoice"));
        break;
    }
  }

  /**
   * Given the WorkLoadChoices enum value passed in, creates the requested visualization.
   * 
   * @author sgoddard
   * @param choice The work load visualization selected.
   */
  private void createVisualization(WorkLoadChoices choice) {
    switch (choice) { // select the requested visualization
      case COMUNICATION_GRAPH:
        // createWarpVisualization();
        createVisualization(new CommunicationGraph(fm, workLoad));
        break;

      case GRAPHVIZ:
        createVisualization(new GraphViz(fm, workLoad.toString()));
        break;

      case INPUT_GRAPH:
        createVisualization(workLoad);
        break;

      default:
        createVisualization(new NotImplentedVisualization("UnexpectedChoice"));
        break;
    }
  }

  /**
   * This method is used when a generic VisalizationObject is passed in instead of one of the enum values of the Visualization interface.
   * It sets up the appropriate visualization, fileContent, displayContent, and fileName fields.
   * 
   * @author sgoddard
   * @param <T> The generic tag extending VisualizationObject.
   * @param obj The generic object extending VisualizationObject.
   */
  private <T extends VisualizationObject> void createVisualization(T obj) {
    visualization = obj.visualization();
    fileContent = obj.fileVisualization();
    /* display is file content printed to console */
    displayContent = obj.displayVisualization();
    fileName = obj.createFile(fileNameTemplate); // in output directory
  }

  /**
   * Creates the fileNameTemplate using the full output path and input filename.
   * 
   * @author sgoddard
   * @param outputDirectory The output used to create a new directory.
   * @return fileNameTemplate, a String 
   */
  private String createFileNameTemplate(String outputDirectory) {
    String fileNameTemplate;
    var workingDirectory = fm.getBaseDirectory();
    var newDirectory = fm.createDirectory(workingDirectory, outputDirectory);
    // Now create the fileNameTemplate using full output path and input filename
    if (inputFileName.contains("/")) {
      var index = inputFileName.lastIndexOf("/") + 1;
      fileNameTemplate = newDirectory + File.separator + inputFileName.substring(index);
    } else {
      fileNameTemplate = newDirectory + File.separator + inputFileName;
    }
    return fileNameTemplate;
  }

}
