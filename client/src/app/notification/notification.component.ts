import {Component, OnInit} from '@angular/core';
import {NotificationService} from "./notification.service";

@Component({
    selector: 'app-notification',
    templateUrl: './notification.component.html',
    styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {
    message: any;
    showNotification = false;

    constructor(private notificationService: NotificationService) {
    }

    ngOnInit() {
        this.notificationService.getMessage()
            .subscribe(message => {
                this.message = message;
                this.showNotification = true;
                setTimeout(function () {
                    this.showNotification = false;
                }.bind(this), 10000);
            });
    }

    close() {
        this.message = undefined;
        this.showNotification = false;
    }
}