describe('login page', () => {
    it('test login', () => {
        browser.get('http://localhost:3000/#/login');
        let username = element(by.id('username'));
        let password = element(by.id('password'));
        let loginButton = element(by.id('loginButton'));
        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/#/store/store-content/');
    });
});