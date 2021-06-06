import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa } from '../pessoa';
import { PessoaService } from '../pessoa.service';
import { DataSource } from '@angular/cdk/collections';
import { __values } from 'tslib';
import { Router } from '@angular/router';
import * as moment from 'moment';


@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  displayedColumns: string[] = [
    'nome', 'sexo', 'email', 'dataNascimento', 'naturalidade', 'nacionalidade',
    'cpf', 'dataCadastro', 'dataAtualizacao', 'editar', 'excluir'
  ];


  constructor(
    private service: PessoaService,
    private router: Router
    ) { }

  message: any;
  dataSource: Pessoa[];

  ngOnInit() {
    this.listPeople();
  }


  public listPeople() {
    const response = this.service.listPeople();
    response.subscribe(res => {
      this.dataSource = res;
    },
    (err) => {
      alert('Ocorreu um erro');
      console.log(err);
    });
  }

  public deletePerson(pessoa) {
    if (window.confirm('Deseja excluir o registro de nome ' + pessoa.nome + '?')) {
      this.service.deletePerson(pessoa.id).subscribe(data => {
        alert('Registro ' + pessoa.nome + ' excluído com sucesso');
        window.location.reload();
      },
      (err) => {
        alert('Registro ' + pessoa.nome + ' não excluido');
        console.log(err);
      });
    }
  }

  public editPersonRedirect(pessoa) {
    this.router.navigate(['/detail', {id: pessoa.id} ]);
  }

  public changeSexoDisplay(sexo) {
    switch (sexo) {
      case 'M':
        return 'Masculino';
      case 'F':
        return 'Feminino';
      default :
        return 'Indeterminado';
    }
  }

  public convertTimestampToDate(timestamp) {
    return moment.unix(timestamp);
  }
}
