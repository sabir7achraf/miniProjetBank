import { Component, Input} from '@angular/core';
import {NgForOf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  templateUrl: './side-bar.component.html',
  imports: [
    NgForOf,
    RouterLink
  ],
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent {
  @Input() menus: Array<{
    name: string,
    icon: {viewBox: string, path: string},
    route?: string
  }> = [];
}

