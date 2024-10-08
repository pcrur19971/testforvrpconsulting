// accountContactWrapper.js
import { LightningElement } from 'lwc';

export default class AccountContactWrapper extends LightningElement {
    selectedAccountId;

    handleAccountSelected(event) {
        this.selectedAccountId = event.detail;
    }
}
