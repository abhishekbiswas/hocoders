import { Injectable }    from '@angular/core';
import {HttpService} from "./httpService";
import {URI} from "../resources/Uri";

@Injectable()
export class SampleDataService {

    constructor(private httpService: HttpService) {
    }

    getSampleMessage(): Promise<any> {
        return this.httpService.executeHttpRequest(URI.SAMPLE_API);
    }
}