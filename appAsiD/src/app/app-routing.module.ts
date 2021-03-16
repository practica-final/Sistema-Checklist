import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },
  
  
  {
    path: 'listar-vehiculo',
    loadChildren: () => import('./listar-vehiculo/listar-vehiculo.module').then( m => m.ListarVehiculoPageModule)
  },
  {
    path: 'vehiculo',
    loadChildren: () => import('./listar-vehiculo/vehiculo-detail/vehiculo-detail.module').then( m => m.VehiculoDetailPageModule)
  },
  {
    path: 'listar-checklist',
    loadChildren: () => import('./listar-checklist/listar-checklist.module').then( m => m.ListarChecklistPageModule)
  },
  {
    path: 'checklist',
    loadChildren: () => import('./listar-checklist/checklist-detail/checklist-detail.module').then( m => m.ChecklistDetailPageModule)
  },
  {
    path: 'new-check',
    loadChildren: () => import('./listar-checklist/new-check/new-check.module').then( m => m.NewCheckPageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then( m => m.LoginPageModule)
  },

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },


];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
