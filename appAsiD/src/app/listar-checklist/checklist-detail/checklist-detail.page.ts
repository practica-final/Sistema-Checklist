import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastController } from '@ionic/angular';

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
  public URLservidor: String;
  //Si no encuentra URL en la cookie usara la siguiente URL
  public URLSecundaria: String =  'https://asid-sistema-checklist.herokuapp.com';

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, public toastController: ToastController) { }

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
    const URL = this.URLservidor+'/restful/objects/dominio.Checklist/' + idCheck;
    this.http.get(URL, httpOptions)
      .subscribe((resultados) => {
        this.checkData = resultados;
      });
  }

}
