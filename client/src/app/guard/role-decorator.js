"use strict";
var core_1 = require("@angular/core");
var login_service_1 = require("../authorization/login.service");
exports.Roles = function () {
    var rolesAllowed = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        rolesAllowed[_i - 0] = arguments[_i];
    }
    return CanActivate(function (next, prev) {
        //this would not work if user info was not being kept in session storage
        //as of now it doesn't seem possible to access same-instance application services through non-components
        var injector = core_1.Injector.resolveAndCreate([login_service_1.LoginService, Storage]);
        var authentication = injector.get(login_service_1.LoginService);
        var userRoles = authentication.userRoles;
        return isAllowedAccess(rolesAllowed, userRoles);
    });
};
var isAllowedAccess = function (rolesAllowed, currentRoles) {
    var intersectedRoles = currentRoles.reduce(function (acc, curr) {
        return acc.concat(rolesAllowed.filter(function (role) { return role.trim().toUpperCase() === curr.trim().toUpperCase(); }));
    }, []);
    return intersectedRoles.length > 0;
};
