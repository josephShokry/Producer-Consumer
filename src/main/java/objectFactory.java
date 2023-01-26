import DTOs.Dto;
import models.Element;
import models.Machine;
import models.Queue;

public class objectFactory {
    public static Element getObject(Dto dto){
        switch (dto.type){
            case "machine":
                //machineBuilder = MachineBuilder(dto)
                //return machineBuilder.build()
                new Machine(dto,(int)(Math.random()*(10)+1));
                break;
            case "queue":
                //queueBuilder = queueBuilder(dto)
                //return queueBuilder.build()
                new Queue(dto);
                break;
            default: return null;
        }
        return null;
    }
}
