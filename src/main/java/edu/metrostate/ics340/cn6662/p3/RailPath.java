/**
 * This class implements a RailPath object, which is meant to represent two stations
 * and the path that connects them. It has the stations and cost attributes.
 */
package edu.metrostate.ics340.cn6662.p3;

import com.google.common.graph.EndpointPair;

public class RailPath implements Comparable<RailPath>{
	EndpointPair<String> stations;
	int cost;
	
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
	
	public String getStationU() {
		return stations.nodeU();
	}
	
	public String getStationV() {
		return stations.nodeV();
	}

	@Override
	public int compareTo(RailPath path) {
		return Integer.compare(cost, path.getCost());
	}
}