import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-vehiculo-detail',
  templateUrl: './vehiculo-detail.page.html',
  styleUrls: ['./vehiculo-detail.page.scss'],
})
export class VehiculoDetailPage implements OnInit {

  idVeh;
  vehData;
  param : any;
  private autenticacion = '';
  public URLservidor: String;
  //Si no encuentra URL en la cookie usara la siguiente URL
  public URLSecundaria: String =  'https://asid-sistema-checklist.herokuapp.com';

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, public toastController: ToastController) {}

  ngOnInit() {
    if(window.localStorage.autenticacion){
      this.autenticacion = window.localStorage.autenticacion;
    }
    if(window.localStorage.URLservidor){
      this.URLservidor = window.localStorage.URLservidor;
    }else{ 
      this.URLservidor = this.URLSecundaria;
    }
    this.param = this.activatedRoute.snapshot.params;
    if (Object.keys(this.param).length) {
			this.listarVeh(this.param.idVeh);
		}
  }

  listarVeh(idVeh) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        // 'Authorization': 'Basic c3ZlbjpwYXNz',
        'Authorization': 'Basic ' + this.autenticacion,
      })
    }
    const URL = this.URLservidor+'/restful/objects/dominio.Vehiculo/' + idVeh;
    this.http.get(URL, httpOptions)
      .subscribe((resultados) => {
        this.vehData = resultados;
      });
  }


}
