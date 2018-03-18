import {api} from "../constants/api";

export class CommonFunctions {

    public static cleanAvailableItems(itemList: any[], selectedItems: any[]): any[] {
        let resultItems: any[] = [];
        itemList.forEach(item => {
            if (!this.contains(selectedItems, item))
                resultItems.push(item);
        });
        return resultItems;
    }

    static getImageUrl(id: string): string {
        return api.SERVER + 'image/' + id;
    }

    private static contains(array: any[], obj: any): boolean {
        let index = array.length;
        while (index--) {
            if (array[index].id === obj.id)
                return true;
        }
        return false;
    }
}