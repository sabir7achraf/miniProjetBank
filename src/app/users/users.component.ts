import {Component, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {Client} from '../services/models/client.model';
import {ClientService} from '../services/client.service';
import {NgForOf} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [RouterLink, NgForOf,HttpClientModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit{
  Clients: Client[] = [];

  constructor(private ClientService: ClientService) {}

  ngOnInit() {
    this.ClientService.getClients().subscribe((data: Client[]) => {
      this.Clients = data;
    });
  }

}
