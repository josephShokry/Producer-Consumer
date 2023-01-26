import DTOs.Dto;
import models.Element;

public class objectFactory {
    public Element getObject(Dto dto){
        switch (dto.type){
            case "machine":
                //machineBuilder = MachineBuilder(dto)
                //return machineBuilder.build()
                break;
            case "queue":
                //queueBuilder = queueBuilder(dto)
                //return queueBuilder.build()
                break;
            default: return null;
        }
        return null;
    }
}
