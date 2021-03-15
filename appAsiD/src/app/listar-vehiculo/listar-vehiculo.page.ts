import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-vehiculo',
  templateUrl: './listar-vehiculo.page.html',
  styleUrls: ['./listar-vehiculo.page.scss'],
})
export class ListarVehiculoPage implements OnInit {

  public resultadosV : any = null;
  private autenticacion = '';
  public URLservidor: String;
  //Si no encuentra URL en la cookie usara la siguiente URL
  public URLSecundaria: String =  'https://asid-sistema-checklist.herokuapp.com';

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
    // if(window.localStorage.autenticacion){
    //   this.autenticacion = window.localStorage.autenticacion;
    // }
    // this.listarVehiculos();
  }

  ionViewWillEnter(){
    if(window.localStorage.autenticacion){
      this.autenticacion = window.localStorage.autenticacion;
    }
    if(window.localStorage.URLservidor){
      this.URLservidor = window.localStorage.URLservidor;
    }else{ 
      this.URLservidor = this.URLSecundaria;
    }
    this.listarVehiculos();
 }

  listarVehiculos(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept':  'application/json;profile=urn:org.apache.isis/v1',
        //'Authorization': 'Basic c3ZlbjpwYXNz',
        'Authorization': 'Basic ' + this.autenticacion,
      })
    }
    const URL = this.URLservidor+'/restful/services/Vehiculo/actions/listAll/invoke';
    this.http.get(URL, httpOptions)
    .subscribe((resultados : Array<any>) => {
      var array = resultados;
      array.pop();
      this.resultadosV = array;
    });

  }

  goDetVeh(id_veh) { 
    this.router.navigate(['/vehiculo', { idVeh: id_veh }])
  }


filterItemsOfType(){
 return this.resultadosV.filter(resultado => resultado.titulo != null);
}


}
