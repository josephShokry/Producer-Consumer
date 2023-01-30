import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Dto } from './dto';

@Injectable({
  providedIn: 'root'
})
export class ControllerService {

  configUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }
  
  startSimulation(dto: Dto){
    return this.http.post<Dto>(`${this.configUrl}/start_simulate`, dto);
  }
  
  getUpdate(){
    return this.http.get<Dto>(`${this.configUrl}/get_changes`);
  }
  
  incrementProduct(){
    return this.http.put<Dto>(`${this.configUrl}/add_product`, null);
  }

}
