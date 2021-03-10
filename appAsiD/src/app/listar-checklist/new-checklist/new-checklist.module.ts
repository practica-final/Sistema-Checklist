import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NewChecklistPageRoutingModule } from './new-checklist-routing.module';

import { NewChecklistPage } from './new-checklist.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NewChecklistPageRoutingModule
  ],
  declarations: [NewChecklistPage]
})
export class NewChecklistPageModule {}
