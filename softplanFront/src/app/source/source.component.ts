import { Component, OnInit } from '@angular/core';
import { SourceService } from './source.service';

@Component({
  selector: 'app-source',
  templateUrl: './source.component.html',
  styleUrls: ['./source.component.css']
})
export class SourceComponent implements OnInit {

  url: any;

  constructor(private service: SourceService) { }

  ngOnInit() {
    this.getSourceUrl();
  }

  public getSourceUrl() {
    const response = this.service.getSourceURL();
    response.subscribe((res) => {
      this.url = res;
    },
    (err) => {
      alert('Ocorreu um erro');
      console.log(err);
    });
  }
}
