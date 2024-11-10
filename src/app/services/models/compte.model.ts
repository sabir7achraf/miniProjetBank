import {Client} from './client.model';
import {Employee} from './Employee';

export interface Compte {
  id: number;
  numCompte: string;
  solde: number;
  dateCreation: Date;
  client: Client;
  employe: Employee;

}
