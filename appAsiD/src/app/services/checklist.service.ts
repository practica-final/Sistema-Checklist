import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { toBase64String } from '@angular/compiler/src/output/source_map';
@Injectable({
  providedIn: 'root',
})
export class ChecklistService {

  private autenticacion = '';
  //private temp : Blob;
  private temp : FileReader = new FileReader();
  constructor(private httpClient: HttpClient) {}

  private urlServidor = 'https://asid-sistema-checklist.herokuapp.com';
  
  httpOptions = {
    headers: new HttpHeaders({
      Accept: 'application/json;profile=urn:org.apache.isis/v1',
      Authorization: 'Basic c3ZlbjpwYXNz=',
      // 'Authorization': 'Basic ' + this.autenticacion,
    }),
  };

  private Url = this.urlServidor + '/restful/objects/dominio.Checklist/';

  getChecklist(id: number) {
    console.log('id de getChecklist de checklistService ' + id);
    return this.httpClient.get(this.Url + id, this.httpOptions);
  }
  
  crearChecklist(id, checklist, file) {
    //let image = new Blob([file.stream()], file.type).then(resp => resp)
    let fotos : any = `${file.name}:${file.type}:${file}`;  
    //this.temp = new Blob([file]);
    this.temp.readAsText(file);
    this.temp.onloadend = () => {console.log(this.temp.result);
       
    }
    // fotos = {
    //   fotos_name: file.name, 
    //   fotos_mimetype: file.type,
    //  //fotos_bytes:  JSON.stringify(file.Blob)
    //   fotos_bytes: file
    // }
    
      // "Amelie-0-flowers.jpg:image/jpeg:/9j/4AAQSkZJRgABAQEASABIAAD//gAcY21wMy4xMC4zLjFMcTMgMHg0NmE5YmE5ZgD..."
   
     
    console.log(file);
    
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic c3ZlbjpwYXNz=',
        // 'Authorization': 'Basic dXNlcjE6YWJjMTIz=',
        // 'Authorization': 'Basic ' + this.autenticacion,
      }),

    }
    const crearChecklistUrl =
      this.urlServidor + '/restful/services/Checklist/';

      console.log(checklist);
    let datos = {
      'dominio:': {
        value: { href: checklist.dominio },
      },
      'usuario:': {
        value: { href: checklist.usuario },
      },
      identificacion: {
        value: checklist.identificacion,
      },
      destino: {
        value: checklist.destino,
      },
      fechaDeSalida: {
        value: checklist.fechaDeSalida.substr(0, 10),
      },
      documentacion: {
        value: checklist.documentacion,
      },
      tablero: {
        value: checklist.tablero,
      },
      laterales: {
        value: checklist.laterales,
      },
      seccionTrasera: {
        value: checklist.seccionTrasera,
      },
      frente: {
        value: checklist.frente,
      },
      comentarios: {
        value: checklist.comentarios,
      },
      fotos: {
       // value: "Amelie-0-flowers.jpg:image/jpeg:/9j/4AAQSkZJRgABAQEASABIAAD//gAcY21wMy4xMC4zLjFMcTMgMHg0NmE5YmE5ZgD..."
          value: fotos,
       },
      
    };
    console.log(datos);
    return this.httpClient.post(
      crearChecklistUrl + 'actions/create/invoke',
      //JSON.stringify(datos),
      datos,
      httpOptions
    );
  }
}
