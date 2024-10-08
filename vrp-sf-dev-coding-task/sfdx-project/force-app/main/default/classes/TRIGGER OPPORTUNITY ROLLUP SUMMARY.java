trigger OpportunityTrigger on Opportunity (after insert, after update, after delete, after undelete) {
    Set<Id> accountIds = new Set<Id>();

    if (Trigger.isInsert || Trigger.isUpdate || Trigger.isUndelete) {
        for (Opportunity opp : Trigger.new) {
            accountIds.add(opp.AccountId);
        }
    }

    if (Trigger.isDelete) {
        for (Opportunity opp : Trigger.old) {
            accountIds.add(opp.AccountId);
        }
    }

    if (!accountIds.isEmpty()) {
        OpportunityTriggerHandler.updateAccountSales(accountIds);
    }
}
