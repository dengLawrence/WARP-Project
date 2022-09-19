package edu.uiowa.cs.warp;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * <h1>Implementation of the Channel class</h1>
 *  The Channel class allows the user to handle the amount of wireless channels that are available for
 *  scheduling using the private sub class ChannelSet and methods that can add or remove channel sets or
 *  channels individually<p>
 * @author sgoddard
 * @version 1.4
 *
 */
public class Channels {

  /**
   * Private class that extends a string HashSet to keep track of channels as a set
   */
  private class ChannelSet extends HashSet<String> {
    private static final long serialVersionUID = 6725256944325470867L;

    /**
     * Default Constructor
     */
    private ChannelSet() {
      super();
    }

    /**
     * Constructor that takes in a channel amount on creation
     * @param nChannels number of channels to add
     */
    private ChannelSet(Integer nChannels) {
      super();
      for (int i = 0; i < nChannels; i++) { // ASSUMES channels range from 0 to nChannels-1
        this.add(String.valueOf(i));
      }
    }
  }


  /**
   * Size of the full set of channels
   */
  Integer nChannels;

  /**
   * Verbose flag
   */
  Boolean verbose;

  /**
   * ArrayList to hold channels available in each time slot
   */
  ArrayList<ChannelSet> channelsAvailable;

  /**
   * Constructor that takes in number of channels and verbose flag
   * @param nChannels amount of channels
   * @param verbose if true, prints data to the screen
   */
  Channels(Integer nChannels, Boolean verbose) {
    this.nChannels = nChannels;
    this.verbose = verbose;
    this.channelsAvailable = new ArrayList<ChannelSet>();
  }

  /**
   * Gets the channel set for selected timeSlot
   * @param timeSlot time slot to retrieve channel set for
   * @return HashSet of channels based on timeslot
   */
  public HashSet<String> getChannelSet(Integer timeSlot) {
    /* get the channel set for this timeSlot */
    HashSet<String> channelSet = new HashSet<String>(channelsAvailable.get(timeSlot));
    return channelSet;
  }

  /**
   * Adds a new channel set to available channels
   */
  public void addNewChannelSet() {
    var channels = new ChannelSet(nChannels);
    channelsAvailable.add(channels);
  }

  /**
   * Checks if the channelSet is empty with the given timeSlot
   * @param timeSlot time slot to check channelSet
   * @return boolean value based on if the channelSet at the given timeSlot is empty
   */
  public Boolean isEmpty(int timeSlot) {
    ChannelSet channelSet = channelsAvailable.get(timeSlot); // get the channel set for this
                                                             // timeSlot
    return channelSet.isEmpty(); // returns true channel set is empty and false if not
  }

  /**
   * Removes channel from available channels at the given timeSlot
   * @param timeSlot timeSlot to get channelSet
   * @param channel Channel to remove
   * @return boolean value based on the success of the channel removal at the given timeSlot
   */
  public Boolean removeChannel(int timeSlot, String channel) {
    Boolean result;
    ChannelSet channels = channelsAvailable.get(timeSlot);
    result = channels.remove(channel);
    return result;
  }

  /**
   * Adds channel at the given timeSlot
   * @param timeSlot timeSlot to add channel at
   * @param channel the channel to add
   * @return boolean value based on success of channel being added at the given timeSlot
   */
  public Boolean addChannel(int timeSlot, String channel) {
    Boolean result;
    ChannelSet channels = channelsAvailable.get(timeSlot); // get a pointer to the channel set
    result = channels.add(channel); // remove the channel, returning the result
    return result;
  }

  /**
   * Gets the number of channels
   * @return Integer representing the number of channels
   */
  public Integer getNumChannels() {
    return nChannels;
  }

}
