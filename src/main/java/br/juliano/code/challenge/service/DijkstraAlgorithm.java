package br.juliano.code.challenge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.juliano.code.challenge.vo.LineVO;
import br.juliano.code.challenge.vo.RouteVO;
import br.juliano.code.challenge.vo.StationVO;

public class DijkstraAlgorithm {

    private final List<StationVO> nodes;
    private final List<LineVO> edges;
    private Set<StationVO> settledNodes;
    private Set<StationVO> unSettledNodes;
    private Map<StationVO, StationVO> predecessors;
    private Map<StationVO, Integer> distance;

    public DijkstraAlgorithm(RouteVO graph) {
        this.nodes = new ArrayList<StationVO>(graph.getVertexes());
        this.edges = new ArrayList<LineVO>(graph.getEdges());
    }

    public void execute(StationVO source) {
        settledNodes = new HashSet<StationVO>();
        unSettledNodes = new HashSet<StationVO>();
        distance = new HashMap<StationVO, Integer>();
        predecessors = new HashMap<StationVO, StationVO>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            StationVO node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(StationVO node) {
        List<StationVO> adjacentNodes = getNeighbors(node);
        for (StationVO target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target,
                        getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(StationVO node, StationVO target) {
        for (LineVO edge : edges) {
            if (edge.getStation1().equals(node)
                    && edge.getStation2().equals(target)) {
                return (int) edge.distFrom(node.getLatitude(), node.getLongitude(), target.getLatitude(), target.getLongitude());
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<StationVO> getNeighbors(StationVO node) {
        List<StationVO> neighbors = new ArrayList<StationVO>();
        for (LineVO edge : edges) {
            if (edge.getStation1().equals(node)
                    && !isSettled(edge.getStation2())) {
                neighbors.add(edge.getStation2());
            }
        }
        return neighbors;
    }

    private StationVO getMinimum(Set<StationVO> vertexes) {
        StationVO minimum = null;
        for (StationVO vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(StationVO vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(StationVO destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<StationVO> getPath(StationVO target) {
        LinkedList<StationVO> path = new LinkedList<StationVO>();
        StationVO step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

}