import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController, NavController, ToastController } from '@ionic/angular';
import { OperarioService } from 'src/app/services/operario.service';

@Component({
  selector: 'app-new-ope',
  templateUrl: './new-ope.page.html',
  styleUrls: ['./new-ope.page.scss'],
})
export class NewOpePage implements OnInit {

  opForm: FormGroup;
  idOp: any;
  datosOp = '';
  private autenticacion = '';

  public URLservidor = 'https://asid-sistema-checklist.herokuapp.com';

  constructor(private fb: FormBuilder,
    private http: HttpClient,
    private paramRoute: ActivatedRoute,
    private router: Router,
    private operarioService: OperarioService,
    public alertController: AlertController, 
    public navCtrl: NavController, public toastController: ToastController) { 

  this.opForm = this.fb.group({
    nombreYApellido: [''],
    legajoSap: [''],
    correo: [''],
    telefono: [''],
    númeroDeLicencia: [''],
    vencimientoDeLicencia: [''],
    llaveRsv: [''],
    estado: [''],
  });
}
  ngOnInit() {

    this.paramRoute.paramMap.subscribe((param) => {
      this.idOp = param.get('id');
    });
  }

  submit() {
    this.operarioService
      .crearOperario(this.idOp, this.opForm.value)
      .subscribe((operario) => {
        console.log(operario);
      });
    // this.router.navigate(['/operarios']);
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
