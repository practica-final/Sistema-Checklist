import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-vehiculo-detail',
  templateUrl: './vehiculo-detail.page.html',
  styleUrls: ['./vehiculo-detail.page.scss'],
})
export class VehiculoDetailPage implements OnInit {

  idVeh;
  vehData;
  param : any;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute,) {}

  ngOnInit() {
    this.param = this.activatedRoute.snapshot.params;
    if (Object.keys(this.param).length) {
			this.listarVeh(this.param.idVeh);
		}
  }

  listarVeh(idVeh) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic c3ZlbjpwYXNz',
      })
    }
    const URL = 'http://localhost:8080/restful/objects/dominio.Vehiculo/' + idVeh;
    this.http.get(URL, httpOptions)
      .subscribe((resultados) => {
        this.vehData = resultados;
      });
  }


}
