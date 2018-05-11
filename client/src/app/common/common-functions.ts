import {api} from "../constants/api";

export class CommonFunctions {

    static getImageUrl(id: string): string {
        return api.SERVER + 'image/' + id;
    }
}