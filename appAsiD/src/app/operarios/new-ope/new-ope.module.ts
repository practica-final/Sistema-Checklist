import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NewOpePageRoutingModule } from './new-ope-routing.module';

import { NewOpePage } from './new-ope.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    NewOpePageRoutingModule
  ],
  declarations: [NewOpePage]
})
export class NewOpePageModule {}
