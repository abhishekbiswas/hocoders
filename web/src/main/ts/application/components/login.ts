///<reference path="../../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {Router} from '@angular/router-deprecated';

@Component({
    selector: 'login',
    templateUrl: 'templates/login.html'
})
export class Login{

    private userName: string;
    private passWord: string;

    constructor(private router: Router) {
    }

    tryLogin(){
        console.log("Hi"+ this.userName + " " + this.passWord);
        if(this.userName==="admin" && this.passWord==="pwd"){
            this.router.navigate(['Register']);
            console.log("Done Admin");
        }
        else if(this.userName==="user" && this.passWord==="pwd"){
            console.log("Done User");
        }
        else{
            console.log("Sorry");
        }
    }
}