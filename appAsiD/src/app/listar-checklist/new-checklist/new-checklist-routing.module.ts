import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewChecklistPage } from './new-checklist.page';

const routes: Routes = [
  {
    path: '',
    component: NewChecklistPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NewChecklistPageRoutingModule {}
