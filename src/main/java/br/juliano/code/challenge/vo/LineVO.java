package br.juliano.code.challenge.vo;

public class LineVO { // Edge

    private String lineId;

    private int line; // line FK from Route
    private StationVO station1; // id FK from Station
    private StationVO station2; // id FK from Station

    public LineVO() {
    }
    
    public LineVO(StationVO station1, StationVO station2) {
        this.station1 = station1;
        this.station2 = station2;
    }

    public LineVO(String lineId, int line, StationVO station1, StationVO station2) {
        this.lineId = lineId;
        this.line = line;
        this.station1 = station1;
        this.station2 = station2;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public StationVO getStation1() {
        return station1;
    }

    public void setStation1(StationVO station1) {
        this.station1 = station1;
    }

    public StationVO getStation2() {
        return station2;
    }

    public void setStation2(StationVO station2) {
        this.station2 = station2;
    }

    @Override
    public String toString() {
        return "LineVO [lineId=" + lineId + ", line=" + line + ", station1="
                + station1 + ", station2=" + station2 + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + line;
        result = prime * result + ((lineId == null) ? 0 : lineId.hashCode());
        result = prime * result
                + ((station1 == null) ? 0 : station1.hashCode());
        result = prime * result
                + ((station2 == null) ? 0 : station2.hashCode());
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
        LineVO other = (LineVO) obj;
        if (line != other.line)
            return false;
        if (lineId == null) {
            if (other.lineId != null)
                return false;
        } else if (!lineId.equals(other.lineId))
            return false;
        if (station1 == null) {
            if (other.station1 != null)
                return false;
        } else if (!station1.equals(other.station1))
            return false;
        if (station2 == null) {
            if (other.station2 != null)
                return false;
        } else if (!station2.equals(other.station2))
            return false;
        return true;
    }

}
