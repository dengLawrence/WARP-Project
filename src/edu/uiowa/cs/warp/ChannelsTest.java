package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ChannelsTest {

	final int channelSize = 0;


	/* --------------------------
	 * Begin getChannelSet tests
	 * -------------------------- */
	@Test
	void testGetChannelSetByChannelSetSize() { // Unintended use of the channels class, but works
		final var size = 10; // How many channelSets to be added.
		var channels = new Channels(channelSize, true);


		// Loop creates 'size' amount of channel sets, each timeSlot get i amount of channels added.
		// timeSlot 0 = channelSet size of 0, timeSlot 1 = channelSet size of 1, ...timeSlot n = channelSet size of n
		for(int i = 0; i < size; i++){
			channels.addNewChannelSet();
			for(int j = 0; j < i; j++) {
				channels.addChannel(i,Integer.toString(j)); //add j as a filler channel
			}
		}

		// Checks if getChannelSet works by comparing the timeSlot with channel size which should be equal from above loop
		for(var i = 0; i < size; i++){
			var channelSet = channels.getChannelSet(i);
			System.out.println(channelSet.size());
			assertEquals(i, channelSet.size(), "Error in getChannelSet returning correct Channel");
		}
	}

	@Test
	void testGetChannelSetByChannel(){
		var channels = new Channels(channelSize, true);
		channels.addNewChannelSet();
		channels.addNewChannelSet();

		channels.addChannel(0, "Test 0");
		channels.addChannel(1, "Test 1");

		assertTrue(channels.getChannelSet(0).contains("Test 0"));
		assertTrue(channels.getChannelSet(1).contains("Test 1"));
	}

	/* --------------------------
	 * Begin addNewChannelSet tests
	 * -------------------------- */
	@Test
	void testAddNewChannelSetBySize() {
		var channels = new Channels(channelSize, true);
		final var channelSetSize = 10;

		// Checks if initial size is 0
		assertEquals(0, channels.channelsAvailable.size(), "Error in new ChannelSet Size");

		for(int i = 0; i < channelSetSize; i++){ // Adds the channelSets
			channels.addNewChannelSet();
		}

		// Checks if the channelSet count is accurate
		assertEquals(channelSetSize, channels.channelsAvailable.size(), "ChannelSet size error");
	}

	/* --------------------------
	 * Begin isEmpty tests
	 * -------------------------- */
	@Test
	void testIsEmptyWhenCreatingEmptyChannels() {
		var channels = new Channels(channelSize, true);
		channels.addNewChannelSet();
		assertTrue(channels.isEmpty(0), "IsEmpty failed on zero ChannelSet Size");
	}

	@Test
	void testIsEmptyWhenChannelsStartAboveZero(){
		final var channelSize = 10;
		var channels = new Channels(channelSize, true);
		channels.addNewChannelSet();
		assertFalse(channels.isEmpty(0), "IsEmpty retuned true on an non empty ChannelSet Size");
	}

	@Test
	void testEmptyAfterAdd(){
		var channels = new Channels(channelSize, true);
		channels.addNewChannelSet();


		assertTrue(channels.isEmpty(0), "Error in new ChannelSet Size");
		channels.addChannel(0, "Test");
		assertFalse(channels.isEmpty(0), "ChannelSet empty after adding channel");
	}

	@Test
	void testEmptyAfterRemove(){
		final var channelSize = 1;
		var channels = new Channels(channelSize, true);
		channels.addNewChannelSet();

		// Checks if created is not empty
		assertFalse(channels.isEmpty(0), "Error in new ChannelSet Size");
		channels.removeChannel(0, "0");

		// Checks if removal of the only channel makes an empty ChannelSet
		assertTrue(channels.isEmpty(0), "ChannelSet not empty after channel removal");
	}

	/* --------------------------
	 * Begin remove channel tests
	 * -------------------------- */
	@Test
	void testRemoveChannel() {
		var channels = new Channels(channelSize, true);
		channels.addNewChannelSet();

		// Checks if created channelSet is 0
		assertEquals(0, channels.getChannelSet(0).size(), "Error in new ChannelSet Size");
		channels.addChannel(0, "Test");

		// Checks if channel was added
		assertEquals(1, channels.getChannelSet(0).size(), "Channel add failed, ChannelSet is empty");

		// Checks if removeChannel returns true
		assertTrue(channels.removeChannel(0, "Test"), "Channel removal failed");

		// Checks if channel was removed by size
		assertEquals(0, channels.getChannelSet(0).size(), "Channel removal failed");
	}

	/* --------------------------
	 * Begin addChannel tests
	 * -------------------------- */
	@Test
	void testAddChannelTimeSlots() {
		var channels = new Channels(channelSize, true); // Creates a channels class with no channels
		channels.addNewChannelSet();

		// Checks if channelSet is indeed 0
		assertEquals(0, channels.getChannelSet(0).size(), "Error in new ChannelSet Size");
		channels.addChannel(0, "Test"); //Adds a test channel

		// Checks if the channel count increased for timeSlot 0 channelSet
		assertEquals(1, channels.getChannelSet(0).size(), "Channel add failed, ChannelSet is empty");
	}

	@Test
	void testAddChannelDuplicates(){ // Test makes sure that duplicate channels aren't added
		var channels = new Channels(channelSize, false);
		channels.addNewChannelSet();

		// Checks if initial is zero
		assertEquals(0, channels.getChannelSet(0).size(), "Error in new ChannelSet Size");
		channels.addChannel(0, "Test");

		// Checks if adding channel worked
		assertEquals(1, channels.getChannelSet(0).size(), "Channel add failed, ChannelSet is empty");
		channels.addChannel(0, "Test");

		// Confirms duplicate channels aren't added
		assertEquals(1, channels.getChannelSet(0).size(), "Error, channel duplicate added");
	}

	@Test
	void testAddChannel(){
		var channels = new Channels(channelSize, true);
		channels.addNewChannelSet();
		assertTrue(channels.addChannel(0, "Test"), "Channel add failed, ChannelSet is empty");
	}

	/* --------------------------
	 * Begin getNumChannels tests
	 * -------------------------- */
	@Test
	void testGetNumChannelsNew(){ //Tests the size passed in the constructor actually creates the intended size
		final var channelSize = 10;
		var channels = new Channels(channelSize, true);
		assertEquals(channelSize, channels.getNumChannels(), "NumChannels has incorrect size");
	}

	@Test
	void testGetNumChannelsNChannels(){ //Tests if the method matches the nChannels variable
		final var channelSize = 10;
		var channels = new Channels(channelSize, true);

		// getNumChannels returns nChannels, so this should never fail
		assertEquals(channels.nChannels, channels.getNumChannels(), "GetNumChannels does not equal nChannels");
	}
}
