import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pessoa } from '../pessoa';
import { PessoaService } from '../pessoa.service';

enum SexoType {
  Masculino = 'M',
  Feminino = 'F'
}

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  eSexoType = SexoType;
  pessoa = new Pessoa('', '', '', new Date(0), '', '', '');
  message: any;

  constructor(
    private service: PessoaService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.editPerson(params.id);
    });
  }

  editPerson(id) {
    if (id) {
      this.service.getPersonById(id).subscribe((data) => {
        this.pessoa = data;
        this.pessoa.dataNascimento = this.formatDate(this.pessoa.dataNascimento);
      },
      (err) => {
        alert('Ocorreu um erro: ' + err.error.message);
        console.log(err);
      });
    }
  }

  savePerson() {
    const response = this.service.putPerson(this.pessoa);
    response.subscribe((data) => {
      this.message = data;
      this.router.navigate(['/list']);
    },
    (err) => {
      alert('Ocorreu um erro: ' + err.error.message);
      console.log(err);
    });
  }

  formatDate(date) {
    const year = date[0];
    let newDate;
    let month = date[1];
    let day = date[2];
    if (date[1] < 10) {
      month = '0' + month;
    }
    if (day < 10) {
      day = '0' + day;
    }
    newDate = year + '-' + month + '-' + day;
    return newDate;
  }

}
