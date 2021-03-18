import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { VerOpePageRoutingModule } from './ver-ope-routing.module';

import { VerOpePage } from './ver-ope.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    VerOpePageRoutingModule
  ],
  declarations: [VerOpePage]
})
export class VerOpePageModule {}
