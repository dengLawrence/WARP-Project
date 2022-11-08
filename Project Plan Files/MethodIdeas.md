#### WARP Project Method Ideas
This file contains method ideas needed in the ChannelVisualization, ChannelAnalysis, and potential other classes necessary to fulfill the requirements outlined in the *Project Description*. 

**ChannelAnalysis.java**:<br>
The main method that needs to implemented within ChannelAnalysis is getChannelAnalysisTable(). This method parses the programSchedule object, programTable, to create a channel analysis table which is also a programScheudle object. We need to visually compare equivalent \*.dsl and \*.ch output files (uploaded on ICON) to create the code within the body of this method that parses the "programTable" to create our channel analysis table (which is essentially a 2D ArrayList). This method can then be called by the visualization() method within ChannelVisualiztion (ca.getChannelAnalysisTable).

**ChannelVisualization.java**:<br>
We are modeling the methods needed to create the channel visualization after the code in *ProgramVisualization.java*.

*Visualization()*:<br>
This method overrides that in the *VisualizationObject.java* class. It will have a similar implementation to the visualization() method in the ProgramVisualization class. It starts with creating a Description object called content (essentially an ArrayList) and first adds the Time Slot row. Then ca.getChannelAnalysisTable will be called to get the ProgramSchedule object created in ChannelAnalysis. It will add each of the String rows to content and finally return content.
<br>
@Override<br>
  public Description visualization() {<br>
    Description content = new Description();<br>
    //code here<br>
    ca.getChannelAnalysisTable<br>
    //code here<br>
    return content;<br>
  }<br>
<br>
 
We also need to override and implement the createHeader() method from the *VisualizationObject.java* class so that the fileVisualization() method in *VisualizationObject.java* formats the file correctly. Again, this will only require minor changes from the createHeader method in the ProgramVisualization class.<br>
<br>
  @Override<br>
  public Description createHeader() {<br>
    Description header = new Description();<br>
    //code here<br>
    return header;<br>
  }<br>
<br>

We do not need to implement a footer() method.

displayVisualization() and createFile(fileNameTemplate) are already properly implemented in the *VisualizationObject.java* class (we may need to call them from *ChannelVisualization.java*).<br>

