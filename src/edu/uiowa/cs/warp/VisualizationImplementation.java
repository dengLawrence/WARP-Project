/**
 * 
 */
package edu.uiowa.cs.warp;

import java.io.File;

/**
 * <h1>Implementation of the Visualization Interface</h1>
 * The VisualizationImplementation class allows the user to request visualizations of various aspects of the given file content in Description object.
 * Methods to print Workload Descriptions to the console, write to a file, and create string are defined.
 * Visualizations are created based on system choices of SOURCE, RELIABILITIES, SIMULATOR_INPUT, LATENCY, LATENCY_REPORT, 
 * DEADLINE_REPORT, and CHANNEL. Workload choices include INPUT_GRAPH, COMUNICATION_GRAPH, GRAPHVIZ. 
 * <p>
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
   * 
   */
  private FileManager fm = null;
  
  /**
   * 
   */
  private WarpInterface warp = null;
  
  /**
   * 
   */
  private WorkLoad workLoad = null;


  public VisualizationImplementation(WarpInterface warp, String outputDirectory,
      SystemChoices choice) {
    this.fm = new FileManager();
    this.warp = warp;
    inputFileName = warp.toWorkload().getInputFileName();
    this.fileNameTemplate = createFileNameTemplate(outputDirectory);
    createVisualization(choice);
  }

  public VisualizationImplementation(WorkLoad workLoad, String outputDirectory,
      WorkLoadChoices choice) {
    this.fm = new FileManager();
    this.workLoad = workLoad;
    inputFileName = workLoad.getInputFileName();
    this.fileNameTemplate = createFileNameTemplate(outputDirectory);
    createVisualization(choice);
  }

  @Override
  public void toDisplay() {
    System.out.println(displayContent.toString());
  }

  @Override
  public void toFile() {
    fm.writeFile(fileName, fileContent.toString());
  }

  @Override
  public String toString() {
    return visualization.toString();
  }

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

  private <T extends VisualizationObject> void createVisualization(T obj) {
    visualization = obj.visualization();
    fileContent = obj.fileVisualization();
    /* display is file content printed to console */
    displayContent = obj.displayVisualization();
    fileName = obj.createFile(fileNameTemplate); // in output directory
  }

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
