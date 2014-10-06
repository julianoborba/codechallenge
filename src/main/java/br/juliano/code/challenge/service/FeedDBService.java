package br.juliano.code.challenge.service;

import java.io.File;

import br.juliano.code.challenge.dao.LondonSubwayDAO;

public class FeedDBService {

    public boolean withThisFood(String serverUploadLocationFolder) {
        
        File feed = new File(serverUploadLocationFolder);
        
        LondonSubwayDAO londonSubwayDAO = new LondonSubwayDAO();
     
        return true;
        
    }

}
