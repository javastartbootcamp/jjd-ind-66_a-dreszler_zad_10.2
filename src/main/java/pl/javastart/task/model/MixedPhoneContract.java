package pl.javastart.task.model;

public class MixedPhoneContract extends CardPhoneContract {
    private int smsLimit;
    private int mmsLimit;
    private double minuteLimit;
    private static final double LIMIT_USED_UP = 0;

    public MixedPhoneContract(double accountBalance, double smsCost, double mmsCost, double callCostPerMinute,
                              int smsLimit, int mmsLimit, double minuteLimit) {
        super(accountBalance, smsCost, mmsCost, callCostPerMinute);
        this.smsLimit = smsLimit;
        this.mmsLimit = mmsLimit;
        this.minuteLimit = minuteLimit;
    }

    @Override
    void sendSms() {
        if (smsLimit > 0) {
            smsLimit--;
            sentSmsCount++;
            smsSentPrompt();
        } else {
            super.sendSms();
        }
    }

    @Override
    void sendMms() {
        if (mmsLimit > 0) {
            mmsLimit--;
            sentMmsCount++;
            mmsSentPrompt();
        } else {
            super.sendMms();
        }
    }

    @Override
    void call(int seconds) {
        double callLengthInMinutes = convertSecondsToMinutes(seconds);
        if (minuteLimit > callLengthInMinutes) {
            minuteLimit -= callLengthInMinutes;
            callSecondsCount += seconds;
            super.callSuccessfulPrompt(seconds);
        } else if (areDoublesEqual(minuteLimit, callLengthInMinutes)) {
            minuteLimit = LIMIT_USED_UP;
            callSecondsCount += seconds;
            super.callSuccessfulPrompt(seconds);
        } else {
            int minuteLimitInSeconds = convertMinutesToSeconds(minuteLimit);
            int secondsRemaining = seconds - minuteLimitInSeconds;
            callSecondsCount += minuteLimitInSeconds;
            super.call(secondsRemaining);
            minuteLimit = LIMIT_USED_UP;
        }
//        Stara wersja metody. Zastąpiona ze względu na powtarzalność kodu.
//        if (doubleEqualToZero(minuteLimit)) {
//            super.call(seconds);
//        } else if (minuteLimitAfterCall > 0 || doubleEqualToZero(minuteLimitAfterCall)) {
//            minuteLimit = roundToTwoDigits(minuteLimitAfterCall);
//            callSuccessfulPrompt(seconds);
//        } else {
//            double secondsRemaining = seconds - convertMinutesToSeconds(minuteLimit);
//            double balanceAfterCall = accountBalance - getCallCostPerSecond() * secondsRemaining;
//
//            if (accountBalance == 0) {
//                callSecondsCount += seconds - secondsRemaining;
//                callFailureZeroBalancePrompt();
//            } else if (balanceAfterCall > 0 || doubleEqualToZero(balanceAfterCall)) {
//                callSecondsCount += secondsRemaining;
//                accountBalance = balanceAfterCall;
//                callSuccessfulPrompt(seconds);
//            } else {
//                int duration = getSecondsOfCallAvailableForCurrentBalance();
//                callInterruptedZeroBalancePrompt(duration);
//                callSecondsCount += duration;
//                accountBalance = 0;
//            }
//        }
    }

    @Override
    protected void callSuccessfulPrompt(int seconds) {
        super.callSuccessfulPrompt(seconds + convertMinutesToSeconds(minuteLimit));
    }

    @Override
    protected void callInterruptedZeroBalancePrompt(int duration) {
        super.callInterruptedZeroBalancePrompt(duration + convertMinutesToSeconds(minuteLimit));
    }

    @Override
    void printAccountState() {
        String additionalPlanInfo = "Zostało darmowych SMSów: " + smsLimit + "\nZostało darmowych MMSów: " +
                mmsLimit + "\nZostało darmowych minut: " + minuteLimit + "\n";
        System.out.println(getAccountState(additionalPlanInfo));
    }
}
