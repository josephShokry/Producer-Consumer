import { Injectable } from '@angular/core';
import { MatLine } from '@angular/material/core';
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
    let circle = new Konva.Group({
      id: this.myId,
        x: 700, 
        y: 300, 
        width: 130,
        height: 25,
        name: "machine",
        in: [],
        out: [],
        draggable: true,
    }); 

    circle.add(new Konva.Circle({
        width: 130,
        height: 25,
        radius: 40,
        fill: '#FFFF00',
        stroke: 'red'
    }));

    circle.add(new Konva.Text({
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
      return circle
  }

  queue(){
    let circle = new Konva.Group({
      id: this.myId,
        x: 700, 
        y: 500, 
        width: 130,
        height: 25,
        draggable: true,
        name:"queue",
        in:[],
        out:[],
    }); 

    circle.add(new Konva.Circle({
        width: 130,
        height: 25,
        radius: 40,
        fill: '#E5E0FF',
        stroke: '#7286D3'
    }));

    circle.add(new Konva.Text({
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

    let arr;

    this.myId = (Number(this.myId) + 1).toString();
    let myLayer = new Konva.Layer().add(circle);
    return circle;
  }


}
