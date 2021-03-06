import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VehiculoDetailPage } from './vehiculo-detail.page';

const routes: Routes = [
  {
    path: '',
    component: VehiculoDetailPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VehiculoDetailPageRoutingModule {}
