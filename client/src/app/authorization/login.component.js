"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var user_1 = require("../model/user");
var LoginComponent = (function () {
    function LoginComponent(loginService, router) {
        this.loginService = loginService;
        this.router = router;
    }
    LoginComponent.prototype.ngOnInit = function () {
        this.user = new forms_1.FormGroup({
            username: new forms_1.FormControl('', forms_1.Validators.required),
            password: new forms_1.FormControl('', forms_1.Validators.required)
        });
        this.loginService.logout();
    };
    LoginComponent.prototype.onSubmit = function () {
        this.authenticate();
    };
    LoginComponent.prototype.authenticate = function () {
        var _this = this;
        this.loginService.login(new user_1.User(this.user.value.username, this.user.value.password))
            .subscribe(function (result) {
            if (result === true) {
                console.log("You're logged in!");
                _this.router.navigate(['/secured']);
            }
        });
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'login-component',
            templateUrl: './login.component.html'
        })
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
