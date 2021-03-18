import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { OperariosPageRoutingModule } from './operarios-routing.module';

import { OperariosPage } from './operarios.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    OperariosPageRoutingModule
  ],
  declarations: [OperariosPage]
})
export class OperariosPageModule {}
