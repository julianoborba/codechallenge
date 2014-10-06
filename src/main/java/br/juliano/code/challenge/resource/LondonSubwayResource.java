
package br.juliano.code.challenge.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.juliano.code.challenge.service.LondonSubwayService;

@Path("/fromLondonSubway")
public class LondonSubwayResource {
    
    private String timeSpent;
    
    @GET
    @Path("/getWayBetween")
    @Produces(MediaType.TEXT_PLAIN)
    public String getWayBetween(@QueryParam("param") final List<String> params) {
        // Um método que liste um caminho (contendo todas as estações) qualquer entre a estação X e a estação Y

        String x = params.get(0);
        String y = params.get(1);
        
        LondonSubwayService service = new LondonSubwayService();
        
        return "";
    }
    
    @GET
    @Path("/getShortestWayBetween")
    @Produces(MediaType.TEXT_PLAIN)
    public String getShortestWayBetween(@QueryParam("param") final List<String> params) {
        // Um método que liste o menor caminho (contendo todas as estações) (considerando a quantidade de paradas como requisito para o menor caminho) entre a estação X e a estação Y

        String x = params.get(0);
        String y = params.get(1);
        
        LondonSubwayService service = new LondonSubwayService();
        
        timeSpent = "";
        
        return "";
    }
    
    @GET
    @Path("/getTimeSpent") // from latest getShortestWayBetween
    @Produces(MediaType.TEXT_PLAIN)
    private String timeSpent() {
        // Um método que calcule o tempo aproximando da viagem no item 2, considerando que ao passar de uma estação adjacente à próxima, o passageiro gaste 3 minutos e ao trocar de linha gaste 12 minutos.
        
        LondonSubwayService service = new LondonSubwayService();
        
        return timeSpent;
    }
    
    // Anotações
    
    //http://traintimes.org.uk/map/tube/
    
    /*
        stations
        stationId  |id          |"latitude"|"longitude"|"name"            |"display_name"              |"zone"|"total_lines"|"rail"
        *          |133         |51.5027   |-0.1527    |"Hyde Park Corner"|"Hyde<br />Park<br />Corner"|1     |1            |0
        *          |146         |51.5015   |-0.1607    |"Knightsbridge"   |NULL                        |1     |1            |0
        
        lines
        lineId|"station1"|"station2"|line
        *     |133       |146         |10
        
        routes
        routeId |line        |"name"           |"colour"|"stripe"
        *       |10          |"Piccadilly Line"|"002d73"|NULL
     */

}
