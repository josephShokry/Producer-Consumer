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
        x: 25, 
        y: 25, 
        width: 130,
        height: 25,
        draggable: true,
    }); 

    circle.add(new Konva.Circle({
        width: 130,
        height: 25,
        radius: 40,
        fill: 'grey',
        stroke: 'blue'
    }));

    circle.add(new Konva.Text({
      text:'M',
      fontSize: 18,
      fontFamily: 'Calibri',
      fill: '#000',
      // width: 130,
      // padding: 5,
      align: 'center',
      name: "machine",
      x: -10,
      y: -10
    }));
      this.myId = (Number(this.myId) + 1).toString();
      return circle
  }

  queue(){
    let circle = new Konva.Group({
      id: this.myId,
        x: 25, 
        y: 25, 
        width: 130,
        height: 25,
        draggable: true,
        in:[],
        out:[],
    }); 

    circle.add(new Konva.Circle({
        width: 130,
        height: 25,
        radius: 40,
        fill: 'lightblue',
        stroke: 'green'
    }));

    circle.add(new Konva.Text({
      text:'Q' + this.myId,
      fontSize: 18,
      fontFamily: 'Calibri',
      fill: '#000',
      // width: 130,
      // padding: 5,
      align: 'center',
      name:"queue",
      x: -10,
      y: -10
    }));

    let arr;

    // circle.on('mouseup', (e: any) => {
    //   if(this.isLineMode){
    //   }
    // })
    this.myId = (Number(this.myId) + 1).toString();
    // let temp2 = new Konva.Layer();
    // temp2.setAttr("id", circle.id);
    // temp2.on('mouesdown', (e: any) => {
    //   if(this.isLineMode){
    //     this.firstSelectedObject = circle.getAttr("id");
    //     console.log("first object selected ", this.firstSelectedObject);
    //   }
    // })
    // temp2.add(circle);
    let myLayer = new Konva.Layer().add(circle);
    return circle;
  }


}
