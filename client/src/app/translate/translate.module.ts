import {NgModule} from "@angular/core";
import {TranslatePipe} from "./translate.pipe";
import {TRANSLATION_PROVIDERS} from "./translations";
import {TranslateService} from "./translate.service";

@NgModule({
    imports: [],
    declarations: [
        TranslatePipe
    ],
    exports: [
        TranslatePipe
    ]
})
export class TranslateModule {
    static forRoot() {
        return {
            ngModule: TranslateModule,
            providers: [TRANSLATION_PROVIDERS,
                TranslateService],
        };
    }
}