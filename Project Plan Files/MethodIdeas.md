#### WARP Project Method Ideas
This file contains method ideas needed in the ChannelVisualization, ChannelAnalysis, and potential other classes necessary to fulfill the requirements outlined in the *Project Description*. 

**ChannelAnalysis.java**:<br>
*SPRINT 3 UPDATE:*<br>
The primary method that needs to implemented within ChannelAnalysis is the buildChannelAnalysisTable() method. This method parses the programSchedule object, programTable, to create a channel analysis table which is also a programScheudle object. We need to visually compare equivalent \*.dsl and \*.ch output files (uploaded on ICON) to create the code within the body of this method to create our channel analysis table (which is essentially a 2D ArrayList). The completed channel analysis table is then retrieved by the visualization() method within ChannelVisualiztion (using ca.getChannelAnalysisTable).<br>

Implementation idea: Loop through programTable using the .get(row, column) method and instantiate Strings for each instruction. Then create a new WarpDSL object. Create an instruction parameters array using dsl.getInstructionParameters(instruction) for each entry of programTable. For each instruction, use .getName() for the push or pull instruction, .getFlow() for the flow name, and .getSrc() and .getSnk() for the source and sink nodes. Using each of these parameters, we can use the .format method to create the String entries of the channel analysis table (i.e. "[A]::F0:(A:B)"). This should be fairly straightforward. We will need an if statement for checking if multiple instructions are in the same time slot for the same coordinator (separate with a comma in the output). We will also need to check if more than one coordinator is allocated the same channel in the same time slot. This is a channel conflict and needs to add a semicolon in the output String and also needs to set the conflictExists flag to true. Each of these Strings will be converted to an InstructionTimeSlot object and added to the ProgramSchedule object, "channelAnalysisTable."

**ChannelVisualization.java (COMPLETE AS OF SPRINT 2)**:<br>
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

