import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertController, NavController, ToastController } from '@ionic/angular';

@Component({
  selector: 'app-operarios',
  templateUrl: './operarios.page.html',
  styleUrls: ['./operarios.page.scss'],
})
export class OperariosPage implements OnInit {

  public resultados : any = null;
  private autenticacion = '';
  public URLservidor: String;
  //Si no encuentra URL en la cookie usara la siguiente URL
  public URLSecundaria: String =  'https://asid-sistema-checklist.herokuapp.com';

  constructor(private http: HttpClient, private router: Router,
    public alertController: AlertController, public navCtrl: NavController, 
    public toastController: ToastController) { }

  ngOnInit() {
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
    this.listarOperarios();
 }

  listarOperarios(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept':  'application/json;profile=urn:org.apache.isis/v1',
        'Authorization': 'Basic ' + this.autenticacion,
      })
    }
    const URL = this.URLservidor+'/restful/services/Operario/actions/listAll/invoke';
    this.http.get(URL, httpOptions)
    .subscribe((resultados : Array<any>) => {
      var array = resultados;
      array.pop();
      this.resultados = array;
    });

  }

  goDetOp(id_op) { 
    this.router.navigate(['/ver-op', { idOp: id_op }])
  }


filterItemsOfType(){
 return this.resultados.filter(resultado => resultado.titulo != null);
}

async alertaLogOut() {
  const alert = await this.alertController.create({
    header: 'Cerrar Sesión',
    message: '¿Esta seguro que desea cerrar sesión?',
    buttons: [
      {
        text: 'Cancelar',
        role: 'cancel',
        cssClass: 'secondary',
        handler: (blah) => {
          console.log('Apreto boton cancelar');
        }
      }, {
        text: 'Si, salir',
        handler: () => {
          console.log('Apreto boton Salir');
          this.navCtrl.navigateRoot('/login');
        }
      }
    ]
  });

  await alert.present();
}

}
