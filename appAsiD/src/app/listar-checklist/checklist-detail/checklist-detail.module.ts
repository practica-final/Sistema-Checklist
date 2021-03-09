import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ChecklistDetailPageRoutingModule } from './checklist-detail-routing.module';

import { ChecklistDetailPage } from './checklist-detail.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ChecklistDetailPageRoutingModule
  ],
  declarations: [ChecklistDetailPage]
})
export class ChecklistDetailPageModule {}
