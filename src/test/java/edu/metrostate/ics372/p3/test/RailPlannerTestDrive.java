package edu.metrostate.ics372.p3.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.graph.EndpointPair;
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
				"C:\\Users\\carlw\\ICS 340\\Workspace\\P3_CN6662_RailPlanner\\src\\test\\resources\\base.txt");
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
	
	@Test
	public void testABCDEF() {
		ValueGraph<String, Integer> testGraph = RailPlanner.createPlan(
				"C:\\Users\\carlw\\ICS 340\\Workspace\\P3_CN6662_RailPlanner\\src\\test\\resources\\TestABCDEF.txt");
		assertEquals(testGraph.edges().size(), 5);
		assertEquals(testGraph.nodes().size(), 6);
		int pathSum = 0;
		for (EndpointPair<String> edge : testGraph.edges()) {
			pathSum += testGraph.edgeValue(edge.nodeU(), edge.nodeV()).get();
		}
		assertEquals(pathSum, 16);
	}
	
	@Test
	public void test100() {
		ValueGraph<String, Integer> testGraph = RailPlanner.createPlan(
				"C:\\Users\\carlw\\ICS 340\\Workspace\\P3_CN6662_RailPlanner\\src\\test\\resources\\Test100.txt");
		assertEquals(testGraph.edges().size(), 99);
		assertEquals(testGraph.nodes().size(), 100);
		int pathSum = 0;
		for (EndpointPair<String> edge : testGraph.edges()) {
			pathSum += testGraph.edgeValue(edge.nodeU(), edge.nodeV()).get();
		}
		System.out.println(pathSum);
	}
	
	@Test
	public void test1000() {
		ValueGraph<String, Integer> testGraph = RailPlanner.createPlan(
				"C:\\Users\\carlw\\ICS 340\\Workspace\\P3_CN6662_RailPlanner\\src\\test\\resources\\Test1000.txt");
		assertEquals(testGraph.edges().size(), 999);
		assertEquals(testGraph.nodes().size(), 1000);
		int pathSum = 0;
		for (EndpointPair<String> edge : testGraph.edges()) {
			pathSum += testGraph.edgeValue(edge.nodeU(), edge.nodeV()).get();
		}
		System.out.println(pathSum);
	}
	
	@Test
	public void test10000() {
		ValueGraph<String, Integer> testGraph = RailPlanner.createPlan(
				"C:\\Users\\carlw\\ICS 340\\Workspace\\P3_CN6662_RailPlanner\\src\\test\\resources\\Test10000.txt");
		assertEquals(testGraph.edges().size(), 9999);
		assertEquals(testGraph.nodes().size(), 10000);
		int pathSum = 0;
		for (EndpointPair<String> edge : testGraph.edges()) {
			pathSum += testGraph.edgeValue(edge.nodeU(), edge.nodeV()).get();
		}
		System.out.println(pathSum);
	}
}