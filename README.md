# CS2820 Fall 2022 WARP Project Code
****FALL 2022 GROUP PROJECT DOCUMENTATION TOWARDS BOTTOM**** <br>
<br>
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

**Project Description:**<br>

The ultimate aim of this project is to complete the ChannelAnalysis and ChannelVisualization classes so that they 
create *.ch files and evaluate the channel allocation of WARP flows, as requested in the Warp main program when the
runtime configuration option -ca is used. To do this, methods must be implemented within the ChannelAnalysis class
to correctly analyze and return the state of each channel, and methods must be implemented within the ChannelVisualization class to correctly visualize and display the information found in ChannelAnalysis.
<br>

**Project Schedule:**<br>

Sprint 1 (Due Nov. 8th): Create high level plan and design documents in addition to a UML Sequence diagram. <br>
Sprint 2 (Due Nov. 18th): ChannelVisualization implementation, documentation, and testing. <br>
Sprint 3 (Due Dec. 9th): ChannelAnalysis implementation, documentation, and testing. System documentation and testing. <br>

-----------------------------**Sprint 1**-----------------------------<br>
<br>
**Tasks to Be Done:**<br>

*Sprint 1*: <br>
- README.md Layout and Documentation: Lawrence
- Finalized UML Sequence Diagram: David
- Create Method Design document: Ethan
- ChannelVisualization UML Class Diagram: Ethan
<br><br>

*Sprint 2*: <br>
- Design test cases for ChannelVisualization: Alex
- ChannelVisualization Methods Implementation: Ethan
- ChannelVisualization JavaDoc: Ethan
- ChannelVisualization JUnit Tests: Lawrence, David, Alex
- Sprint 2 README.md updates: Everyone
<br>

*Sprint3*: <br>
- ChannelAnalysis Methods Implementation: TBA
- ChannelAnalysis JavaDoc: TBA
- ChannelAnalysis JUnit Tests: TBA
- Sprint 3 README.md updates: TBA
<br>

**Other Project Artifacts:** <br>
See *Project Plan Files* Folder:
- UML Sequence Diagram (*WSD.txt* and *WSD.png*)
- UML Class Diagram for ChannelVisualization (*ChannelVisualization.umlcd*)
- Method Design File (*MethodIdeas.md*)
<br>

-----------------------------**Sprint 2**-----------------------------<br>
<br>
**Tasks to Be Done:**<br>

*Sprint 2*: <br>
- Design test cases for ChannelVisualization: Alex
- ChannelVisualization Methods Implementation: Ethan
- ChannelVisualization JavaDoc: Ethan
- ChannelVisualization JUnit Tests: Lawrence, David, Alex
- Sprint 2 README.md updates: Everyone
<br>

*Sprint3*: <br>
- Design test cases for ChannelAnalysis: Lawrence, David, Alex
- ChannelAnalysis Methods Implementation: Ethan, Lawrence
- ChannelAnalysis JavaDoc: Ethan
- ChannelAnalysis JUnit Tests: Lawrence, David, Alex
- Sprint 3 README.md updates: Everyone
<br>

**Other Project Artifacts:** <br>
See *Project Plan Files* Folder:
- UML Sequence Diagram (*WSD.txt* and *WSD.png*)
- UML Class Diagram for ChannelVisualization (*ChannelVisualization.umlcd*)
- UML Class Diagram for ChannelAnalysis (*ChannelAnalysis.umlcd*)
- Method Design File (*MethodIdeas.md*)
<br>

-----------------------------**Sprint 3**-----------------------------<br>
<br>
**Tasks to Be Done:**<br>

*Sprint3*: <br>
- Design test cases for ChannelAnalysis: Ethan, Lawrence, David, Alex
- ChannelAnalysis Methods Implementation: Ethan, Lawrence
- ChannelAnalysis JavaDoc: Ethan, Lawrence
- ChannelAnalysis JUnit Tests: Lawrence, David, Alex
- UML Sequence Diagram Updates: Lawrence, David
- UML Class Diagram Updates: Ethan
- Sprint 3 README.md updates: Everyone
<br><br>

**Other Project Artifacts:** <br>
See *Project Plan Files* Folder:
- UML Sequence Diagram (*WSD.txt* and *WSD.png*)
- UML Class Diagram for ChannelVisualization (*ChannelVisualization.umlcd*)
- UML Class Diagram for ChannelAnalysis (*ChannelAnalysis.umlcd*)
- Method Design File (*MethodIdeas.md*)
<br>

**Design Decisions and Program Output:** <br>
The major design decision for this project was how to implement ChannelAnalysis.java. We decided to have a method called "buildChannelAnalysisTable" that is called from the class constructor. This method sets up the rows and columns of the channel analysis table based on the programSchedule and Program object of the input file. It then loops through each entry of the programTable keeping track of the current and previous instructions. This was necessary for entries that contained both a 'push' and 'pull' instruction since they are within the same InstructionParameters array. Once a 'push' instruction is encountered, the helper method, "setTableEntry," is called. This method parses the current instruction to produce the string output requested in the program specification. If the next instruction is 'pull' we need to append the desired string output to that created from the 'push' instruction. If the channel & time slot that we are trying to put a new entry into is not null, then a channel conflict has a occurred and we append the two entries separated by a semicolon. In this case, the conflictExists boolean variable is set to true. Warp.java runs a method called verifyNoChannelConflicts that calls isChannelConflict(). Our implementation now outputs the correct message about channel conflicts existing when Warp is run. <br><br>
Given this, we decided to have just one helper method for the buildChannelAnalysisTable method. It made more sense for our implementation to have this method get to the point of finding a 'push' instruction and then calling just one helper method to parse the current and next instructions, and determine if a channel conflict exists. The class would only get more complicated to read with further helper methods. <br><br>
*Note on program output:* Our program produces the "BugExists" output files found on ICON. Channels and Time Slots are allocated differently in the output than if this bug was fixed which leads to channel conflicts (see TestBugPriority-1Faults.ch). This is a bug within the underlying Program.java logic and not due to the code written in ChannelAnalysis.java or ChannelVisualization.java. <br>
Also note: The .ch output file for TestBug found on ICON contains a small error where two semicolons are used within the same Channel/Time Slot (2/1). The second semicolon in this string should be a comma because the instruction it is parsing is a 'push' instruction conflicting with a 'push-pull' instruction. Our output corrects this error. <br>
Our visualization() table also lacks a tab at the end of each row compared to the ICON .ch files, due to the way we implemented tabs in visualization().


**Record/Timeline:**
- *Sprint 1*
- 10/26/2022 : First team meeting in lab.
- 10/31/2022 : Team meeting to discuss sequence diagram and task designation for Sprint 1 delivery.
- 11/2/2022 : Lab meeting - Began discussing methods needed to meet project requirements and completed sketch of sequence diagram.
- 11/4/2022 : Warp Sequence Diagram is uploaded. -David
- 11/6/2022 : ChannelVisualization UML class diagram and test classes for ChannelVisualization & ChannelAnalysis classes created. -Ethan
- 11/7/2022 : ChannelVisualization placeholder methods were created and the method design file (*MethodIdeas.md*) was created. -Ethan and Lawrence
- 
- *Sprint 2*
- 11/9/2022 : Lab meeting - delegated roles for Sprint 2.
- 11/12/2022 : Started ChannelVisualizationTest.java -Lawrence
- 11/13/2022 : Implemented most of ChannelVisualiztion.java and created a dummy method within ChannelAnalysis.java. -Ethan
- 11/14/2022 : Meeting to discuss ChannelVisualiztion.java implementation and testing. -Ethan, Lawrence, David
- 11/14/2022 : Added initial 2 tests to ChannelVisualizationTest.java. -David, Lawrence
- 11/15/2022 : Updated implementation of ChannelVisualization.java to work for tests. -Ethan
- 11/16/2022 : Finalized ChannelVisualization.java with JavaDoc comments. -Ethan
- 11/16/2022 : Lab meeting - delegated roles and planned for Sprint 3.
- 11/16/2022 : Updated UML Sequence Diagram and ChannelVisualizationTest.java. -David
- 11/17/2022 : Completed JUnit tests for ChannelVisualization.java (empty tables only). -Lawrence, David
- 11/18/2022 : Created ChannelAnalysis UML class diagram and updated MethodIdeas.md. -Ethan
- 11/18/2022 : Added additional JUnit tests. -Lawrence
- 11/18/2022 : Added 2 JUnit tests and timeouts. - David
- 
- *Sprint 3*
- 11/18/2022 : Added rudimentary implementation of buildChannelAnalysisTable. Does not handle channel conflicts and will need to be refactored. -Ethan
- 11/28/2022 : Team meeting & completed basic implementation of buildChannelAnalysisTable (w/ conflicts). Needs clean-up. -Ethan, David, Alex, Lawrence
- 12/4/2022 : Updated documentation in ChannelAnalysis.java. -Lawrence
- 12/5/2022 : Updated documentation in ChannelVisualization.java. -Ethan
- 12/6/2022 : Team meeting to discuss final project tasks and began ChannelAnalysis tests. -Ethan, Lawrence, David
- 12/6/2022 : Created JUnit tests for ExampleX.txt and TestBug.txt. -David
- 12/7/2022 : Polished comments further, added "Design Decisions and Program Output" to the README, and updated class diagrams. -Ethan
- 12/7/2022 : Cleaned up formatting and comments for ChannelAnalysis and added on to its tests. -Lawrence
- 12/7/2022 : Added a basic channel conflict test and began setTableEntry tests. -Ethan
- 12/7/2022 : Team meeting for ChannelAnalysis test ideas. -Ethan, Lawrence, David
- 12/7/2022 : Added tests for setTableEntry. -Ethan
- 12/7/2022 : Finalized the Warp Sequence Diagram. Updated and formatted ChannelVisualiation Tests. -David
- 12/7/2022 : Organized and fixed comments for test classes. -Ethan
- 12/7/2022 : Cleaned up and added tests for both test classes. -Lawrence
- 12/8/2022 : Added StressTest4 & multiple channel conflicts tests for buildChannelAnalysisTable. -Ethan
- 12/8/2022 : Cleaned up documentation and tests in both test classes. -Lawrence
- 12/9/2022 : Modified Sequence Diagram. -Lawrence
- 12/9/2022 : Final comments clean-up, updates to class diagrams and generation of JavaDoc. -Ethan