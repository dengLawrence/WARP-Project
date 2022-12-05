package edu.uiowa.cs.warp;

import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;

/**
 * <h1>Implementation of the ChannelAnalysis class.</h1>
 * <p>Used to analyze either Program or WarpInterface by converting to program.
 * Evaluates the channel allocation of WARP flows including detection of channel conflicts.</p>
 * 
 * @author sgoddard
 * @version 1.4
 *
 */
public class ChannelAnalysis {

  /**
   * Reference to the program, set in constructor.
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected Program program;

  /**
   * Reference to ProgramSchedule within the Program class, set in constructor.
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected ProgramSchedule programTable;

  /**
   * Channel conflict exists flag
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected Boolean conflictExists;
  
  /**
   * Reference to the channel analysis ProgramSchedule, built in constructor.
   * Will be set to private once ChannelAnalysis is fully implemented.
   */
  protected ProgramSchedule channelAnalysisTable;

  /**
   * Constructor that takes in a WarpInterface and converts it to a a program, calls the other 
   * class constructor with the Program object parameter then sets the program and it's schedule.
   * 
   * @author sgoddard
   * @param warp WarpInterface to analyze
   */
  ChannelAnalysis(WarpInterface warp) {
    this(warp.toProgram());
  }

  /**
   * Constructor to set the program and it's schedule. Builds channel analysis table.
   * 
   * @author sgoddard
   * @param program The program to analyze
   */
  ChannelAnalysis(Program program) {
    this.program = program;
    this.programTable = program.getSchedule();
    conflictExists = false;
	buildChannelAnalysisTable();
  }
  
  
  /**
   * Determines whether or not there is a channel conflict.
   * 
   * @author sgoddard
   * @return boolean based on if there is a channel conflict or not
   */
  public Boolean isChannelConflict() {
    return conflictExists;
  }
  
  /**
   * buildChannelAnalysisTable() parses the program schedule (programTable) and WarpDSL to create a channel
   * analysis table. The Visualization() method within ChannelVisualization will then use this analysis table
   * to create the desired visualization.
   * 
   * Should be private.
   * 
   * @author eborchard, lldeng
   */
  public void buildChannelAnalysisTable() {
	  //Create a new channel analysis table with the correct number of rows and columns based on input file,
	  //where the number of rows is the number of channels in the program and the number of columns is the
	  //number of rows in the programTable (the number of TimeSlots in the program).
	  //The table entries are initially set to null.
	  var numColumns = programTable.getNumRows();
	  var numRows = program.getNumChannels();
	  channelAnalysisTable = new ProgramSchedule(numRows,numColumns);
	  
	  //Create a Warp instruction parser object to get the instruction parameters, which have push and pull
	  //instructions for each channel/timeSlot combination.
	  var dsl = new WarpDSL();
	  
	  //Loop through each entry of the programTable rows and columns to retrieve the instruction name string.
	  for (int i = 0; i < programTable.getNumRows(); i++) {
		  for (int j = 0; j < programTable.getNumColumns(); j++) {
			  //Retrieve individual programTable entry to parse its instructionParameters with the WarpDSL.
			  String instruction = programTable.get(i, j);
			  //Create an ArrayList of the instruction parameters of each entry to be looped through.
			  var instructionParametersArray = dsl.getInstructionParameters(instruction);
			  //Loop through these instruction parameters, looking for any that have a 'pull' instruction
			  for (int k = 0; k < instructionParametersArray.size(); k++) {
				  //Set InstructionParameters instr to the current instructionParameters object in the array.
				  InstructionParameters currInstr = instructionParametersArray.get(k);
				  //Initialize nextInstr to be null. This variable will be used to look for an additional
				  //'pull' instruction after a 'push' instruction has been found.
				  InstructionParameters nextInstr = null;
				  //If we are not at the end of the array, nextInstr can be set to the next InstructionParameter
				  //ahead of the current InstructionParameter in the array.
				  if(k < instructionParametersArray.size() - 1) {
					  nextInstr = instructionParametersArray.get(k+1);
				  }
				  //Get the name of currInstr, and if it is 'push', then we have a flow instruction we must grab
				  //for the channel analysis table, so setTableEntry() using i (index of channelAnalysisTable's column
				  //and programTable's row), currIsntr, and nextInstr(which will be needed in case of a 'pull'
				  //instruction following the 'push' instruction.
				  String name = currInstr.getName();
				  if (name.equals("push")) {
					  setTableEntry(i, currInstr, nextInstr);
				  }
			  }
		  }
	  }
   }

  /**
   * setTableEntry() creates the string that will be placed at the row(channel) and column(timeSlot)
   * of the channel analysis table associated with the pulled InstructionParameter. It gets the source,
   * sink, and flow strings of the current InstructionParameter, and formats them together appropriately.
   * If the next instructionParameter is 'pull' instruction, it appends the existing output string with
   * the flow from the 'pull' instruction. If a channel conflict occurs, where multiple coordinators have
   * instructions in the same channel and timeSlot, the method adds a semicolon between the different
   * coordinators' instructions in the channel analysis table entry and sets the conflictExists flag to true.
   * 
   * @author eborchard, lldeng
   * @param timeSlot The timeSlot of the instruction, which is also the column number of the channel analysis table.
   * @param currInstr The current InstructionParameter, from looping through the InstructionParametersArray.
   * @param nextInstr The InstructionParameter after the current one, which may be a 'pull' instruction.
   */
  public void setTableEntry(int timeSlot, InstructionParameters currInstr, InstructionParameters nextInstr) {
	  //Get the source, sink, and flow of currInstr and format them into a string output for the table entry.
	  String pushSrc = currInstr.getSrc();
	  String pushSnk = currInstr.getSnk();
	  String pushFlow = currInstr.getFlow();
	  String output = String.format("[%s]::%s:(%s:%s)", pushSrc, pushFlow, pushSrc, pushSnk);
	  //If nextInstr is a 'pull' instruction, it needs to be appended to output with a comma.
	  if (nextInstr != null && nextInstr.getName().equals("pull")) {
		  String pullSrc = nextInstr.getSrc();
		  String pullSnk = nextInstr.getSnk();
		  String pullFlow = nextInstr.getFlow();
		  output += String.format(", %s:(%s:%s)", pullFlow, pullSrc, pullSnk);
	  }
	  //Get the channel of the currInstr, and get the channel analysis table entry of that channel
	  //and the current timeSlot. If the entry is not null, i.e. there is a flow instruction already
	  //at the channel/timeSlot position, then a channel conflict exists, in which case we must
	  //separate the existing table entry string and the newly created one with a semicolon, and mark
	  //the conflictExists flag as true.
	  int channel = Integer.parseInt(currInstr.getChannel());
	  String currentTableEntry = channelAnalysisTable.get(channel, timeSlot);
	  if(currentTableEntry != null) {
		  String tableString = currentTableEntry;
		  tableString += String.format("; %s", output);
		  output = tableString;
		  conflictExists = true;
	  }
	  //set the channelAnalysisTable entry at (channel, timeSlot) to the created output string.
	  channelAnalysisTable.set(channel, timeSlot, output);
  }
  
  /**
   * Method that retrieves the channel analysis table created from the buildChannelAnalysisTable() method.
   * 
   * @author eborchard
   * @return ProgramSchedule The completed channel analysis table.
   */
  public ProgramSchedule getChannelAnalysisTable() {
	  return channelAnalysisTable;
   }
}
