#### WARP Project Method Ideas
This file contains method ideas needed in the ChannelVisualization, ChannelAnalysis, and potential other classes necessary to fulfill the requirements outlined in the *Project Description*. 

**ChannelVisualization.java**:<br>
Right now, we are modeling the methods needed to create the channel visualization after the code in *ProgramVisualization.java*.

First, the following two lines were added in the constructor to get the program schedule:<br>
this.program = warp.toProgram();<br>
this.sourceCode = program.getSchedule();

We will then need to parse the schedule (sourceCode variable) to create the desired format of the \*.ch files. We need to implement the logic needed to do this in the visualization() method which overrides that in the *VisualizationObject.java* class:<br>
<br>
@Override<br>
  public Description visualization() {<br>
    Description content = new Description();<br>
    //code here<br>
    return content;<br>
  }<br>
<br>
 
We also need to override and implement the createHeader() method from the *VisualizationObject.java* class so that the fileVisualization() method in *VisualizationObject.java* formats the file correctly:<br>
<br>
  @Override<br>
  public Description createHeader() {<br>
    Description header = new Description();<br>
    //code here<br>
    return header;<br>
  }<br>
<br>

displayVisualization() and createFile(fileNameTemplate) are already properly implemented in the *VisualizationObject.java* class (we may need to call them from *ChannelVisualization.java*).<br>

**ChannelAnalysis.java**:<br>
We still need to figure out how the ChannelAnaylsis class plays into this and the methods needed.

