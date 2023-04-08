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
    void sendSms() {
        if (areDoublesEqual(accountBalance, smsCost)) {
            accountBalance = 0;
            smsSentPrompt();
            sentSmsCount++;
        } else if (accountBalance > smsCost) {
            accountBalance -= smsCost;
            smsSentPrompt();
            sentSmsCount++;
        } else {
            smsSendFailureZeroBalancePrompt();
        }
    }

    @Override
    void call(int seconds) {
        double callCost = getCallCostPerSecond() * seconds;

        if (areDoublesEqual(accountBalance, NO_FUNDS)) {
            callFailureZeroBalancePrompt();
        } else if (areDoublesEqual(callCost, accountBalance)) {
            callSecondsCount += seconds;
            accountBalance = 0;
            callSuccessfulPrompt(seconds);
        } else if (accountBalance > callCost) {
            callSecondsCount += seconds;
            accountBalance -= callCost;
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
        if (areDoublesEqual(accountBalance, mmsCost)) {
            accountBalance = 0;
            mmsSentPrompt();
            sentMmsCount++;
        } else if (accountBalance > mmsCost) {
            accountBalance -= mmsCost;
            mmsSentPrompt();
            sentMmsCount++;
        } else {
            mmsSendFailureZeroBalancePrompt();
        }
    }

    @Override
    void printAccountState() {
        final String noAdditionalPlanInfo = "";
        System.out.println(getAccountState(noAdditionalPlanInfo));
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
