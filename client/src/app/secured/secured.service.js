"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var environment_1 = require("../constants/environment");
var SecuredService = (function () {
    function SecuredService(http) {
        this.http = http;
    }
    SecuredService.prototype.getMessage = function () {
        var userToken = JSON.parse(localStorage.getItem(environment_1.environment.USER_KEY)).token;
        var headers = new http_1.Headers({ 'x-auth-token': userToken });
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.get(environment_1.environment.SECURED_URL, options)
            .map(function (response) { return response.text(); })
            .catch(this.handleError);
    };
    SecuredService.prototype.handleError = function (error) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    };
    SecuredService = __decorate([
        core_1.Injectable()
    ], SecuredService);
    return SecuredService;
}());
exports.SecuredService = SecuredService;
