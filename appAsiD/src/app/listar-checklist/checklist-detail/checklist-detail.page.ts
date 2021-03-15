import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-checklist-detail',
  templateUrl: './checklist-detail.page.html',
  styleUrls: ['./checklist-detail.page.scss'],
})
export class ChecklistDetailPage implements OnInit {

  idCheck;
  checkData;
  param : any;
  private autenticacion = '';

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.param = this.activatedRoute.snapshot.params;
    if (Object.keys(this.param).length) {
			this.listarCheck(this.param.idCheck);
		}
  }

  listarCheck(idCheck) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        // 'Authorization': 'Basic c3ZlbjpwYXNz',
        'Authorization': 'Basic ' + this.autenticacion,
      })
    }
    const URL = 'http://localhost:8080/restful/objects/dominio.Checklist/' + idCheck;
    this.http.get(URL, httpOptions)
      .subscribe((resultados) => {
        this.checkData = resultados;
      });
  }

}
