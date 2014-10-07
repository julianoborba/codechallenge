package br.juliano.code.challenge.vo;

import java.util.List;

public class RouteVO { // Graph

    private String routeId;

    private int line;
    private String name;
    private String colour;
    private String stripe;

    private List<StationVO> vertexes;
    private List<LineVO> edges;

    public RouteVO() {
	}
    
    public RouteVO(List<StationVO> vertexes, List<LineVO> edges) {
        
        this.vertexes = vertexes;
        this.edges = edges;
    
    }

    public RouteVO(String routeId, int line, String name, String colour, String stripe, List<StationVO> vertexes, List<LineVO> edges) {
        
        this.routeId = routeId;
        this.line = line;
        this.name = name;
        this.colour = colour;
        this.stripe = stripe;
        this.vertexes = vertexes;
        this.edges = edges;
    
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getStripe() {
        return stripe;
    }

    public void setStripe(String stripe) {
        this.stripe = stripe;
    }

    public List<StationVO> getVertexes() {
        return vertexes;
    }

    public List<LineVO> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "RouteVO [routeId=" + routeId + ", line=" + line + ", name="
                + name + ", colour=" + colour + ", stripe=" + stripe
                + ", vertexes=" + vertexes + ", edges=" + edges + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((colour == null) ? 0 : colour.hashCode());
        result = prime * result + ((edges == null) ? 0 : edges.hashCode());
        result = prime * result + line;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
        result = prime * result + ((stripe == null) ? 0 : stripe.hashCode());
        result = prime * result
                + ((vertexes == null) ? 0 : vertexes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RouteVO other = (RouteVO) obj;
        if (colour == null) {
            if (other.colour != null)
                return false;
        } else if (!colour.equals(other.colour))
            return false;
        if (edges == null) {
            if (other.edges != null)
                return false;
        } else if (!edges.equals(other.edges))
            return false;
        if (line != other.line)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (routeId == null) {
            if (other.routeId != null)
                return false;
        } else if (!routeId.equals(other.routeId))
            return false;
        if (stripe == null) {
            if (other.stripe != null)
                return false;
        } else if (!stripe.equals(other.stripe))
            return false;
        if (vertexes == null) {
            if (other.vertexes != null)
                return false;
        } else if (!vertexes.equals(other.vertexes))
            return false;
        return true;
    }

}
