import { Component, OnInit } from '@angular/core';
import {UserService} from 'src/app/services/user.service';
import {TokenStorageService} from 'src/app/services/token-storage.service';
import {NavComponent} from 'src/app/components/shared/nav/nav.component';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit {
  roles: string[];
  users: any ;
  adminRole: any;
  params: {};
  errorMassage = '';
  constructor(private userService: UserService, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.listAllUsers();
    this.authentication();
  }
  listAllUsers(){
    this.userService.findAll().subscribe(user => {
      console.log(user);
      this.users = user
    });
  }
  authentication(){
    const user = this.token.getUser();
    this.roles = user.roles;
    this.adminRole = this.roles.includes('ROLE_ADMIN');
  }
  updateOrDeleteRole(username: string, method: string, role: string){
  this.params = {username, method, role};
    this.userService.updateRole(this.params).subscribe(data => {
      this.users = data
    },error => {
      this.errorMassage = 'You do not have permission to perform this operation!'
    });
  }
  delete(username: string){
    this.userService.deleteUser(username).subscribe(data => this.users = data);
  }
}
