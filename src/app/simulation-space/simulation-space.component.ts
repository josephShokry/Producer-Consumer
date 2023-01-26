import { Component, OnInit } from '@angular/core';
import Konva from "konva";
import { Stage } from 'konva/lib/Stage';
import { Layer } from 'konva/lib/Layer';
import { Transformer } from 'konva/lib/shapes/Transformer';
import { KonvaService } from '../Service/konva.service';

@Component({
  selector: 'app-simulation-space',
  templateUrl: './simulation-space.component.html',
  styleUrls: ['./simulation-space.component.css']
})
export class SimulationSpaceComponent implements OnInit{
  stage!: Stage;
  layer!: Layer;
  transformer!: Transformer;

  isLineMode = false;
  firstSelectedObject!: number[];
  secondSelectedObject!: number[];
  isFirstNotNull = false;
  myArrow!:any;
  targets!:any;
  connectors!: any;
  shapes: Map<string, any> = new Map([
  
  ]);
  constructor(private konva: KonvaService){}

  ngOnInit(){
    this.stage = new Stage({
      container: 'container',
      width: window.innerWidth,
      height: window.innerHeight
    });
    this.layer = new Layer();
    this.transformer = new Transformer();
    this.layer.add(this.transformer);
    this.stage.add(this.layer);

    this.stage.on('click', (e:any) => {

    })

/* 
    this.targets = this.generateTargets();
    this.connectors = this.generateConnectors();

  // generate nodes for the app
  this.connectors.forEach((connect:any) => {
    let line = new Konva.Arrow({
      stroke: 'black',
      id: connect.id,
      fill: 'black',
    });
    layer.add(line);
  });

  targets.forEach((target) => {
    let node = new Konva.Circle({
      id: target.id,
      fill: Konva.Util.getRandomColor(),
      radius: 20 + Math.random() * 20,
      shadowBlur: 10,
      draggable: true,
    });
    layer.add(node);

    node.on('dragmove', () => {
      // mutate the state
      target.x = node.x();
      target.y = node.y();

      // update nodes from the new state
      updateObjects();
    });
  }); */

  }

  drawMachine(){
    let myShape = this.konva.machine()
    this.setEvents(myShape);
    this.shapes.set(myShape.getAttrs().id, myShape);
    let lay = new Layer().add(myShape);
    this.stage.add(lay)
  }
  
  drawQueue(){
    let myShape = this.konva.queue();
    this.shapes.set(myShape.getAttr("id"), myShape);
    this.setEvents(myShape);
    let lay = new Layer().add(myShape);
    this.stage.add(lay);
  }

  setEvents(myShape: any){
    myShape.on('click', (e: any) => {
      if (this.isLineMode && !this.isFirstNotNull){
        this.firstSelectedObject = [myShape.getAttr("x"), myShape.getAttr("y")]
        console.log("first object selected ", this.firstSelectedObject);
        this.isFirstNotNull = true;
      }else if (this.isLineMode && this.isFirstNotNull){
        this.secondSelectedObject = [myShape.getAttr("x"), myShape.getAttr("y")]
        console.log("second object selected ", this.secondSelectedObject);
        this.isFirstNotNull = false;
        this.myArrow = this.drawArrow();
      }
    })
  }

  drawArrow() { 
    const dx = this.firstSelectedObject[0] - this.secondSelectedObject[1];
    const dy = this.firstSelectedObject[1] - this.secondSelectedObject[1];
    let radius = 20;
    let angle = Math.atan2(-dy, dx);
    let line = new Konva.Arrow({
    points:[this.firstSelectedObject[0] + -radius * Math.cos(angle + Math.PI),
        this.firstSelectedObject[1] + radius * Math.sin(angle + Math.PI),
        this.secondSelectedObject[0] + -radius * Math.cos(angle),
        this.secondSelectedObject[1] + radius * Math.sin(angle)],
      stroke: 'black',
      fill: 'black',
    })
    this.layer.add(line);
  }

  startSimulation(){
    console.log(this.stage)
  }

  drawLine(){
    this.isLineMode = true;
  }

  ///////////////////////////////////////////////////////////////////////////////////////////

  // function to generate a list of "targets" (circles)
  generateTargets() {
    let number = 10;
    let result = [];
    while (result.length < number) {
      result.push({
        id: 'target-' + result.length,
        x: this.stage.width() * Math.random(),
        y: this.stage.height() * Math.random(),
      });
    }
    return result;
  }

  

  // function to generate arrows between targets
  generateConnectors() {
    let number = 10;
    let result = [];
    while (result.length < number) {
      let from = 'target-' + Math.floor(Math.random() * this.targets.length);
      let to = 'target-' + Math.floor(Math.random() * this.targets.length);
      if (from === to) {
        continue;
      }
      result.push({
        id: 'connector-' + result.length,
        from: from,
        to: to,
      });
    }
    return result;
  }

  getConnectorPoints(from: { x: number; y: number; }, to: { x: number; y: number; }) {
    const dx = to.x - from.x;
    const dy = to.y - from.y;
    let angle = Math.atan2(-dy, dx);

    const radius = 50;

    return [
      from.x + -radius * Math.cos(angle + Math.PI),
      from.y + radius * Math.sin(angle + Math.PI),
      to.x + -radius * Math.cos(angle),
      to.y + radius * Math.sin(angle),
    ];
  }


  // update all objects on the canvas from the state of the app
  updateObjects() {
    this.targets.forEach((target: any) => {
      let node = this.layer.findOne('#' + target.id);
      node.x(target.x);
      node.y(target.y);
    });
    this.connectors.forEach((connect:any) => {
      let line = this.layer.findOne('#' + connect.id);
      let fromNode = this.layer.findOne('#' + connect.from);
      let toNode = this.layer.findOne('#' + connect.to);

      const points = this.getConnectorPoints(
        fromNode.position(),
        toNode.position()
      );
      // line.points(points);
      // line.setAttr()
    });
  }



  // updateObjects();


}
