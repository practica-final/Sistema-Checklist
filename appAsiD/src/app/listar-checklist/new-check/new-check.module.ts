import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NewCheckPageRoutingModule } from './new-check-routing.module';

import { NewCheckPage } from './new-check.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    NewCheckPageRoutingModule
  ],
  declarations: [NewCheckPage]
})
export class NewCheckPageModule {}
