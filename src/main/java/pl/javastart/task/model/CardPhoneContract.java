package pl.javastart.task.model;

public class CardPhoneContract extends PhoneContract {
    protected double accountBalance;
    protected double smsCost;
    protected double mmsCost;
    protected double callCostPerMinute;
    protected static final double NO_FUNDS = 0;

    public CardPhoneContract(double accountBalance, double smsCost, double mmsCost, double callCostPerMinute) {
        this.accountBalance = accountBalance;
        this.smsCost = smsCost;
        this.mmsCost = mmsCost;
        this.callCostPerMinute = callCostPerMinute;
    }

    @Override
    boolean sendSms() {
        if (accountBalance >= smsCost) {
            accountBalance -= smsCost;
            sentSmsCount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    boolean call(int seconds) {
        double callCost = getCallCostPerSecond() * seconds;

        if (accountBalance == 0) {
            return false;
        } else if (accountBalance >= callCost) {
            callSecondsCount += getCallDuration(seconds);
            accountBalance -= callCost;
            return true;
        }

        return false;
    }

    @Override
    boolean sendMms() {
        if (accountBalance >= mmsCost) {
            accountBalance -= mmsCost;
            sentMmsCount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String getAccountState() {
        return super.getAccountState() + getRemainingAccountBalance();
    }

    String getAccountState(String additionalPlanInfo) {
        return super.getAccountState() + additionalPlanInfo + getRemainingAccountBalance();
    }

    protected String getRemainingAccountBalance() {
        return String.format("Na koncie zostało %.2f zł\n", accountBalance);
    }

    protected double getCallCostPerSecond() {
        return callCostPerMinute / 60;
    }

    int getSecondsOfCallAvailableForCurrentBalance() {
        return (int) Math.round(accountBalance / getCallCostPerSecond());
    }

    double getCallCost(int seconds) {
        return getCallCostPerSecond() * seconds;
    }

    @Override
    boolean wasCallInterrupted(int seconds) {
        return seconds > getSecondsOfCallAvailableForCurrentBalance();
    }

    @Override
    int getCallDuration(int seconds) {
        if (getSecondsOfCallAvailableForCurrentBalance() >= seconds) {
            return seconds;
        } else {
            return getSecondsOfCallAvailableForCurrentBalance();
        }
    }
}
