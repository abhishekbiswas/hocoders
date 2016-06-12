///<reference path="../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component, OnInit} from '@angular/core';
import {FORM_DIRECTIVES} from '@angular/common';
import {ROUTER_DIRECTIVES, RouteConfig} from '@angular/router-deprecated';
import {NavBar} from './components/navbar';
import {SampleDataService} from "./services/sampleDataService";
import {HttpService} from "./services/httpService";
import {Register} from "./components/register";
import {AdminDashboard} from "./components/adminDashboard";
import {Dashboard} from "./components/mainDashboard";
import {Auth} from "./services/auth.service";
import {Login} from "./components/login";
import {LoginService} from "./services/loginService";

@Component({
    selector: 'my-app',
    directives: [ROUTER_DIRECTIVES, FORM_DIRECTIVES, Login, Register],
    providers: [HttpService, SampleDataService, Auth, LoginService],
    templateUrl: 'templates/application.html'
})
@RouteConfig([
    { path: '/', name: 'Dashboard', component: Dashboard },
    { path: '/register', name: 'Register', component: Register },
    { path: '/login', name: 'Login', component: Login },
    { path: '/admindashboard', name: 'AdminDashboard', component: AdminDashboard }
])
export class AppComponent implements OnInit {

    authenticated: boolean = false;

    constructor(private sampleDataService: SampleDataService, private auth: Auth, private loginService: LoginService) {
        console.log("App component initialised.");
        loginService.userAuthenticated$.subscribe(status => this.persistUserSession(status));
    }

    ngOnInit() {
        this.sampleDataService.getSampleMessage()
            .then((result) => {
                    console.log("Result retrieved: " + JSON.stringify(result));
                },
                (error) => {
                    console.log("Error: " + error.status);
                });
    }

    private persistUserSession(status:boolean) {
        this.authenticated = status;
    }
}