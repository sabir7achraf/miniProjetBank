import {Employee} from './Employee';

export interface Groupe {
  id: number;
  nomGroup: string;
  numGroup: number;
  employees: Employee[];
}
