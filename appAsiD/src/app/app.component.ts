import { Component } from '@angular/core';
import { AlertController, NavController, ToastController } from '@ionic/angular';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {

  pages = [
    {
      title: 'Home',
      url: '/home',
      icon: 'home'
    },
    {
      title: 'Operario',
      // children:[
      //   {
          // title: 'Ver Operario',
          url: '/operarios',
          icon: 'person-circle'
        // },
        // {
        //   title: 'Crear Operario',
        //   url: '/new-op',
        //   icon: 'add-circle'
        // }
      // ]      
    },
    {
      title: 'Vehiculo',
      url: '/vehiculos',
      icon: 'car'
    },
    {
      title: 'Checklist',
      children: [
        {
          title: 'Ver Checklist',
          url: '/checklist',
          icon: 'clipboard'
        },
        {
          title: 'Crear Check',
          url: '/new-check',
          icon: 'add-circle'
        },
      ]
    }
  ]

  constructor(public alertController: AlertController, public navCtrl: NavController, public toastController: ToastController) {}

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
