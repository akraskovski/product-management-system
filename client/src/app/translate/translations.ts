import { OpaqueToken } from '@angular/core';

// import translations
import { LANG_EN_NAME, LANG_EN_TRANS } from './english-language';
import {LANG_RU_NAME, LANG_RU_TRANS} from "./russian-language";

// translation token
export const TRANSLATIONS = new OpaqueToken('translations');

// all traslations
const dictionary = {
    [LANG_EN_NAME]: LANG_EN_TRANS,
    [LANG_RU_NAME]: LANG_RU_TRANS,
};

// providers
export const TRANSLATION_PROVIDERS = [
    { provide: TRANSLATIONS, useValue: dictionary },
];