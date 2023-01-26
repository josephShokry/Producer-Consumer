package com.producer_consumer.controllers;

import com.producer_consumer.DTOs.Dto;
import com.producer_consumer.models.Element;
import com.producer_consumer.models.Machine;
import com.producer_consumer.models.Queue;

public class objectFactory {
    public static Element getObject(Dto dto){
        switch (dto.type){
            case "machine":
                //machineBuilder = MachineBuilder(dto)
                //return machineBuilder.build()
                return new Machine(dto,1);//(int)(Math.random()*(10)+1));

            case "queue":
                //queueBuilder = queueBuilder(dto)
                //return queueBuilder.build()
                return new Queue(dto);
            default: return null;
        }
    }
}
