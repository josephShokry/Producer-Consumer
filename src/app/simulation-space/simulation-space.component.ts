import { Component, OnDestroy, OnInit } from '@angular/core';
import Konva from "konva";
import { Stage } from 'konva/lib/Stage';
import { Layer } from 'konva/lib/Layer';
import { Transformer } from 'konva/lib/shapes/Transformer';
import { KonvaService } from '../Service/konva.service';
import { ControllerService } from '../Service/controller.service';
import { AdapterService } from '../Service/adapter.service';
import {Subscription, switchMap, timer} from 'rxjs';  
import { Group } from 'konva/lib/Group';
import { Dto } from '../Service/dto';

@Component({
  selector: 'app-simulation-space',
  templateUrl: './simulation-space.component.html',
  styleUrls: ['./simulation-space.component.css']
})
export class SimulationSpaceComponent implements OnInit, OnDestroy{
  stage!: Stage;
  layer!: Layer;
  transformer!: Transformer;

  isLineMode = false;
  firstSelectedObject!: any;
  secondSelectedObject!: any;
  isFirstNotNull = false;
  myArrow!:any;
  targets!:any;
  connectors!: any;
  shapes: Map<string, any> = new Map([ ]);
  subscription!: Subscription;
  productsInQueue: Map<string, number> = new Map([]);
  numberOfProducts: number = 0;
  columnsToDisplay = ['queueName', 'numberOfProducts']
  dtoStorage: Dto[] = [];
  replayStarted = false;

