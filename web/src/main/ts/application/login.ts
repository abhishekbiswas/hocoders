///<reference path="../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';

@Component({
    selector: 'login',
    templateUrl: 'templates/login.html'
})

export class Login{

    private userName: string;
    private passWord: string;

    tryLogin(){
        console.log("Hi"+ this.userName + " " + this.passWord);
        if(this.userName==="admin" && this.passWord==="pwd"){
            alert("Done Admin");
        }
        else if(this.userName==="user" && this.passWord==="pwd"){
            alert("Done User");
        }
        else{
            alert("Sorry");
        }
    }
}