///<reference path="../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {NavBar} from './components/navbar';
import {SampleDataService} from "./services/sampleDataService";
import {HttpService} from "./services/httpService";

@Component({
    selector: 'my-app',
    directives: [NavBar, ROUTER_DIRECTIVES],
    providers: [HttpService, SampleDataService],
    templateUrl: 'templates/application.html'
})
export class AppComponent {
    constructor(private sampleDataService: SampleDataService){
        console.log("App component initialised.");
        this.sampleDataService.getSampleMessage()
            .then((result) => {
                console.log("Result retrieved: " + JSON.stringify(result));
            },
            (error) => {
                console.log("Error: " + error.status);
            });
    }
}