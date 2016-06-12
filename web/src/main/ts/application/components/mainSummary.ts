import {Component} from "@angular/core";
import {Auth} from "../services/auth.service";

@Component({
    selector: 'main-summary',
    templateUrl: 'templates/main_summary.html'
})
export class MainSummary {
    constructor(private auth: Auth) {
    }
}