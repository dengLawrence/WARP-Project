package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

class ChannelAnalysisTest {

	private ChannelAnalysis createChannelAnalysis(Integer numFaults, String inputFile, Integer numChannels) {
		WorkLoad workload = new WorkLoad(numFaults, .9, .99, inputFile);
		ScheduleChoices choice = ScheduleChoices.PRIORITY;
		WarpInterface warp = new WarpSystem(workload, numChannels, choice);
		ChannelAnalysis channelAnalysis = new ChannelAnalysis(warp);
		return channelAnalysis;
	}
	
	@Test
	void buildChannelAnalysisTableTestEmpty() {
		ChannelAnalysis channelAnalysis = createChannelAnalysis(1, "Empty.txt", 16);
		ProgramSchedule expected = new ProgramSchedule(16, 5);
		expected.set(1,  0, "[A]::F0:(A:B)");
		expected.set(2, 1, "[A]::F0:(A:B)");
		ProgramSchedule actual = channelAnalysis.getChannelAnalysisTable();
		assertEquals(actual, expected);
		System.out.println(actual.toString());
		System.out.println(expected.toString());
	}

}
