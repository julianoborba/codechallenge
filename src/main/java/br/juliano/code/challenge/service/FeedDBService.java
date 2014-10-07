package br.juliano.code.challenge.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.juliano.code.challenge.dao.LondonSubwayDAO;

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
			persistLine(fileLines, londonSubwayDAO);
		} else if (fileName.equals("routes.csv")) {
			persistRoute(fileLines, londonSubwayDAO);
		} else if (fileName.equals("stations.csv")) {
			persistStation(fileLines, londonSubwayDAO);
		}
		
	}

	private void persistLine(List<String> lines, LondonSubwayDAO londonSubwayDAO) {
		
		for (String line : lines.subList(1, lines.size())) {
			String[] rawLine = line.split(",");
			System.out.println(line);
//			LineVO lineVO = new LineVO(lineId, line, station1, station2);
//			londonSubwayDAO.insertLine(lineVO);
		}
		
	}
	
	private void persistRoute(List<String> lines, LondonSubwayDAO londonSubwayDAO) {
		
		for (String line : lines.subList(1, lines.size())) {
			String[] rawLine = line.split(",");
			System.out.println(line);
//			RouteVO routeVO = new RouteVO(routeId, line, name, colour, stripe, vertexes, edges);
//			londonSubwayDAO.insertRoute(routeVO);
		}
		
	}
	
	private void persistStation(List<String> lines, LondonSubwayDAO londonSubwayDAO) {
		
		for (String line : lines.subList(1, lines.size())) {
			String[] rawLine = line.split(",");
			System.out.println(line);
//			StationVO stationVO = new StationVO(stationId, id, latitude, longitude, name, display_name, zone, total_lines, rail);
//			londonSubwayDAO.insertStation(stationVO);
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
