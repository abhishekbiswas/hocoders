///<reference path="../../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {Router} from '@angular/router-deprecated';
import {LoginService} from "../services/loginService";

@Component({
    selector: 'login',
    templateUrl: 'templates/login.html'
})
export class Login {

    private username: string;
    private password: string;

    constructor(private router: Router, private loginService: LoginService) {
        console.log("Login loaded...");
    }

    login(){
        console.log("Username: " + this.username + "Password: " + this.password);
        this.loginService.login(this.username, this.password)
            .then((result) => {
                console.log("Raised event!");
            },
                (error) => {
                    console.log("LoginService returned bad response: " + error);
                });
    }
}