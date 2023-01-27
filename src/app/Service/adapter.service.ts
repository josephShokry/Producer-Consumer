import { Injectable } from '@angular/core';
import { ControllerService } from './controller.service';
import { Dto } from './dto';

@Injectable({
  providedIn: 'root'
})
export class AdapterService {

  constructor(private controller: ControllerService) { }

  startSimulation(shapes: Map<string, any>, numberOfProducts: number){
    let dto:Dto = new Dto;
    dto.productsNumberInStock = numberOfProducts;

    shapes.forEach((v,k: any) => {
      let temp: Dto = new Dto;
      temp.x = v.getAttrs().x;
      temp.y = v.getAttrs().y;
      temp.color = v.children[0].getAttrs().fill;
      temp.id = v.getAttrs().id;
      temp.text = v.children[1].getAttrs().text;
      temp.type = v.getAttrs().name;
      if(temp.type == "queue"){
        for(let shape of v.getAttrs().out){
          temp.queueToMachine.push(shape.getAttrs().id);
        }
        for(let shape of v.getAttrs().in){
          temp.machineToQueue.push(shape.getAttrs().id);
        }
      }
      dto.rootGraph.push(temp);
      console.log("test dto mapping", dto);
    })  

    this.controller.startSimulation(dto).subscribe( data => {

    });
  }
}
