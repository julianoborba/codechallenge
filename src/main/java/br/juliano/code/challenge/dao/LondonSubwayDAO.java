package br.juliano.code.challenge.dao;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import br.juliano.code.challenge.database.connection.factory.RedisDatabaseConnectionFactory;
import br.juliano.code.challenge.vo.LineVO;
import br.juliano.code.challenge.vo.RouteVO;
import br.juliano.code.challenge.vo.StationVO;

public class LondonSubwayDAO {

    Jedis jedis = RedisDatabaseConnectionFactory.openConnection("localhost", 6379);

    private long nextLineSeq() {
        return Long.parseLong(jedis.get("lineSeq"));
    }

    private long nextRouteSeq() {
        return Long.parseLong(jedis.get("routeSeq"));
    }

    private long nextStationSeq() {
        return Long.parseLong(jedis.get("stationSeq"));
    }

    public LineVO insertLine(LineVO line) {

        jedis.incr("lineSeq");
        jedis.set("line:" + ":" + nextLineSeq(), line.toString());
        line.setLineId("line:" + ":" + nextLineSeq());
        return line;

    }

    public RouteVO insertRoute(RouteVO route) {

        jedis.incr("routeSeq");
        jedis.set("route:" + ":" + nextRouteSeq(), route.toString());
        route.setRouteId("route:" + ":" + nextLineSeq());
        return route;

    }

    public StationVO insertStation(StationVO station) {

        jedis.incr("stationSeq");
        jedis.set("station:" + ":" + nextStationSeq(), station.toString());
        station.setStationId("station:" + ":" + nextLineSeq());
        return station;

    }

    public List<StationVO> loadAllStations() {

        List<StationVO> stations = new ArrayList<StationVO>();
        int seq = Integer.parseInt(jedis.get("stationSeq"));

        for (int i = 1; i < seq; i++) {
            String rawStation = jedis.get("station:" + ":" + seq);
            String[] sStation = rawStation.split(",");
            StationVO station = new StationVO();
            station.setStationId("station:" + ":" + seq);
            station.setId(Integer.parseInt(sStation[0]));
            station.setLatitude(Double.parseDouble(sStation[1]));
            station.setLongitude(Double.parseDouble(sStation[2]));
            station.setName(sStation[3]);
            station.setDisplay_name(sStation[4]);
            station.setZone(Integer.parseInt(sStation[5]));
            station.setTotal_lines(Integer.parseInt(sStation[6]));
            station.setRail(Integer.parseInt(sStation[7]));
            stations.add(station);
        }

        return stations;

    }

}
