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
    private Set<Integer> settledNodes;
    private Set<Integer> unSettledNodes;
    private Map<Integer, Integer> predecessors;
    private Map<Integer, Double> distance;

    public DijkstraAlgorithm(RouteVO graph) {
        this.nodes = new ArrayList<StationVO>(graph.getVertexes());
        this.edges = new ArrayList<LineVO>(graph.getEdges());
    }

    public void execute(Integer source) {
        settledNodes = new HashSet<Integer>();
        unSettledNodes = new HashSet<Integer>();
        distance = new HashMap<Integer, Double>();
        predecessors = new HashMap<Integer, Integer>();
        distance.put(source, new Double(0));
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Integer node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Integer node) {
        List<Integer> adjacentNodes = getNeighbors(node);
        for (Integer target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    private double getDistance(Integer node, Integer target) {
        for (LineVO edge : edges) {
            if (edge.getStation1().getId() == node.intValue() && edge.getStation2().getId() == target.intValue()) {
                return edge.distFrom(edge.getStation1().getLatitude(), edge.getStation1().getLongitude(), edge.getStation2().getLatitude(), edge.getStation2().getLongitude());
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Integer> getNeighbors(Integer node) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (LineVO edge : edges) {
            if (edge.getStation1().getId() == node.intValue() && !isSettled(edge.getStation2().getId())) {
                neighbors.add(edge.getStation2().getId());
            }
        }
        return neighbors;
    }

    private Integer getMinimum(Set<Integer> vertexes) {
        Integer minimum = null;
        for (Integer vertex : vertexes) {
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

    private boolean isSettled(Integer vertex) {
        return settledNodes.contains(vertex);
    }

    private double getShortestDistance(Integer destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Integer> getPath(Integer target) {
        LinkedList<Integer> path = new LinkedList<Integer>();
        Integer step = target;
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