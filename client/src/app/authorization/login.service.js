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
var LoginService = (function () {
    function LoginService(http) {
        this.http = http;
    }
    LoginService.prototype.login = function (user) {
        var _this = this;
        this.currentUser = user;
        var body = JSON.stringify({ username: user.username, password: user.password });
        var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        var options = new http_1.RequestOptions({ headers: headers });
        return this.http.post(environment_1.environment.LOGIN_URL, body, options)
            .map(function (response) {
            var token = response.json() && response.json().token;
            if (token) {
                _this.currentUser.token = token;
                _this.currentUser.roles = ['ROLE_ADMIN', 'ROLE_STOCK_MANAGER'];
                alert('Welcome, ' + _this.currentUser.username);
                //localStorage.setItem('currentUser', JSON.stringify({username: user.username, token: token}));
                localStorage.setItem(environment_1.environment.USER_KEY, JSON.stringify(_this.currentUser));
                return true;
            }
            else {
                return false;
            }
        });
    };
    LoginService.prototype.logout = function () {
        //this.token = null;
        this.currentUser = null;
        localStorage.removeItem(environment_1.environment.USER_KEY);
    };
    Object.defineProperty(LoginService.prototype, "userRoles", {
        get: function () {
            var user = JSON.parse(localStorage.getItem(environment_1.environment.USER_KEY));
            return user ? user.roles : [];
        },
        enumerable: true,
        configurable: true
    });
    LoginService = __decorate([
        core_1.Injectable()
    ], LoginService);
    return LoginService;
}());
exports.LoginService = LoginService;
