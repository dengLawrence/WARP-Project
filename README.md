# CS2820 Fall 2022 WARP Project Code
This code base will be used for the University of Iowa CS 2820 Introduction to Software
Development course. The code was developed by Steve Goddard for the WARP sensor network 
research project. It was first written in Swift and rewritten in Java. It was then 
rewritten again in an object-oriented programming style. It was a quick
hack, and it needs a lot of cleanup and refactoring. A perfect code base to teach
the value of software developement fundamentals!
<br>

NOTE: To add run parameters, go to Run->Run Configurations. Then go to the Arguments tab and
add flags within the Program Arguments box. Apply and Run


o-------------------------------------------------------------------o<br>
F22-HW2 (JavaDoc) Partners' Tasks Completed:<br>
o-------------------------------------------------------------------o<br>
** Ethan Borchard: ** <br>
- &#35;6 -> Developed JavaDoc comments for the missing summary comment and commented all 
	attributes and all methods in VisualizationImplementation.java.
- &#35;7 -> Developed JavaDoc comments for the specified methods in WorkLoad.java.
- &#35;8 -> Develop JavaDoc comments for the following methods in Program.java.<br>

** Bryce Biwer: ** <br>
- &#35;2 -> Developed JavaDoc comments for the missing summary and methods in Warp.java.
	As well as converted comment lines for global variables to JavaDoc.
- &#35;3 -> Developed JavaDoc comments for the missing summary and methods in Channels.java.
- &#35;4 -> Developed JavaDoc comments for the missing summary and methods in ChannelAnalysis.java.
- &#35;5 -> Developed JavaDoc comments for the missing summary and methods in ProgramVisualization.java.

o-------------------------------------------------------------------o<br>
F22-HW3 (JUnit Tests) Partners' Tasks Completed:<br>
o-------------------------------------------------------------------o<br>
** Ethan Borchard: ** <br>
Developed and executed JUnit tests for the following methods in WorkLoad.java:
- setFlowsInDMorder()
- getFlowNamesInPriorityOrder()
- getNodeChannel()
- getNodesInFlow()
- setFlowNamesInOriginalOrder()
- setFlowsInPriorityOrder()
- getMinPeriod()
- maxFlowLength()
- getFlowDeadline()
- getNodeNamesOrderedAlphabetically() <br>

** Bryce Biwer: ** <br>
Developed and executed JUnit tests for the following methods in Channels.java:
- getChannelSet()
- addNewChannelSet()
- isEmpty()
- removeChannel()
- addChannel()
- getNumChannels()

Modified Channels.java
- Added checkIfTimeslotIsOutOfBounds method to check if a valid timeSlot is given
- Added checkIfTimeslotIsOutOfBounds to the following methods:<br>
	- getChannelSet,    isEmpty,    removeChannel,    addChannel <br>
	
o-------------------------------------------------------------------o<br>
F22-HW4 (UML Diagrams) Updates Made:<br>
o-------------------------------------------------------------------o<br>
- Created a UML diagram for Program that only shows its children and public elements.
- Created a UML diagram for WorkLoad that shows associated classes and parent classes.
- Created a UML diagram that consists of all of the Channel*.java classes, with the file name Channel.uml.
- Added a public method called getChannelAnalysisTable to ChannelAnalysis.java.

o-------------------------------------------------------------------o<br>
F22-HW5 (Refactoring) Partners' Tasks Completed:<br>
o-------------------------------------------------------------------o<br>
** Ethan Borchard: ** <br>
Refactored the implementation of findNextAvailableChannel() in the Program class.
- Created JavaDoc for each method
- Updated Program Class UML

** Alex Lenaers: ** <br>
Main refactoring of the implementation of findNextAvailableChannel() in the Program class.
- Cleaned up comments
- Created helper methods
- Changed to static variable types
- Changed variable names to be more descriptive
<br> <br> <br>

### F22-Project:
**Team Members**:
- Alex Lenaers
- David Lin
- Ethan Borchard
- Lawrence Deng
<br>

**Project Description:**
The ultimate aim of this project is to complete the ChannelAnalysis and ChannelVisualisation classes so that they create *.ch files and evaluate
the channel allocation of WARP flows, as requested in the Warp main program when the runtime configuration option -ca is used.

-----------------------------**Sprint 1**-----------------------------<br>
<br>
**Tasks to Be Done:**
Sprint 1:
- README.md Documentation: Lawrence
- UML Sequence Diagram: David
- Design methods for ChannelVisualization: Ethan
- UML Class Diagrams: Ethan
- Design test cases for ChannelVisualization:
Later Sprints:
- ChannelVisualization Methods Implementation: TBD
- ChannelVisualization JavaDoc: TBD
- ChannelVisualization JUnit Tests: TBD
- ChannelAnalysis Methods Implementation: TBD
- ChannelAnalysis JavaDoc: TBD
- ChannelAnalysis JUnit Tests: TBD
<br>

**Project Artifacts:**
- UML Sequence Diagram
- UML Class Diagram for ChannelVisualization (update as methods created)
- UML Class Diagram for ChannelAnalysis (update as methods created)
- Method Design Documents
<br>

**Record/Timeline:**
- 10/26/2022 : First team meeting in lab.
- 10/31/2022 : Team meeting to discuss sequence diagram and task designation for Sprint 1 delivery.
- 11/2/2022 : Lab meeting - Began discussing methods needed to meet project requirements and completed sketch of sequence diagram. 


