import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-checklist',
  templateUrl: './listar-checklist.page.html',
  styleUrls: ['./listar-checklist.page.scss'],
})
export class ListarChecklistPage implements OnInit {

  public resultadosC : any = null;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.listarChecklist();
  }

  listarChecklist(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept':  'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic c3ZlbjpwYXNz',
      })
    }
    const URL = 'http://localhost:8080/restful/services/Checklist/actions/listAll/invoke';
    this.http.get(URL, httpOptions)
    .subscribe((resultados : Array<any>) => {
      var array = resultados;
      array.pop();
      this.resultadosC = array;
    });

  }

  goDetCheck(id_check) { 
    this.router.navigate(['/checklist', { idCheck: id_check }])
  }


filterItemsOfType(){
 return this.resultadosC  .filter(resultado => resultado.titulo != null);

  }

}
