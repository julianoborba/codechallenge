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
        line.setLineId("line" + ":" + nextLineSeq());
        jedis.set(line.getLineId(), line.toRedisString());
        return line;

    }

    public RouteVO insertRoute(RouteVO route) {

        jedis.incr("routeSeq");
        route.setRouteId("route" + ":" + nextRouteSeq());
        jedis.set(route.getRouteId(), route.toRedisString());
        return route;

    }

    public StationVO insertStation(StationVO station) {

        jedis.incr("stationSeq");
        station.setStationId("station" + ":" + nextStationSeq());
        jedis.set(station.getStationId(), station.toRedisString());
        return station;

    }

    public List<StationVO> loadAllStations() {

        List<StationVO> stations = new ArrayList<StationVO>();
        int seq = Integer.parseInt(jedis.get("stationSeq"));

        for (int i = 1; i < seq; i++) {
            String rawStation = jedis.get("station" + ":" + i);
            String[] sStation = rawStation.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            StationVO station = new StationVO();
            station.setStationId("station" + ":" + i);
            station.setId(Integer.parseInt(sStation[0]));
            station.setLatitude(Double.parseDouble(sStation[1]));
            station.setLongitude(Double.parseDouble(sStation[2]));
            station.setName(sStation[3]);
            station.setDisplay_name(sStation[4]);
            station.setZone(Double.parseDouble(sStation[5]));
            station.setTotal_lines(Integer.parseInt(sStation[6]));
            station.setRail(Integer.parseInt(sStation[7]));
            stations.add(station);
        }

        return stations;

    }
    
    public List<LineVO> loadAllLines() {

        List<LineVO> lines = new ArrayList<LineVO>();
        int seq = Integer.parseInt(jedis.get("lineSeq"));

		for (int i = 1; i < seq; i++) {
			String rawLine = jedis.get("line" + ":" + i);
			String[] sLine = rawLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			StationVO station1 = new StationVO();
			station1.setId(Integer.parseInt(sLine[0]));
			StationVO station2 = new StationVO();
			station2.setId(Integer.parseInt(sLine[1]));
			RouteVO lini = new RouteVO();
			lini.setLine(Integer.parseInt(sLine[2]));
			LineVO line = new LineVO();
			line.setLineId("line" + ":" + i);
			line.setLine(lini);
			line.setStation1(station1);
			line.setStation2(station2);
			lines.add(line);
		}

        return lines;

    }
    
    public List<RouteVO> loadAllRoutes() {

        List<RouteVO> routes= new ArrayList<RouteVO>();
        int seq = Integer.parseInt(jedis.get("routeSeq"));

		for (int i = 1; i < seq; i++) {
			String rawLine = jedis.get("route" + ":" + i);
			String[] sLine = rawLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			RouteVO route = new RouteVO();
			route.setRouteId("route" + ":" + i);
			route.setLine(Integer.parseInt(sLine[0]));
			route.setName(sLine[1]);
			route.setColour(sLine[2]);
			route.setStripe(sLine[3]);
			routes.add(route);
		}

        return routes;
        
    }

}
