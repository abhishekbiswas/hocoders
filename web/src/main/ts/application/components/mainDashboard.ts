import {Component} from "@angular/core";
import {Auth} from "../services/auth.service";

@Component({
    selector: 'main-dashboard',
    templateUrl: 'templates/main_dashboard.html'
})
export class Dashboard {
    constructor(private auth: Auth) {
    }
}