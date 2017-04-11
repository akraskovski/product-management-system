import { Pipe, PipeTransform } from '@angular/core';
import {TranslateService} from "./translate.service";

@Pipe({
    name: 'translate',
    pure: false
})
export class TranslatePipe implements PipeTransform {

    constructor(private _translate: TranslateService) { }

    transform(value: string): any {
        if (!value) return;
        return this._translate.instant(value);
    }
}