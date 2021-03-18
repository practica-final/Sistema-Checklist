import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OperarioService {
  private autenticacion = '';

  constructor(private httpClient: HttpClient) {}

  urlServidor = 'https://asid-sistema-checklist.herokuapp.com';
  
  httpOptions = {
    headers: new HttpHeaders({
      Accept: 'application/json;profile=urn:org.apache.isis/v1',
      Authorization: 'Basic c3ZlbjpwYXNz=',
      // 'Authorization': 'Basic ' + this.autenticacion,
    }),
  };

  private Url = this.urlServidor + '/restful/objects/dominio.Operario/';

  getOperario(id: number) {
    console.log('id de getOperario de operarioService ' + id);
    return this.httpClient.get(this.Url + id, this.httpOptions);
  }

  crearOperario(id, operario) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic c3ZlbjpwYXNz=',
      }),
    }
    const crearOperarioUrl =
      this.urlServidor + '/restful/services/Operario/';

      console.log(operario);
    let datos = {

      "nombreYApellido:": {
        value: operario.nombreYApellido,
      },
      "legajoSap:": {
        value: operario.legajoSap,
      },
      "correo:": {
        value: operario.correo,
      },
      "telefono:": {
        value: operario.telefono,
      },
      "númeroDeLicencia:": {
        value: operario.númeroDeLicencia,
      },
      "vencimientoDeLicencia:": {
         value: operario.vencimientoDeLicencia.substr(0, 10),
        //value: operario.vencimientoDeLicencia,
      },
      "llaveRsv:": {
        value: operario.llaveRsv,
      },
      "estado:": {
        value: operario.estado,
      }
    };
    console.log(datos);
    return this.httpClient.post(
      crearOperarioUrl + 'actions/create/invoke',
      JSON.stringify(datos),
      httpOptions
    );
  }  
}
