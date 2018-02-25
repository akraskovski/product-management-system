import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class NotificationService {
    private subject = new Subject<any>();

    constructor() {
    }

    success(message: string) {
        this.subject.next({type: 'success', text: message});
    }

    warning(message: string) {
        this.subject.next({type: 'warning', text: message});
    }

    error(message: string) {
        this.subject.next({type: 'error', text: message});
    }

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }
}