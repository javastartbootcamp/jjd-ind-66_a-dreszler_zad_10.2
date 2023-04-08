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
    void sendSms() {
        double balanceAfterSend = accountBalance - smsCost;
        if (doubleEqualToZero(balanceAfterSend)) {
            accountBalance = 0;
            sentSmsCount++;
        } else if (balanceAfterSend > 0) {
            accountBalance = balanceAfterSend;
            smsSentPrompt();
            sentSmsCount++;
        } else {
            smsSendFailureZeroBalancePrompt();
        }
    }

    @Override
    void call(int seconds) {
        double balanceAfterCall = accountBalance - getCallCostPerSecond() * seconds;

        if (doubleEqualToZero(accountBalance)) {
            callFailureZeroBalancePrompt();
        } else if (doubleEqualToZero(balanceAfterCall)) {
            callSecondsCount += seconds;
            accountBalance = 0;
            callSuccessfulPrompt(seconds);
        } else if (balanceAfterCall > 0) {
            callSecondsCount += seconds;
            accountBalance = balanceAfterCall;
            callSuccessfulPrompt(seconds);
        } else {
            int duration = getSecondsOfCallAvailableForCurrentBalance();
            callInterruptedZeroBalancePrompt(duration);
            callSecondsCount += duration;
            accountBalance = 0;
        }
    }

    @Override
    void sendMms() {
        double balanceAfterSend = accountBalance - mmsCost;
        if (doubleEqualToZero(balanceAfterSend)) {
            accountBalance = 0;
            mmsSentPrompt();
            sentMmsCount++;
        } else if (balanceAfterSend > 0) {
            accountBalance = balanceAfterSend;
            mmsSentPrompt();
            sentMmsCount++;
        } else {
            mmsSendFailureZeroBalancePrompt();
        }
    }

    @Override
    void printAccountState() {
        final String NO_ADDITIONAL_PLAN_INFO = "";
        System.out.println(getAccountState(NO_ADDITIONAL_PLAN_INFO));
    }

    protected String getAccountState(String additionalPlanInfo) {
        return super.getAccountState() + additionalPlanInfo + getRemainingAccountBalance();
    }

    protected String getRemainingAccountBalance() {
        return String.format("Na koncie zostało %.2f zł\n", accountBalance);
    }

    protected double getCallCostPerSecond() {
        return callCostPerMinute / 60;
    }

    protected int getSecondsOfCallAvailableForCurrentBalance() {
        return (int) Math.round(accountBalance / getCallCostPerSecond());
    }
}
