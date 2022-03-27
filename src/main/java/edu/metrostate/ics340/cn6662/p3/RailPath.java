/**
 * This class implements a RailPath object, which is meant to represent two stations
 * and the path that connects them. It has the stations and cost attributes.
 */
package edu.metrostate.ics340.cn6662.p3;

import com.google.common.graph.EndpointPair;

public class RailPath implements Comparable<RailPath>{
	EndpointPair<String> stations;
	int cost;
	
	/**
	 * Constructs a RailPath object with an endpoint pair and cost given by the user.
	 * @param stations
	 * @param cost
	 */
	public RailPath(EndpointPair<String> stations, int cost) {
		this.stations = stations;
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	
	public EndpointPair<String> getStations() {
		return stations;
	}
	
	/**
	 * Returns one of the stations along this rail set. The station returned as U is
	 * arbitrary but consistent.
	 * @return
	 */
	public String getStationU() {
		return stations.nodeU();
	}
	
	/**
	 * Returns one of the stations along this rail set. The station returned as V is
	 * arbitrary but consistent.
	 * @return
	 */
	public String getStationV() {
		return stations.nodeV();
	}

	/**
	 * compareTo set to compare RailPath objects by their costs.
	 */
	@Override
	public int compareTo(RailPath path) {
		return Integer.compare(cost, path.getCost());
	}
}