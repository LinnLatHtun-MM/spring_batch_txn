package per.llt.spring_batch_txn.model;

public enum TransactionDescription {

    FUND_TRANSFER("FUND TRANSFER"),
    ATM_WITHDRAWAL("ATM WITHDRWAL"),
    THIRD_PARTY_FUND_TRANSFER("3rd Party FUND TRANSFER"),
    BILL_PAYMENT("BILL PAYMENT");

    private final String description;

    TransactionDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
