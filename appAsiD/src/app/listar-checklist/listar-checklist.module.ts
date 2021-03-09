import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ListarChecklistPageRoutingModule } from './listar-checklist-routing.module';

import { ListarChecklistPage } from './listar-checklist.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ListarChecklistPageRoutingModule
  ],
  declarations: [ListarChecklistPage]
})
export class ListarChecklistPageModule {}
