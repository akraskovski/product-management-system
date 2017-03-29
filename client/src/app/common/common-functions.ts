export class CommonFunctions {

    public static cleanAvailableItems(itemList: any[], seletedItems: any[]): any[] {
        let resultItems: any[] = [];
        itemList.forEach(item => {
            if (!this.contains(seletedItems, item))
                resultItems.push(item);
        });
        return resultItems;
    }

    private static contains(array: any[], obj: any): boolean {
        let index = array.length;
        while (index--) {
            if (array[index].id === obj.id) {
                return true;
            }
        }
        return false;
    }
}