///<reference path="../../../node_modules/angular2/typings/browser.d.ts"/>

import {bootstrap} from '@angular/platform-browser-dynamic';
import {provide} from '@angular/core';
import {APP_BASE_HREF, LocationStrategy, HashLocationStrategy} from '@angular/common';
import {HTTP_PROVIDERS} from '@angular/http';
import {ROUTER_PROVIDERS} from '@angular/router-deprecated';
import {AppComponent} from './application/app.component';

bootstrap(AppComponent, [ ROUTER_PROVIDERS, HTTP_PROVIDERS, provide(LocationStrategy, {useClass: HashLocationStrategy}),  provide(APP_BASE_HREF, { useValue:'/' })]);