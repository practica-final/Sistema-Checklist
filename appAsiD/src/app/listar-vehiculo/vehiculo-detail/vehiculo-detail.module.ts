import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { VehiculoDetailPageRoutingModule } from './vehiculo-detail-routing.module';

import { VehiculoDetailPage } from './vehiculo-detail.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    VehiculoDetailPageRoutingModule
  ],
  declarations: [VehiculoDetailPage]
})
export class VehiculoDetailPageModule {}
