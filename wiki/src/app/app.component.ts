import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {DataService} from "./services/data.service";

declare var THREE: any;
declare var ModelViewer: any;
declare var JsonModel: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  @ViewChild("modelViewer", {static: false}) modelViewer: ElementRef;

  constructor(private dataService: DataService) {}

  ngAfterViewInit(): void {
    let mv = new ModelViewer(this.modelViewer.nativeElement, {width: 250, height: 250},
      {x: 6, y: 6, z: 12}, {x: 0, y: -4, z: 0});
    mv.hideGrid();

    window.addEventListener('resize', mv.resize);

    this.dataService.getModel('apple').then(data => {
      let model = new JsonModel('apple', JSON.stringify(data), [{name: 'apple', texture: data['texture']}])
      mv.load(model);
    });
  }
}
