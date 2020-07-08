import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';

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

  host = "ws://localhost:8090/ws/";
  socket: any;


  constructor() { }

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

    try {
      this.socket = new WebSocket(this.host);
      var self = this;
      //console.log('connect: Socket Status: '+ this.socket.readyState);

      this.socket.onopen = function () {
        //console.log('onopen: Socket Status: '+ this.socket.readyState+' (open)');
        //console.log('onopen: LogedUser: '+user+'');
      }

      this.socket.onmessage = function (msg) {
        console.log('onmessage: Received: ' + msg.data);

        
      }

      this.socket.onclose = function () {
        this.socket = null;
      }

    } catch (exception) {
      console.log('Error' + exception);
    }
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
    //this.lat = parseFloat(this.adresa.split('-')[0]);
    //this.lng = parseFloat(this.adresa.split('-')[1]);
    console.log(this.lat, this.lng);
    this.coordinates = new google.maps.LatLng(this.lat, this.lng);

    this.mapOptions.center = this.coordinates;

    this.marker.setPosition(this.coordinates);
    this.marker.setMap(this.map);

    this.mapInitializer();
  }
}
