package br.juliano.code.challenge.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.juliano.code.challenge.dao.LondonSubwayDAO;
import br.juliano.code.challenge.vo.LineVO;
import br.juliano.code.challenge.vo.RouteVO;
import br.juliano.code.challenge.vo.StationVO;

import com.google.gson.Gson;

public class LondonSubwayService {
	
	private LondonSubwayDAO dao = new LondonSubwayDAO();
	private List<StationVO> nodes;
	private List<LineVO> edges;
	private String timeSpent;
	
	public String getWayBetween(StationVO x, StationVO y) {
		
		nodes = dao.loadAllStations();
        edges = new ArrayList<LineVO>();
        fillLanes();
        DijkstraAlgorithm dijkstra = setupGraph();
        LinkedList<Integer> path = findWay(x, y, dijkstra);
		return new Gson().toJson(path); // TODO trazer mais dados na saída
		
	}

	private LinkedList<Integer> findWay(StationVO x, StationVO y, DijkstraAlgorithm dijkstra) {
		
		dijkstra.execute(x.getId());
        LinkedList<Integer> path = dijkstra.getPath(y.getId());
		return path;
		
	}

	private DijkstraAlgorithm setupGraph() {
		
		RouteVO graph = new RouteVO(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		return dijkstra;
		
	}

	private void fillLanes() {

		for (LineVO lineVO : dao.loadAllLines()) {
        	StationVO um = null;
        	StationVO dois = null;
        	for (StationVO stationVO : nodes) {
        		if (stationVO.getId() == lineVO.getStation1().getId())
        			um = stationVO;
        		if (stationVO.getId() == lineVO.getStation2().getId())
        			dois = stationVO;
        		if (um != null && dois != null) {
        			addLane(um, dois);
        			break;
        		}
			}
		}
		
	}
	
    private void addLane(StationVO sourceLocNo, StationVO destLocNo) {
        
        LineVO lane = new LineVO(sourceLocNo, destLocNo);
        edges.add(lane);
        
    }
	
	public String getShortestWayBetween(StationVO x, StationVO y) {
		
		nodes = dao.loadAllStations();
		edges = new ArrayList<LineVO>();
		timeSpent = "";
		return new Gson().toJson("");
		
	}
	
	public String getTimeSpentIfAny() {
		
		return timeSpent;
		
	}
	
}
