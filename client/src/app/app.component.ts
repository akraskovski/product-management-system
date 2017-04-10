import {Component, OnInit} from "@angular/core";
import {AuthorizationService} from "./authorization/authorization.service";
import {Router} from "@angular/router";
import {AuthorityWorker} from "./common/authority-worker";
import {User} from "./model/user";
import {TranslateService} from "./translate/translate.service";

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent extends AuthorityWorker implements OnInit{
    public supportedLanguages: any[];
    welcomeName: string;

    constructor(private router: Router, private authorizationService: AuthorizationService, private translate: TranslateService) {
        super();
    }

    ngOnInit(): void {
        this.supportedLanguages = [
            { display: 'English', value: 'en' },
            { display: 'Русский', value: 'ru' },
        ];
        this.selectLang('en');
    }

    isCurrentLang(lang: string) {
        return lang === this.translate.currentLang;
    }

    selectLang(lang: string) {
        this.translate.use(lang);
    }

    public isLoggedIn(): boolean {
        const user: User = AuthorityWorker.getCurrentUser();
        if (user) {
            this.welcomeName = user.username;
            return true;
        }
        return false;
    }

    logout(): void {
        this.welcomeName = null;
        this.authorizationService.logout();
        this.router.navigate(["/"]);
    }
}