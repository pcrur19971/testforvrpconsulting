// contactList.js
import { LightningElement, api, wire } from 'lwc';
import getContactsByAccount from '@salesforce/apex/ContactController.getContactsByAccount';

export default class ContactList extends LightningElement {
    @api accountId;
    contacts;
    error;

    @wire(getContactsByAccount, { accountId: '$accountId' })
    wiredContacts({ error, data }) {
        if (data) {
            this.contacts = data;
            this.error = undefined;
        } else if (error) {
            this.error = error;
            this.contacts = undefined;
        }
    }
}
