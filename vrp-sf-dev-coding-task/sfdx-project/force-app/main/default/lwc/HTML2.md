<!-- contactList.html -->
<template>
    <lightning-card title="Contact List">
        <template if:true={contacts}>
            <ul>
                <template for:each={contacts} for:item="contact">
                    <li key={contact.Id}>{contact.Name}</li>
                </template>
            </ul>
        </template>
        <template if:true={error}>
            <p>{error}</p>
        </template>
    </lightning-card>
</template>
