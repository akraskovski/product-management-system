import {OpaqueToken} from '@angular/core';
import {LANG_EN_NAME, LANG_EN_TRANS} from './english-language';
import {LANG_RU_NAME, LANG_RU_TRANS} from "./russian-language";

export const TRANSLATIONS = new OpaqueToken('translations');

const dictionary = {
    [LANG_EN_NAME]: LANG_EN_TRANS,
    [LANG_RU_NAME]: LANG_RU_TRANS,
};

export const TRANSLATION_PROVIDERS = [
    {provide: TRANSLATIONS, useValue: dictionary},
];