import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListarVehiculoPage } from './listar-vehiculo.page';

const routes: Routes = [
  {
    path: '',
    component: ListarVehiculoPage
  },
  {
    path: 'vehiculo-detail',
    loadChildren: () => import('./vehiculo-detail/vehiculo-detail.module').then( m => m.VehiculoDetailPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListarVehiculoPageRoutingModule {}
