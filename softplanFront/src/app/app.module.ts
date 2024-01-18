import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateComponent } from './create/create.component';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';
import { PessoaService } from './pessoa.service';
import { SourceService } from './source/source.service'
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { HeaderComponent } from './header/header.component';
import { BasicAuthHttpInterceptorService } from './service/basic-auth-http-interceptor.service';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import { SourceComponent } from './source/source.component';



@NgModule({
  declarations: [
    AppComponent,
    CreateComponent,
    ListComponent,
    DetailComponent,
    LoginComponent,
    LogoutComponent,
    HeaderComponent,
    SourceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  providers: [
    PessoaService,
    SourceService,
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHttpInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
