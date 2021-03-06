import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-listar-vehiculo',
  templateUrl: './listar-vehiculo.page.html',
  styleUrls: ['./listar-vehiculo.page.scss'],
})
export class ListarVehiculoPage implements OnInit {

  public resultadosV : any = null;

  constructor(private http: HttpClient) { }

  ngOnInit() {

    this.listarVehiculos();
  }

  listarVehiculos(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept':  'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic c3ZlbjpwYXNz',
      })
    }
    const URL = 'http://localhost:8080/restful/services/Vehiculo/actions/listAll/invoke';
    this.http.get(URL, httpOptions)
    .subscribe(resultados => (this.resultadosV = resultados)
    );

  }

filterItemsOfType(){
 return this.resultadosV.filter(resultado => resultado.titulo != null);
}


}
