///<reference path="../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {NavBar} from './navbar';

@Component({
    selector: 'my-app',
    directives: [NavBar, ROUTER_DIRECTIVES],
    templateUrl: 'templates/application.html'
})
export class AppComponent {
    constructor(){
        console.log("App component initialised.");
    }
}