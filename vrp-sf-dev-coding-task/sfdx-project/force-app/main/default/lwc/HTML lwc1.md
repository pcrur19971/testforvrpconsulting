<!-- accountList.html -->
<template>
    <lightning-card title="Account List">
        <template if:true={accounts}>
            <ul>
                <template for:each={accounts} for:item="account">
                    <li key={account.Id}>
                        <a href="javascript:void(0);" data-id={account.Id} onclick={handleAccountClick}>
                            {account.Name}
                        </a>
                    </li>
                </template>
            </ul>
        </template>
        <template if:true={error}>
            <p>{error}</p>
        </template>
    </lightning-card>
</template>
