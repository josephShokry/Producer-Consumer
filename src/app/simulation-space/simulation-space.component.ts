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
  shapes: any = [];

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

  }

  drawMachine(){
    this.stage.add(this.konva.machine())
  }

  drawQueue(){
    this.stage.add(this.konva.queue());
  }

  startSimulation(){
    console.log(this.stage)
  }

}
