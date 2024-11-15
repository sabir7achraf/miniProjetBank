import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from './models/client.model';

@Injectable({
    providedIn: 'root'
})
export class ClientService {
   private baseUrl = 'http://localhost:8085';

    constructor(private http: HttpClient) {}
        getClients(): Observable<Client[]> {
            return this.http.get<Client[]>(`${this.baseUrl}/clients`);
        }
    }

