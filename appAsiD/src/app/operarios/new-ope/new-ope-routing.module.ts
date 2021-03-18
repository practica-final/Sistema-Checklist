import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewOpePage } from './new-ope.page';

const routes: Routes = [
  {
    path: '',
    component: NewOpePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NewOpePageRoutingModule {}
