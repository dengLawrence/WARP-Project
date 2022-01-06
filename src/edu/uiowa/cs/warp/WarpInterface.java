/**
 * 
 */
package edu.uiowa.cs.warp;

/**
 * @author sgoddard
 * @version 1.3
 */
public interface WarpInterface extends SystemAttributes {

  public WorkLoad toWorkload();

  public Program toProgram();

  public ReliabilityAnalysis toReliabilityAnalysis();

  public SimulatorInput toSimulator();

  public LatencyAnalysis toLatencyAnalysis();

  public void toSensorNetwork(); // deploys code

  public Boolean reliabilitiesMet();

  public Boolean deadlinesMet();

}
