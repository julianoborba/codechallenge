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
	
	public String getWayBetween(int x, int y) {
		
		nodes = dao.loadAllStations();
        edges = new ArrayList<LineVO>();
        fillLanes();
        DijkstraAlgorithm dijkstra = setupGraph();
        LinkedList<StationVO> path = findWay(getStationById(x), getStationById(y), dijkstra);
		return new Gson().toJson(path);
		
	}
	
    private StationVO getStationById(int id) {
    	
        for (StationVO stationVO : nodes) {
			if (stationVO.getId() == id) 
				return stationVO;
		}
        return null;
        
    }

	private LinkedList<StationVO> findWay(StationVO x, StationVO y, DijkstraAlgorithm dijkstra) {
		
		dijkstra.execute(x);
        LinkedList<StationVO> path = dijkstra.getPath(y);
		return path;
		
	}

	private DijkstraAlgorithm setupGraph() {
		
		RouteVO graph = new RouteVO(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		return dijkstra;
		
	}

	private void fillLanes() {

    	List<RouteVO> routes = dao.loadAllRoutes();
        nodes = dao.loadAllStations();
        edges = new ArrayList<LineVO>();
        for (LineVO lineVO : dao.loadAllLines()) {
        	
        	StationVO um = null;
        	StationVO dois = null;
        	RouteVO route = null;
        	
        	for (StationVO stationVO : nodes) {
        		if (stationVO.getId() == lineVO.getStation1().getId()) {
        			um = stationVO;
        		}
        		if (stationVO.getId() == lineVO.getStation2().getId()) {
        			dois = stationVO;
        		}
        		if (um != null && dois != null) {
        			break;
        		}
			}

        	if (um == null) {
        		um = new StationVO();
        		um.setId(lineVO.getStation1().getId());
        	}
        	
        	if (dois == null) {
        		dois = new StationVO();
        		dois.setId(lineVO.getStation2().getId());
        	}
        	
        	for (RouteVO routeVO : routes) {
        		if (routeVO.getLine() == lineVO.getLine().getLine()){
        			route = routeVO;
        		}
        	}
        	
        	addLane(um, dois, route);
		}
		
	}
	
    private void addLane(StationVO sourceLocNo, StationVO destLocNo, RouteVO route) {
        
        LineVO lane = new LineVO(route, sourceLocNo, destLocNo);
        edges.add(lane);
        
    }
	
	public String getShortestWayBetween(StationVO x, StationVO y) {
		
		// TODO implementar
		nodes = dao.loadAllStations();
		edges = new ArrayList<LineVO>();
		timeSpent = "";
		return new Gson().toJson("");
		
	}
	
	public String getTimeSpentIfAny() {
		
		// TODO implementar
		return timeSpent;
		
	}
	
}
