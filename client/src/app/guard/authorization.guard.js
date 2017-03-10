"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var environment_1 = require("../constants/environment");
var Permissions = (function () {
    function Permissions() {
    }
    Permissions.prototype.canActivate = function (user, id) {
        return true;
    };
    return Permissions;
}());
var AuthorizationGuard = (function () {
    function AuthorizationGuard(router) {
        this.router = router;
    }
    AuthorizationGuard.prototype.canActivate = function () {
        if (localStorage.getItem(environment_1.environment.USER_KEY)) {
            return true;
        }
        alert('Please login first!');
        this.router.navigate(['/login']);
        return false;
    };
    AuthorizationGuard = __decorate([
        core_1.Injectable()
    ], AuthorizationGuard);
    return AuthorizationGuard;
}());
exports.AuthorizationGuard = AuthorizationGuard;
