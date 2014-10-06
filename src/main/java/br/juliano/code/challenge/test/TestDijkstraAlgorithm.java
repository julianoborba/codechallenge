package br.juliano.code.challenge.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import br.juliano.code.challenge.service.DijkstraAlgorithm;
import br.juliano.code.challenge.vo.LineVO;
import br.juliano.code.challenge.vo.RouteVO;
import br.juliano.code.challenge.vo.StationVO;

public class TestDijkstraAlgorithm {

    private List<StationVO> nodes;
    private List<LineVO> edges;

    @Test
    public void testExcute() {
        
        nodes = new ArrayList<StationVO>();
        edges = new ArrayList<LineVO>();
        
        for (int i = 0; i < 11; i++) {
            StationVO location = new StationVO();
            nodes.add(location);
        }

        RouteVO graph = new RouteVO(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<StationVO> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (StationVO vertex : path) {
            System.out.println(vertex);
        }

    }

    private void addLane(StationVO sourceLocNo, StationVO destLocNo) {
        
        LineVO lane = new LineVO(sourceLocNo, destLocNo);
        edges.add(lane);
        
    }
    
}