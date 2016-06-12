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

    routerOnActivate(){
        (<any>jQuery('#loginModal')).openModal();
    }

    tryLogin(){
        if(this.userName==="admin" && this.passWord==="pwd"){
            this.router.navigateByUrl('/admin');
            console.log("Done Admin");
            (<any>jQuery('#loginModal')).modal('hide');
        }
        else if(this.userName==="user" && this.passWord==="pwd"){
            console.log("Done User");
            (<any>jQuery('#loginModal')).modal('hide');
        }
        else{
            console.log("Sorry");
            (<any>jQuery('#loginModal')).modal('hide');
        }
    }
}