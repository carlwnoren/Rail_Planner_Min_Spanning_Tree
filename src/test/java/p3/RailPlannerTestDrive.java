package p3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.graph.ValueGraph;

import edu.metrostate.ics340.cn6662.p3.RailPlanner;

public class RailPlannerTestDrive {

	@Test
	public void badFilePath() {
		assertThrows(IllegalArgumentException.class, () ->RailPlanner.createPlan("C:\\BadFilePath"));
	}
	
	@Test
	public void testBasic() {
		ValueGraph<String, Integer> testGraph = RailPlanner.createPlan(
				"C:\\Users\\carlw\\ICS 340\\Workspace\\p3\\src\\test\\resources\\base.txt");
		assertTrue(testGraph.edgeValue("Nevaehgate", "Syccvarka").get() == 85);
		assertEquals(testGraph.edges().size(), 8);
		assertTrue(testGraph.edgeValue("Chellcargate", "Hamuelland").get() == 23);
		assertTrue(testGraph.edgeValue("Ranaskeep", "Nevaehgate").get() == 45);
		assertTrue(testGraph.edgeValue("Chellcargate", "Dalrayss").get() == 85);
		assertTrue(testGraph.edgeValue("Nevaehgate", "Syccvarka").get() == 85);
		assertTrue(testGraph.edgeValue("Dalrayss", "Kalvinville").get() == 92);
		assertTrue(testGraph.edgeValue("Lyhalson", "Kalvinville").get() == 5);
		assertTrue(testGraph.edgeValue("Kalvinville", "Ikmam").get() == 35);
		assertTrue(testGraph.edgeValue("Kalvinville", "Syccvarka").get() == 47);
		assertTrue(testGraph.edgeValue("Chellcargate", "Hamuelland").get() == 23);
	}
	
}
