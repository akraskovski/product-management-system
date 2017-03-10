"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var UserSearchComponent = (function () {
    function UserSearchComponent(userService) {
        this.userService = userService;
    }
    UserSearchComponent.prototype.loadUserByUsername = function () {
        var _this = this;
        this.userService.getUserByUsername(this.inputText)
            .then(function (user) {
            _this.findUser = user;
            alert("\n                    USERNAME: " + _this.findUser.username + "\n\n                    PASSWORD: " + _this.findUser.password + "\n                ");
        });
    };
    UserSearchComponent = __decorate([
        core_1.Component({
            selector: 'user-search-component',
            templateUrl: './user-search.component.html'
        })
    ], UserSearchComponent);
    return UserSearchComponent;
}());
exports.UserSearchComponent = UserSearchComponent;
