///<reference path="../../../../node_modules/angular2/typings/browser.d.ts"/>

import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES, RouteConfig} from '@angular/router-deprecated';
import {NavBar} from './components/navbar';
import {SampleDataService} from "./services/sampleDataService";
import {HttpService} from "./services/httpService";
import {Register} from "./components/register";
import {Admin} from "./components/admin";
import {MainSummary} from "./components/mainSummary";
import {Auth} from "./services/auth.service";

@Component({
    selector: 'my-app',
    directives: [NavBar, ROUTER_DIRECTIVES],
    providers: [HttpService, SampleDataService, Auth],
    templateUrl: 'templates/application.html'
})
@RouteConfig([
    { path: '/', name: 'MainSummary', component: MainSummary },
    { path: '/register', name: 'Register', component: Register },
    { path: '/admin', name: 'Admin', component: Admin }
])
export class AppComponent {
    constructor(private sampleDataService: SampleDataService, private auth: Auth){
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