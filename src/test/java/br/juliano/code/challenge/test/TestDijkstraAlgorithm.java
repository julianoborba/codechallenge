package br.juliano.code.challenge.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

import br.juliano.code.challenge.dao.LondonSubwayDAO;
import br.juliano.code.challenge.service.DijkstraAlgorithm;
import br.juliano.code.challenge.vo.LineVO;
import br.juliano.code.challenge.vo.RouteVO;
import br.juliano.code.challenge.vo.StationVO;

public class TestDijkstraAlgorithm {

    private List<StationVO> nodes;
    private List<LineVO> edges;

    @Test
    public void testExcute() {
        
    	LondonSubwayDAO dao = new LondonSubwayDAO();
    	
        nodes = dao.loadAllStations();
        edges = new ArrayList<LineVO>();
        for (LineVO lineVO : dao.loadAllLines()) {
        	StationVO um = null;
        	StationVO dois = null;
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
        	// Rotina experimental
        	if (um == null) {
        		um = new StationVO();
        		um.setId(lineVO.getStation1().getId());
        	}
        	if (dois == null) {
        		dois = new StationVO();
        		dois.setId(lineVO.getStation2().getId());
        	}
        	addLane(um, dois);
		}
        
        RouteVO graph = new RouteVO(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        
        dijkstra.execute(133);
        LinkedList<Integer> path = dijkstra.getPath(146); // 293

        assertNotNull(path);
        assertTrue(path.size() > 0);

        System.out.println(new Gson().toJson(path));

    }

    private void addLane(StationVO sourceLocNo, StationVO destLocNo) {
        
        LineVO lane = new LineVO(sourceLocNo, destLocNo);
        edges.add(lane);
        
    }
    
}
