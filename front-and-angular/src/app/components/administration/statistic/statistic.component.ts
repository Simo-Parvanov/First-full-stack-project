import { Component, OnInit } from '@angular/core';
import {UserService} from 'src/app/services/user.service';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {
  stat: {};

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.statistic()
  }
  statistic(){
    this.userService.getStat().subscribe(data => {
      this.stat = {
                    requestCount: data.requestCount,
                    startedOn: data.startedOn}
    })
  }
}
