import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OperariosPage } from './operarios.page';

const routes: Routes = [
  {
    path: '',
    component: OperariosPage
  },
  {
    path: 'ver-ope',
    loadChildren: () => import('./ver-ope/ver-ope.module').then( m => m.VerOpePageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OperariosPageRoutingModule {}
