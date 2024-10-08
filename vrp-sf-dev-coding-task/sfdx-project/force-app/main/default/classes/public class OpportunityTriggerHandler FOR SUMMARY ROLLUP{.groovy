public class OpportunityTriggerHandler {
    public static void updateAccountSales(Set<Id> accountIds) {
        List<Account> accountsToUpdate = new List<Account>();

        // Aggregate standard wood sales
        AggregateResult[] standardSales = [
            SELECT AccountId, SUM(Amount) sumAmount
            FROM Opportunity
            WHERE AccountId IN :accountIds AND Wood_Type__c = 'Standard'
            GROUP BY AccountId
        ];

        // Aggregate recycled wood sales
        AggregateResult[] recycledSales = [
            SELECT AccountId, SUM(Amount) sumAmount
            FROM Opportunity
            WHERE AccountId IN :accountIds AND Wood_Type__c = 'Recycled'
            GROUP BY AccountId
        ];

        Map<Id, Decimal> standardSalesMap = new Map<Id, Decimal>();
        for (AggregateResult ar : standardSales) {
            standardSalesMap.put((Id)ar.get('AccountId'), (Decimal)ar.get('sumAmount'));
        }

        Map<Id, Decimal> recycledSalesMap = new Map<Id, Decimal>();
        for (AggregateResult ar : recycledSales) {
            recycledSalesMap.put((Id)ar.get('AccountId'), (Decimal)ar.get('sumAmount'));
        }

        for (Id accountId : accountIds) {
            Account acc = new Account(Id = accountId);
            acc.Total_Sales_Standard_Wood__c = standardSalesMap.get(accountId) != null ? standardSalesMap.get(accountId) : 0;
            acc.Total_Sales_Recycled_Wood__c = recycledSalesMap.get(accountId) != null ? recycledSalesMap.get(accountId) : 0;
            accountsToUpdate.add(acc);
        }

        if (!accountsToUpdate.isEmpty()) {
            update accountsToUpdate;
        }
    }
}
