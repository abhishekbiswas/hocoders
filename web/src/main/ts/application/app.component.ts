///<reference path="../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES, RouteConfig} from '@angular/router-deprecated';
import {NavBar} from './components/navbar';
import {SampleDataService} from "./services/sampleDataService";
import {HttpService} from "./services/httpService";
import {FbOAuth} from "./components/fboauth";
import {Register} from "./components/register";
import {} from '@angular/router-deprecated';
import {Admin} from "./components/admin";
import {MainSummary} from "./components/mainSummary";

@Component({
    selector: 'my-app',
    directives: [NavBar, FbOAuth, ROUTER_DIRECTIVES],
    providers: [HttpService, SampleDataService],
    templateUrl: 'templates/application.html'
})
@RouteConfig([
    { path: '/', name: 'MainSummary', component: MainSummary },
    { path: '/register', name: 'Register', component: Register },
    { path: '/admin', name: 'Admin', component: Admin }
])
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