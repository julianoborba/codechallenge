package br.juliano.code.challenge.vo;

public class StationVO { // Vertex

    private String stationId;
    
    private int id;
    private double latitude;
    private double longitude;
    private String name;
    private String display_name;
    private int zone;
    private int total_lines;
    private int rail;

    public StationVO() {
    }

    public StationVO(String stationId, int id, double latitude, double longitude, String name, String display_name, int zone, int total_lines, int rail) {
       
        this.stationId = stationId;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.display_name = display_name;
        this.zone = zone;
        this.total_lines = total_lines;
        this.rail = rail;
    
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getTotal_lines() {
        return total_lines;
    }

    public void setTotal_lines(int total_lines) {
        this.total_lines = total_lines;
    }

    public int getRail() {
        return rail;
    }

    public void setRail(int rail) {
        this.rail = rail;
    }

    @Override
    public String toString() {
        return "Station [stationId=" + stationId + ", id=" + id + ", latitude="
                + latitude + ", longitude=" + longitude + ", name=" + name
                + ", display_name=" + display_name + ", zone=" + zone
                + ", total_lines=" + total_lines + ", rail=" + rail + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((display_name == null) ? 0 : display_name.hashCode());
        result = prime * result + id;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + rail;
        result = prime * result
                + ((stationId == null) ? 0 : stationId.hashCode());
        result = prime * result + total_lines;
        result = prime * result + zone;
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
        StationVO other = (StationVO) obj;
        if (display_name == null) {
            if (other.display_name != null)
                return false;
        } else if (!display_name.equals(other.display_name))
            return false;
        if (id != other.id)
            return false;
        if (Double.doubleToLongBits(latitude) != Double
                .doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double
                .doubleToLongBits(other.longitude))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (rail != other.rail)
            return false;
        if (stationId == null) {
            if (other.stationId != null)
                return false;
        } else if (!stationId.equals(other.stationId))
            return false;
        if (total_lines != other.total_lines)
            return false;
        if (zone != other.zone)
            return false;
        return true;
    }

}
