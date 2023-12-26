package ru.netology.maksimmosiaev.entity;

public class CashbackOperation extends Operation {
    private int cashbackAmount;

    public CashbackOperation(Integer id, Integer sum, Currency currency, String merchant, Integer customerId, int cashbackAmount) {
        super(id, sum, currency, merchant, customerId);
        this.cashbackAmount = cashbackAmount;
    }

    public int getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(int cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    @Override
    public String toString() {
        return "Operation{ id = " + getId() +
                ", sum: " + getSum() +
                ", currency: " + getCurrency() +
                ", merchant: " + getMerchant() +
                ", cashbackAmount " + cashbackAmount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }


        CashbackOperation operation = (CashbackOperation) o;
        return getId() == operation.getId()
                && (getSum() == operation.getSum()
                && cashbackAmount == operation.cashbackAmount
                && (getCurrency() != null && getCurrency().equals(operation.getCurrency()))
                && (getMerchant() != null && getMerchant().equals(operation.getMerchant())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;
        res = prime * res + getId();
        res = prime * res + getSum();
        res = prime * res + cashbackAmount;
        res = prime * res + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        res = prime * res + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        return res;
    }

    @Override
    public void printToConsole() {
        System.out.println("Твой id: " + getId() + "." +
                " Сумма операции, которую ты совершил " + getSum() +
                getCurrency() + "." +
                " Оператор, на которого была совершена операция" + getMerchant() + "." +
                " Сумма полученного кэшбэка " + cashbackAmount + ".");
    }
}
