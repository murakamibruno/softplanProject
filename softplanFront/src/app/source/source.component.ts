import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../pessoa.service';

@Component({
  selector: 'app-source',
  templateUrl: './source.component.html',
  styleUrls: ['./source.component.css']
})
export class SourceComponent implements OnInit {

  url: any;

  constructor(private service: PessoaService) { }

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
