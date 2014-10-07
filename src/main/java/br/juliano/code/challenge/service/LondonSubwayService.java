package br.juliano.code.challenge.service;

import java.util.ArrayList;
import java.util.List;

import br.juliano.code.challenge.dao.LondonSubwayDAO;
import br.juliano.code.challenge.vo.LineVO;
import br.juliano.code.challenge.vo.StationVO;

import com.google.gson.Gson;

public class LondonSubwayService {
	
	private LondonSubwayDAO dao = new LondonSubwayDAO();
	private List<StationVO> nodes;
	private List<LineVO> edges;
	private String timeSpent;
	
	public String getWayBetween(StationVO x, StationVO y) {
		
		nodes = dao.loadAllStations();
		edges = new ArrayList<LineVO>();
		return new Gson().toJson("");
		
	}
	
	public String getShortestWayBetween(StationVO x, StationVO y) {
		
		nodes = dao.loadAllStations();
		edges = new ArrayList<LineVO>();
		timeSpent = "";
		return new Gson().toJson("");
		
	}
	
	public String getTimeSpentIfAny() {
		
		return timeSpent;
		
	}
	
}
