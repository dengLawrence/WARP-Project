package edu.uiowa.cs.warp;

/**
 * @author sgoddard2
 *
 */
public class LatencyAnalysis {

  private Description latencyReport;

  LatencyAnalysis(WarpInterface warp) {
    this.latencyReport = new Description();
  }

  public Description latencyReport() {
    return latencyReport;
  }
}
