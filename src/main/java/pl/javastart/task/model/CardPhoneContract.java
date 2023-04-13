package pl.javastart.task.model;

public class CardPhoneContract extends PhoneContract {
    protected double accountBalance;
    protected double smsCost;
    protected double mmsCost;
    protected double callCostPerMinute;

    public CardPhoneContract(double accountBalance, double smsCost, double mmsCost, double callCostPerMinute) {
        this.accountBalance = accountBalance;
        this.smsCost = smsCost;
        this.mmsCost = mmsCost;
        this.callCostPerMinute = callCostPerMinute;
    }

    @Override
    boolean canSendSms() {
        if (accountBalance >= smsCost) {
            accountBalance -= smsCost;
            sentSmsCount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    int getCallDuration(int seconds) {
        double callCost = getCallCostPerSecond() * seconds;
        int duration = 0;

        if (accountBalance >= callCost) {
            duration = seconds;
            callSecondsCount += duration;
            accountBalance -= callCost;
        } else {
            duration = getSecondsOfCallAvailableForCurrentBalance();
            callSecondsCount += duration;
            accountBalance = 0;
        }

        return duration;
    }

    @Override
    boolean canSendMms() {
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
}
