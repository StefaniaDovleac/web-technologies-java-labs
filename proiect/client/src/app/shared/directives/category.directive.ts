import { Directive } from '@angular/core';
import { DxiColumnComponent } from 'devextreme-angular/ui/nested';

@Directive({
  selector: '[category]'
})
export class CategoryDirective {

  constructor(private el: DxiColumnComponent) {
    this.el.lookup = {
      dataSource: [{
        createdBy: 1,
        userName: "Stefania"
      },
      {
        createdBy: 0,
        userName: "aaa"
      },
      ],
      displayExpr: "userName",
      valueExpr: "createdBy"
    }
  }

}
