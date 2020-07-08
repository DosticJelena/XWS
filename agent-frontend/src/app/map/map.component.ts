import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError, map } from 'rxjs/operators';
import { of, throwError } from 'rxjs';
var Stomp = require('stompjs');

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements AfterViewInit, OnInit {

  title = 'angular-gmap';
  @ViewChild('mapContainer', { static: false }) gmap: ElementRef;
  map: google.maps.Map;
  lat = 0;
  lng = 0;
  geocoder: any;
  address: any;
  public query: string;
  public position: string;
  public locations: Array<any>;
  id = 0;

    


  constructor(private http: HttpClient) { }

  coordinates = new google.maps.LatLng(this.lat, this.lng);

  mapOptions: google.maps.MapOptions = {
    center: this.coordinates,
    zoom: 12,
  };

  marker = new google.maps.Marker({
    position: this.coordinates,
    map: this.map,
  });
  
  ngOnInit() {
    var t = window.location.href.split("/");
    this.id = parseInt(t[t.length-1]);
    console.log(this.id);
    this.changeLocation();
   
    
  }
  
  private async changeLocation(){
    while(1){
    console.log("Usao");
    this.reload().subscribe(
      (data: any) => {          
        var returnValue = Object.assign([], (data));
        this.position = returnValue.gps;
        console.log(this.position);
        this.eventHandler();
        //this.reloadMap();
      }, (error) => alert(error.text)
    );
    await this.delay(10000);}
  }
  
  private delay(ms: number)
{
  return new Promise(resolve => setTimeout(resolve, ms));
}

  ngAfterViewInit() { 
    this.mapInitializer();
  }

  mapInitializer() {
    this.geocoder = new google.maps.Geocoder();
    this.map = new google.maps.Map(this.gmap.nativeElement,
      this.mapOptions);
    this.marker.setMap(this.map);
  }

  eventHandler() {
    this.lat = parseFloat(this.position.split(',')[0]);
    this.lng = parseFloat(this.position.split(',')[1]);
    console.log("Event handler "+ this.lat, this.lng);
    this.coordinates = new google.maps.LatLng(this.lat, this.lng);

    this.mapOptions.center = this.coordinates;

    this.marker.setPosition(this.coordinates);
    this.marker.setMap(this.map);

    this.mapInitializer();
  }
  reloadMap(){
    this.lat = parseFloat(this.position.split(',')[0]);
    this.lng = parseFloat(this.position.split(',')[1]);
    console.log("Event handler "+ this.lat, this.lng);
    this.coordinates = new google.maps.LatLng(this.lat, this.lng);

    this.mapOptions.center = this.coordinates;

    this.marker.setPosition(this.coordinates);
    this.marker.setMap(this.map);
  }

  reload() {
    return this.http.get("http://localhost:8090/vehicle/location/"+this.id)
        .pipe(
            map((res: any) => {
                const data = res;
                return data;
            }),
            catchError((err: any) => {
                console.log(err);
                return throwError(err);
            })
        )
}
}
