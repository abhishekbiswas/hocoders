///<reference path="../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';

@Component({
    selector: 'my-app',
    template: `
    Hello World!
    `
})
export class AppComponent {
    constructor(){
        console.log("App component initialised.");
    }
}