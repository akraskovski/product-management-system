import {Component} from '@angular/core';
import '../style/app.css';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent {
    title: string = 'Angular 2 Component';
}
