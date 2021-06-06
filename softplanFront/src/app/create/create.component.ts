import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from '../pessoa';
import { PessoaService } from '../pessoa.service';

enum SexoType {
  Masculino = 'M',
  Feminino = 'F'
}

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  eSexoType = SexoType;

  pessoa: Pessoa = new Pessoa('', '', '', new Date(0), '', '', '');
  message: any;

  constructor(
    private service: PessoaService,
    private router: Router
    ) { }

  ngOnInit() {

  }

  public savePerson() {
    const response = this.service.createPerson(this.pessoa);
    response.subscribe((data) => {
      this.message = data;
      this.router.navigate(['/list']);
    },
    (err) => {
      alert(err.error.forEach);
      console.log(err);
    });
  }

  editPerson(id) {
    if (id) {
      this.service.getPersonById(id).subscribe((data) => {
        this.pessoa = data;
      },
      (err) => {
        alert('Ocorreu um erro');
        console.log(err);
      });
    }
  }

}
