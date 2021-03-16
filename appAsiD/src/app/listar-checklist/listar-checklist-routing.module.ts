import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListarChecklistPage } from './listar-checklist.page';

const routes: Routes = [
  {
    path: '',
    component: ListarChecklistPage
  },
  {
    path: 'checklist-detail',
    loadChildren: () => import('./checklist-detail/checklist-detail.module').then( m => m.ChecklistDetailPageModule)
  },  {
    path: 'new-check',
    loadChildren: () => import('./new-check/new-check.module').then( m => m.NewCheckPageModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListarChecklistPageRoutingModule {}
