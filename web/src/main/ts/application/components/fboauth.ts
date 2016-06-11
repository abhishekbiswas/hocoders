///<reference path="../../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router';

@Component({
    selector: 'fboauth',
    templateUrl: 'templates/fboauth_view.html'
})
export class FbOAuth {
    constructor() {
        console.log("Facebook OAUTH.");
    }
}