import {Compte} from './compte.model';

export interface CreateCompteRequest{
  compte: Compte;
  clientId: number;
  employeId: number;
}
