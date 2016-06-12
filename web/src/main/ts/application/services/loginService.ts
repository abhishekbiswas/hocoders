import { Injectable, EventEmitter }    from '@angular/core';
import {Router} from '@angular/router-deprecated';
import {HttpService} from "./httpService";
import {URI} from "../resources/Uri";
import {HttpRequestType} from "../resources/httpRequestType";

@Injectable()
export class LoginService {

    public userAuthenticated$: EventEmitter<boolean>;

    constructor(private httpService: HttpService, private router: Router) {
        this.userAuthenticated$ = new EventEmitter<boolean>();
    }

    login(username: string, password: string): Promise<any> {
        return new Promise((resolve, reject) => {

            if(username==="admin" && password==="admin"){
                this.router.navigateByUrl('/admindashboard');
                this.userAuthenticated$.emit(true);
                console.log("Admin logged in");
                (<any>jQuery('#loginModal')).modal('hide');
            }
            else if(username==="user" && password==="user"){
                console.log("User logging in");
                (<any>jQuery('#loginModal')).modal('hide');
            }
            else{
                console.log("Authentication failed!");
                (<any>jQuery('#loginModal')).modal('hide');
            }

            var loginInformation = { "username": username, "password": password };

            /*this.httpService.executeHttpRequest(URI.AUTHENTICATION, loginInformation, HttpRequestType.POST)
                .then((result) => {
                    console.log("Successfully authenticated!");
                    this.userAuthenticated$.emit(true);
                },
                (error) => {
                    console.log("Failed to authenticate: " + error);
                });*/

        })
    }
}