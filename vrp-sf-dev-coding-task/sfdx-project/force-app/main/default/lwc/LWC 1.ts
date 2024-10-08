// accountList.js
import { LightningElement, wire } from 'lwc';
import getAccounts from '@salesforce/apex/AccountController.getAccounts';

export default class AccountList extends LightningElement {
    accounts;
    error;

    @wire(getAccounts)
    wiredAccounts({ error, data }) {
        if (data) {
            this.accounts = data;
            this.error = undefined;
        } else if (error) {
            this.error = error;
            this.accounts = undefined;
        }
    }

    handleAccountClick(event) {
        const accountId = event.target.dataset.id;
        const accountSelectedEvent = new CustomEvent('accountselected', {
            detail: accountId
        });
        this.dispatchEvent(accountSelectedEvent);
    }
}
