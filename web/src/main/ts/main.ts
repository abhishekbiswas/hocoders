///<reference path="../../../node_modules/angular2/typings/browser.d.ts"/>

import {bootstrap} from '@angular/platform-browser-dynamic';
import {HTTP_PROVIDERS} from '@angular/http';
import {AppComponent} from './application/app.component';

bootstrap(AppComponent, [ HTTP_PROVIDERS ]);