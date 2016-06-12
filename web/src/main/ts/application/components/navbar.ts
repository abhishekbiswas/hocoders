///<reference path="../../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router';

import {Login} from './login';
import {Register} from './register';
import {Auth} from "../services/auth.service";

@Component({
    selector: 'navbar',
    directives: [Login, Register, ROUTER_DIRECTIVES],
    templateUrl: 'templates/navbar.html'
})
export class NavBar {
    constructor(private auth: Auth) {
    }
}