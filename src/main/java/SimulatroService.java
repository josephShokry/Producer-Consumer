import DTOs.Dto;
import models.Element;
import java.util.ArrayList;
import java.util.List;

public class SimulatroService {
    private List<Element> elements = new ArrayList<>();
    private int productsNumberInStock;
    private List<Dto> rootGraph;
    public SimulatroService(List<Dto> rootGraph){
        this.rootGraph = rootGraph;
    }
    public void buildElements(){
        for(Dto dto: rootGraph){
            elements.add(objectFactory.getObject(dto));
        }
    }
    // make the relation of the graph
    // start simulation
}
