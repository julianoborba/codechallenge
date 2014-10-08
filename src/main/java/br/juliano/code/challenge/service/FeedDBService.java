package br.juliano.code.challenge.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.juliano.code.challenge.dao.LondonSubwayDAO;
import br.juliano.code.challenge.vo.LineVO;
import br.juliano.code.challenge.vo.RouteVO;
import br.juliano.code.challenge.vo.StationVO;

import com.google.gson.Gson;

public class FeedDBService {

	public boolean withThisFood(String serverUploadLocationFolder) {

		Path path = Paths.get(serverUploadLocationFolder);
		String fileName = path.getFileName().toString();
		List<String> fileLines = getFileLinesFrom(path);
		LondonSubwayDAO londonSubwayDAO = new LondonSubwayDAO();
		persist(fileName, fileLines, londonSubwayDAO);
		return true;

	}

	private void persist(String fileName, List<String> fileLines, LondonSubwayDAO londonSubwayDAO) {
		
		if (fileName.equals("lines.csv")) {
			persist(fileLines, londonSubwayDAO, new LineVO());
		} else if (fileName.equals("routes.csv")) {
			persist(fileLines, londonSubwayDAO, new RouteVO());
		} else if (fileName.equals("stations.csv")) {
			persist(fileLines, londonSubwayDAO, new StationVO());
		}
		
	}

	private void persist(List<String> lines, LondonSubwayDAO londonSubwayDAO, LineVO lineVO) {
		
		for (String line : lines.subList(1, lines.size())) {
			String[] raw = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			StationVO station1 = new StationVO();
			station1.setId(Integer.parseInt(raw[0]));
			StationVO station2 = new StationVO();
			station2.setId(Integer.parseInt(raw[1]));
			RouteVO lini = new RouteVO();
			lini.setLine(Integer.parseInt(raw[2]));
			lineVO.setLine(lini);
			lineVO.setStation1(station1);
			lineVO.setStation2(station2);
			londonSubwayDAO.insertLine(lineVO);
		}
		
	}
	
	private void persist(List<String> lines, LondonSubwayDAO londonSubwayDAO, RouteVO routeVO) {
		
		for (String line : lines.subList(1, lines.size())) {
			String[] raw = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			routeVO.setLine(Integer.parseInt(raw[0]));
			routeVO.setName(raw[1]);
			routeVO.setColour(raw[2]);
			routeVO.setStripe(raw[3]);
			londonSubwayDAO.insertRoute(routeVO);
		}
		
	}
	
	private void persist(List<String> lines, LondonSubwayDAO londonSubwayDAO, StationVO stationVO) {
		
		for (String line : lines.subList(1, lines.size())) {
			String[] raw = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			stationVO.setId(Integer.parseInt(raw[0]));
			stationVO.setLatitude(Double.parseDouble(raw[1]));
			stationVO.setLongitude(Double.parseDouble(raw[2]));
			stationVO.setName(raw[3]);
			stationVO.setDisplay_name(raw[4]);
			stationVO.setZone(Double.parseDouble(raw[5]));
			stationVO.setTotal_lines(Integer.parseInt(raw[6]));
			stationVO.setRail(Integer.parseInt(raw[7]));
			londonSubwayDAO.insertStation(stationVO);
		}
		
	}

	private List<String> getFileLinesFrom(Path feed) {

		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(feed, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;

	}

}
