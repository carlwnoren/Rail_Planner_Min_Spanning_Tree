/**
 * This class is a static RailPlanner. Based on a text file from a filepath provided by the user, 
 * its purpose is to provide a ValueGraph of a Minimum Spanning Tree.
 * It has the createPlan, buildRailLine, findStationSets, buildEstimate,
 * buildpathQueue, and buildStationSets methods.
 */
package edu.metrostate.ics340.cn6662.p3;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class RailPlanner {	
	/**
	 * This method, given a filepath to a text file outlining a number of edges from the user, 
	 * will build and return a ValueGraph with a minimum spanning tree build from those edges.
	 * @param estimateFilePath
	 * @return the ValueGraph showing the minimum spanning tree
	 */
	public static ValueGraph<String, Integer> createPlan(String estimateFilePath) {
		MutableValueGraph<String, Integer> railLine = ValueGraphBuilder.undirected().build();
		var estimateLine = buildEstimate(estimateFilePath);
		var pathQueue = buildPathQueue(estimateLine);
		var stationList = buildStationSets(estimateLine);
		buildRailLine(estimateLine, pathQueue, stationList, railLine);
		return railLine;
	}
	
	/*This helper method constructs the minimum spanning tree, based on the estimateLine, pathQueue, and stationSets constructed
	 * previously by the other helper methods in this class.
	 */
	private static void buildRailLine(MutableValueGraph<String, Integer> estimateLine, PriorityQueue<RailPath> pathQueue, 
			LinkedList<TreeSet<String>> stationSets, MutableValueGraph<String, Integer> railLine) {
		while (!pathQueue.isEmpty() ) {
			//Pull the lowest cost path from the queue
			var currentPath = pathQueue.poll();
			//Check if the station stations in the path are in the same linked set of stations
			var stationSetU = findStationSet(currentPath.getStationU(), stationSets);
			if (!stationSetU.contains(currentPath.getStationV())) {
				/*
				 * If the stations are not in the same set, find the set for the second station,
				 * and then merge the two sets.
				 */
				var stationSetV = findStationSet(currentPath.getStationV(), stationSets);
				railLine.putEdgeValue(currentPath.getStationU(), currentPath.getStationV(), currentPath.getCost());
				stationSetU.addAll(stationSetV);
				stationSets.remove(stationSetV);
			}
		}
	}

	/*
	 * This helper method finds which node in the stationSets LinkedList given station is located in.
	 */
	private static TreeSet<String> findStationSet(String station, LinkedList<TreeSet<String>> stationList) {
		for (TreeSet<String> stationSet : stationList) {
			if (stationSet.contains(station)) {
				return stationSet;
			}
		}
		return null;
	}

	/*
	 * This helper method builds a graph based on the edges in the file provided by the user.
	 * The edges and nodes of this graph will be used by other helper methods to build the final rail line.
	 */
	private static MutableValueGraph<String, Integer> buildEstimate(String filePath) {
		MutableValueGraph<String, Integer> estimates = ValueGraphBuilder.undirected().build();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Scanner scnr = new Scanner(fis);
			String[] edge;
			while (scnr.hasNext()) {
				edge = scnr.nextLine().split("\\|");
				estimates.putEdgeValue(edge[0], edge[1], Integer.parseInt(edge[2]));
				System.out.println(edge[0]);
			}
			scnr.close();
		} catch (IOException ioe) {
			throw new IllegalArgumentException("File is not readable or path does not exist.");
		}
		return estimates;
	}
	
	/*
	 * This helper method builds the PriorityQueue of Rail Path objects, or edges, based on the 
	 * cost of the path.
	 */
	private static PriorityQueue<RailPath> buildPathQueue(ValueGraph<String, Integer> estimateLine) {
		PriorityQueue<RailPath> pathQueue = new PriorityQueue<RailPath>();
		for (EndpointPair<String> path : estimateLine.edges()) {
			pathQueue.add(new RailPath(path, estimateLine.edgeValue(path).get()));
		}
		return pathQueue;
	}
	
	/* 
	 * This helper method builds a LinkedLIst of TreeSet<String> objects, each representing a set of
	 * stations currently linked together by safe paths (or edges). Initially, each station (or node) is
	 * its own tree.
	 */
	private static LinkedList<TreeSet<String>> buildStationSets(MutableValueGraph<String, Integer> estimateLine) {
		LinkedList<TreeSet<String>> stationSets = new LinkedList<TreeSet<String>>();
		for (String station : estimateLine.nodes() ) {
			TreeSet<String> tempTree = new TreeSet<String>();
			tempTree.add(station);
			stationSets.add(tempTree);
		}
		return stationSets;
	}
}