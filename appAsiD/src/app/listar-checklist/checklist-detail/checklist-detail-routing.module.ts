import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ChecklistDetailPage } from './checklist-detail.page';

const routes: Routes = [
  {
    path: '',
    component: ChecklistDetailPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ChecklistDetailPageRoutingModule {}
