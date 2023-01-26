import { Injectable } from '@angular/core';
import Konva from "konva";

@Injectable({
  providedIn: 'root'
})
export class KonvaService {

  constructor() { }
  myId: string = '1';
  fillColor: string = 'red'
  strokeColor: string = 'green'

  strokeWidth: string = '3';

  x1: any = window.innerWidth / 4
  y1: any = window.innerHeight / 4
  x2: any = window.innerWidth / 2
  y2: any = window.innerHeight / 2

  machine(){
    let rectangle = new Konva.Group({
        x: 25, 
        y: 25, 
        width: 130,
        height: 25,
        draggable: true,
    }); 

    rectangle.add(new Konva.Circle({
        width: 130,
        height: 25,
        radius: 40,
        fill: 'grey',
        stroke: 'blue'
    }));

    rectangle.add(new Konva.Text({
        text:'M',
        fontSize: 18,
        fontFamily: 'Calibri',
        fill: '#000',
        // width: 130,
        // padding: 5,
        align: 'center',
        x: -10,
        y: -10
      }));
      this.myId = (Number(this.myId) + 1).toString();
      return new Konva.Layer().add(rectangle)
  }
  
  queue(){
    let rectangle = new Konva.Group({
        x: 25, 
        y: 25, 
        width: 130,
        height: 25,
        draggable: true,
    }); 

    rectangle.add(new Konva.Circle({
        width: 130,
        height: 25,
        radius: 40,
        fill: 'lightblue',
        stroke: 'green'
    }));

    rectangle.add(new Konva.Text({
        text:'Q' + this.myId,
        fontSize: 18,
        fontFamily: 'Calibri',
        fill: '#000',
        // width: 130,
        // padding: 5,
        align: 'center',
        x: -10,
        y: -10
    }));
    this.myId = (Number(this.myId) + 1).toString();
    return new Konva.Layer().add(rectangle)
  }
}
