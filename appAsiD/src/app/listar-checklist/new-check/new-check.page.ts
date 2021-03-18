import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ChecklistService } from '../../services/checklist.service';
import { AlertController, NavController, ToastController } from '@ionic/angular';

@Component({
  selector: 'app-new-check',
  templateUrl: './new-check.page.html',
  styleUrls: ['./new-check.page.scss'],
})
export class NewCheckPage implements OnInit {

  idCheck: any;
  datosCheck = '';
  checkForm: FormGroup;
  private autenticacion = '';

  public URLservidor = 'https://asid-sistema-checklist.herokuapp.com';
  public operarioArray: any = null;
  public vehiculoArray: any = null;

  constructor(private http: HttpClient,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private paramRoute: ActivatedRoute,
    private router: Router,
    private checklistService: ChecklistService,
    public alertController: AlertController, 
    public navCtrl: NavController, public toastController: ToastController) { 

      this.checkForm = this.fb.group({
        dominio: [''],
        usuario: [''],
        identificacion: [''],
        destino: [''],
        fechaDeSalida: [''],
        documentacion: [''],
        tablero: [''],
        laterales: [''],
        seccionTrasera: [''],
        frente: [''],
        comentarios: [''],
        fotos: [''],
      });

    }

    ngOnInit() {
      this.paramRoute.paramMap.subscribe((param) => {
        this.idCheck = param.get('id');
        // this.getChecklist(param.get('id'));
      });
      this.listarOperarios();
      this.listarVehiculos();
    }
  
    // getChecklist(id) {
    //   this.checklistService.getChecklist(id).subscribe((checklists: any) => {
    //     this.datosCheck = checklists;
    //     console.log(checklists);
    //     this.checkForm.patchValue({ dominio: checklists.dominio.href });
    //     console.log(this.checkForm);
    //   });
    // }

    //SELECCIONAR OPERARIO

    onSelectOperario(usuario) {
      console.log(usuario);
      this.checkForm.patchValue({ usuario: usuario.$$href });
      console.log(this.checkForm);
    }
  
    listarOperarios() {
      const httpOptions = {
        headers: new HttpHeaders({
          'Accept': 'application/json;profile=urn:org.apache.isis/v1',
          Authorization: 'Basic c3ZlbjpwYXNz=',
          // Authorization: 'Basic QWRtaW46YWRtaW4=',
          // 'Authorization': 'Basic ' + this.autenticacion,
        }),
      }
      const URL =
        this.URLservidor + '/restful/services/Operario/actions/listAll/invoke';
      this.http.get(URL, httpOptions)
      .subscribe((resultados: Array<any>) => {
        var array = resultados;
        array.pop();
        this.operarioArray = array;
        console.log('array operarios? ',this.operarioArray);
      });
    }

    //SELECCIONAR VEHICULO

    onSelectVehiculo(dominio) {
      console.log(dominio);
      this.checkForm.patchValue({ dominio: dominio.$$href });
      console.log(this.checkForm);
    }
  
    listarVehiculos() {
      const httpOptions = {
        headers: new HttpHeaders({
          'Accept': 'application/json;profile=urn:org.apache.isis/v1',
          Authorization: 'Basic c3ZlbjpwYXNz=',
          // Authorization: 'Basic QWRtaW46YWRtaW4=',
          // 'Authorization': 'Basic ' + this.autenticacion,
        }),
      }
      const URL =
        this.URLservidor + '/restful/services/Vehiculo/actions/listAll/invoke';
      this.http.get(URL, httpOptions)
      .subscribe((resultV: Array<any>) => {
        var array = resultV;
        array.pop();
        this.vehiculoArray = array;
        console.log(this.vehiculoArray);
      });
    }

  
    submit() {
      this.checklistService
        .crearChecklist(this.idCheck, this.checkForm.value)
        .subscribe((checklist) => {
          console.log(checklist);
        });
      this.router.navigate(['/checklist']);
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