  constructor(private konva: KonvaService, private controller: ControllerService, private adapter: AdapterService){}

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
    let circle = new Konva.Group({
      id: '0',
        x: 300, 
        y: 300, 
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
        fill: '#B9F3FC',
        stroke: 'purple',
        strokeWidth: 5
    }));

    circle.add(new Konva.Text({
      text:'Q0',
      fontSize: 18,
      fontFamily: 'Calibri',
      fill: '#000',
      // width: 130,
      // padding: 5,
      align: 'center',
      x: -10,
      y: -10
    }));

    let tempLayer = new Konva.Layer();
    this.setEvents(circle);
    tempLayer.add(circle);
    this.stage.add(tempLayer);
    
    let end = new Konva.Group({
      id: '100',
      x: 1250, 
        y: 300, 
        width: 130,
        height: 25,
        draggable: true,
        name:"queue",
        in:[],
        out:[],
      }); 
      
      end.add(new Konva.Circle({
        width: 130,
        height: 25,
        radius: 40,
        fill: '#B9F3FC',
        stroke: 'purple',
        strokeWidth: 5
      }));
      
      end.add(new Konva.Text({
        text:'Q_E',
        fontSize: 18,
        fontFamily: 'Calibri',
        fill: '#000',
        // width: 130,
        // padding: 5,
        align: 'center',
        x: -15,
        y: -10
      }));
      let tempLayer2 = new Konva.Layer();
      this.setEvents(end);
      tempLayer2.add(end);
      this.stage.add(tempLayer2);

      
      this.subscription = timer(0, 200).pipe(
        switchMap(() => this.controller.getUpdate())
      ).subscribe(data => {
        this.dtoStorage.push(data);
        if(!this.replayStarted){
          for(let i of data.rootGraph){
            console.log("data changed", data);
            let shape: any = this.stage.findOne("#" + i.id);
  
            if(shape.getAttrs().name == "machine")
              shape.children[0].setAttr("fill", i.color);
  
            if(shape.getAttrs().name == "queue"){
              shape.children[1].setAttr("text", 'Q' + shape.getAttrs().id+ "\n" +  i.numberOfProducts)
              this.productsInQueue.set("Q" + shape.getAttrs().id, i.numberOfProducts);
            }
              console.log(this.productsInQueue);
          }
        }
      })
      

      // this.stage.on('click', (e:any) => {

    // })

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

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
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
    console.log(myShape);
    console.log(this.stage)
    // this.productsInQueue.push({queueName: myShape.getAttrs().name, numberOfProducts: 0})
    // this.productsInQueue.push({queueName: "Q1", numberOfProducts: 0})
    this.productsInQueue.set('Q' + myShape.getAttrs().id, 0);
    console.log(this.productsInQueue)
    let lay = new Layer().add(myShape);
    this.stage.add(lay);
    console.log(this.stage.find('#' + myShape));
  }

  async replaySimulation(){
    this.replayStarted = true;

    let s: any = this.stage.findOne("#" + "100")
    s.children[1].setAttr("text", "Q" + s.getAttrs().id);

    for(let data of this.dtoStorage){
      console.log("replay changed", data);
      for(let i of data.rootGraph){
        let shape: any = this.stage.findOne("#" + i.id);

        if(shape.getAttrs().name == "machine")
          shape.children[0].setAttr("fill", i.color);

        if(shape.getAttrs().name == "queue"){
          shape.children[1].setAttr("text", 'Q' + shape.getAttrs().id+ "\n" +  i.numberOfProducts)
          this.productsInQueue.set("Q" + shape.getAttrs().id, i.numberOfProducts);
        }
          // console.log(this.productsInQueue);
      }
      await this.delay(200);
    }

  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  incrementProduct(){
    this.controller.incrementProduct().subscribe(data => {
      this.numberOfProducts += 1;
    })
  }

  setEvents(myShape: any){
    myShape.on('click', (e: any) => {
      if (this.isLineMode && !this.isFirstNotNull){
        this.firstSelectedObject = myShape;
        console.log("first object selected ", this.firstSelectedObject);
        this.isFirstNotNull = true;
      }else if (this.isLineMode && this.isFirstNotNull && myShape != this.firstSelectedObject){
        this.secondSelectedObject = myShape;
        console.log("second object selected ", this.secondSelectedObject);
        this.isFirstNotNull = false;
        this.myArrow = this.drawArrow();
      }
    })

    myShape.on("dragmove", (e:any) => {

    })

  }

  drawArrow() { 
    let line = new Konva.Arrow({
    points: this.calculateLine(this.firstSelectedObject, this.secondSelectedObject),
      stroke: 'black',
      fill: 'black',
      from: this.firstSelectedObject,
      to: this.secondSelectedObject 
    })
    let temp: any[] = this.stage.findOne("#" + this.firstSelectedObject.getAttrs().id).getAttrs().out;
    temp.push(this.secondSelectedObject);

    temp = this.stage.findOne("#" + this.secondSelectedObject.getAttrs().id).getAttrs().in
    temp.push(this.firstSelectedObject);

    this.shapes.set(this.firstSelectedObject.getAttrs().id, this.stage.findOne("#" + this.firstSelectedObject.getAttrs().id))
    this.shapes.set(this.secondSelectedObject.getAttrs().id, this.stage.findOne("#" + this.secondSelectedObject.getAttrs().id))
    console.log(this.shapes);
    // console.log(this.stage.findOne("#" + this.secondSelectedObject.getAttrs().id));
    console.log(line);
    this.layer.add(line);
  }

  calculateLine(from:any, to:any){
    const dx = to.getAttrs().x - from.getAttrs().x;
    const dy = to.getAttrs().y - from.getAttrs().y;
    let radius = 40;
    let angle = Math.atan2(-dy, dx);
    return [this.firstSelectedObject.getAttrs().x + -radius * Math.cos(angle + Math.PI)*1.5,
      this.firstSelectedObject.getAttrs().y + radius * Math.sin(angle + Math.PI)*1.5,
      this.secondSelectedObject.getAttrs().x + -radius * Math.cos(angle)*1.5,
      this.secondSelectedObject.getAttrs().y + radius * Math.sin(angle)*1.5]
  }

  startSimulation(){
    this.adapter.startSimulation(this.shapes, this.numberOfProducts);  
    this.replayStarted = false;
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
