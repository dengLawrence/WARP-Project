package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ChannelsTest {

	/* --------------------------
	 * Begin getChannelSet tests
	 * -------------------------- */
	@Test
	void testGetChannelSet() {
		var channels = new Channels(0, true);
		final var size = 10;

		// Loop creates 'size' amount of channel sets, each timeslot get i amount of channels added.
		// timeslot 0 = 0 channels, timeslot 1 = 1 channels, timeslot  2 = 2 channels, ...timeslot n = n
		for(int i = 0; i < size; i++){
			channels.addNewChannelSet();
			for(int j = 0; j < i; j++) {
				channels.addChannel(i,Integer.toString(j)); //add j as a filler channel
			}
		}

		// Checks if getChannelSet works by comparing the timeslot with channel size which should be equal from above loop
		for(var i = 0; i < size; i++){
			var channelSet = channels.getChannelSet(i);
			System.out.println(i + ": " + channelSet.size());
			assertEquals(i, channelSet.size());
		}
	}

	/* --------------------------
	 * Begin addNewChannelSet tests
	 * -------------------------- */

	@Test
	void testAddNewChannelSetBySize() {
		final var channelSize = 0;
		var channels = new Channels(channelSize, true);
		final var channelSetSize = 10;

		assertEquals(0, channels.channelsAvailable.size()); // Checks if initial size is 0

		for(int i = 0; i < channelSetSize; i++){ // Adds the channelSets
			channels.addNewChannelSet();
		}

		assertEquals(channelSetSize, channels.channelsAvailable.size()); // Checks if the channelSet count is accurate
	}

	/* --------------------------
	 * Begin isEmpty tests
	 * -------------------------- */
	@Test
	void testIsEmptyTrue() {
		final var size = 0;
		var channels = new Channels(size, true);
		channels.addNewChannelSet();
		assertTrue(channels.isEmpty(0));
	}

	@Test
	void testIsEmptyFalse(){
		final var size = 10;
		var channels = new Channels(size, true);
		channels.addNewChannelSet();
		assertFalse(channels.isEmpty(0));
	}

	@Test
	void testEmptyAfterAdd(){
		final var size = 0;
		var channels = new Channels(size, true);
		channels.addNewChannelSet();
		assertTrue(channels.isEmpty(0));
		channels.addChannel(0, "Test");
		assertFalse(channels.isEmpty(0));
	}

	@Test
	void testEmptyAfterRemove(){
		final var size = 1;
		var channels = new Channels(size, true);
		channels.addNewChannelSet();
		assertFalse(channels.isEmpty(0));
		channels.removeChannel(0, "0");
		assertTrue(channels.isEmpty(0));
	}

	/* --------------------------
	 * Begin remove channel tests
	 * -------------------------- */
	@Test
	void testRemoveChannel() {
		final var size = 0;
		var channels = new Channels(size, true);
		channels.addNewChannelSet();
		assertEquals(0, channels.getChannelSet(0).size()); // Checks if created channelSet is 0
		channels.addChannel(0, "Test");
		assertEquals(1, channels.getChannelSet(0).size()); // Checks if channel was added
		assertTrue(channels.removeChannel(0, "Test")); // Checks if removeChannel returns true
		assertEquals(0, channels.getChannelSet(0).size()); // Checks if channel was removed by size
	}

	/* --------------------------
	 * Begin addChannel tests
	 * -------------------------- */
	@Test
	void testAddChannelTimeslots() {
		final var size = 0;
		var channels = new Channels(size, true); // Creates a channels class with no channels
		channels.addNewChannelSet();
		assertEquals(0, channels.getChannelSet(0).size()); // Checks if channelSet is indeed 0
		channels.addChannel(0, "Test"); //Adds a test channel
		assertEquals(1, channels.getChannelSet(0).size()); // Checks if the channel count increased for
																	        // timeslot 0 channelSet
	}

	@Test
	void testAddChannelDuplicates(){ // Test makes sure that duplicate channels aren't added
		var channels = new Channels(0, true);
		channels.addNewChannelSet();
		assertEquals(0, channels.getChannelSet(0).size());
		channels.addChannel(0, "Test");
		assertEquals(1, channels.getChannelSet(0).size());
		channels.addChannel(0, "Test");
		assertEquals(1, channels.getChannelSet(0).size());
	}

	@Test
	void testAddChannel(){
		var channels = new Channels(0, true);
		channels.addNewChannelSet();
		assertTrue(channels.addChannel(0, "Test"));
	}

	/* --------------------------
	 * Begin getNumChannels tests
	 * -------------------------- */
	@Test
	void testGetNumChannelsNew(){ //Tests the size passed in the constructor actually creates the intended size
		final var size = 10;
		var channels = new Channels(size, true);
		assertEquals(size, channels.getNumChannels());
	}

	@Test
	void testGetNumChannelsNChannels(){ //Tests if the method matches the nChannels variable
		final var size = 10;
		var channels = new Channels(size, true);
		assertEquals(channels.nChannels, channels.getNumChannels()); // getNumChannels returns nChannels, so this
																	 //	should never fail
	}
}
