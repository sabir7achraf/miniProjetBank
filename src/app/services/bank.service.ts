import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CreateCompteRequest} from './models/create-compte-request.model';
import {Employee} from './models/Employee';
import {Client} from './models/client.model';
import {Groupe} from './models/groupe.model';
import {EmpToGrpRequest} from './models/emp-to-grp-request.model';
import {VirmentRequest} from './models/virment-request.model';
import {Compte} from './models/compte.model';
import {OperationRequest} from './models/operation-request.model';
@Injectable({
  providedIn: 'root'
})
export class BankService {
  private baseUrl = 'http://localhost:8085';

  constructor(private http: HttpClient) { }

  createCompte(request: CreateCompteRequest): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/createCompte`, request);
  }

  createEmploye(employe: Employee): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/createEmploye`, employe);
  }

  createClient(client: Client): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/createClient`, client);
  }

  createGroup(group: Groupe): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/createGroup`, group);
  }

  affecterEmpToGrp(request: EmpToGrpRequest): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/affecterEmpToGrp`, request);
  }

  versement(request: OperationRequest): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/versement`, request);
  }

  retrait(request: OperationRequest): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/retrait`, request);
  }

  virment(request: VirmentRequest): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/virment`, request);
  }

  consulterCompte(id: number): Observable<Compte> {
    return this.http.get<Compte>(`${this.baseUrl}/compte/${id}`);
  }

  consulterComptes(clientId: number): Observable<Compte[]> {
    return this.http.get<Compte[]>(`${this.baseUrl}/comptes/${clientId}`);
  }
}
