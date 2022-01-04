/**
 * 
 */
package edu.uiowa.cs.warp;

/**
 * @author sgoddard
 *
 */
public interface WarpInterface extends SystemAttributes {
	
	public WorkLoad toWorkload();
	public Program toProgram();
	public ReliabilityAnalysis toReliabilityAnalysis();
	public SimulatorInput toSimulator();
	public void toSensorNetwork(); // deploys code
	public Boolean reliabilitiesMet();
	public Boolean deadlinesMet();
}
