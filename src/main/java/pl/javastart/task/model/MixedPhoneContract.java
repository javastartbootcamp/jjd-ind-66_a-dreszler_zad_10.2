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
    boolean sendSms() {
        if (smsLimit > 0) {
            smsLimit--;
            sentSmsCount++;
            return true;
        } else {
            return super.sendSms();
        }
    }

    @Override
    boolean sendMms() {
        if (mmsLimit > 0) {
            mmsLimit--;
            sentMmsCount++;
            return true;
        } else {
            return super.sendMms();
        }
    }

    @Override
    boolean call(int seconds) {
        double callLengthInMinutes = convertSecondsToMinutes(seconds);
        if (minuteLimit > callLengthInMinutes) {
            minuteLimit -= callLengthInMinutes;
            callSecondsCount += seconds;
            return true;
        } else {
            int minuteLimitInSeconds = convertMinutesToSeconds(minuteLimit);
            int secondsRemaining = seconds - minuteLimitInSeconds;
            callSecondsCount += minuteLimitInSeconds;
            minuteLimit = LIMIT_USED_UP;
            return super.call(secondsRemaining);
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

    private double minuteLimitInSeconds() {
        return convertMinutesToSeconds(minuteLimit);
    }

    @Override
    int getSecondsOfCallAvailableForCurrentBalance() {
        return super.getSecondsOfCallAvailableForCurrentBalance() + convertMinutesToSeconds(minuteLimit);
    }

    @Override
    boolean wasCallInterrupted(int seconds) {
        return seconds > minuteLimitInSeconds() + getSecondsOfCallAvailableForCurrentBalance();
    }

    @Override
    int getCallDuration(int seconds) {
        if (seconds >= minuteLimit + getSecondsOfCallAvailableForCurrentBalance()) {
            return seconds;
        } else {
            return getSecondsOfCallAvailableForCurrentBalance();
        }
    }

    @Override
    protected String getAccountState() {
        String limitInfo = String.format("Darmowe SMSy: %d\nDarmowe MMSy: %d\nDarmowe minuty: %.2f", smsLimit, mmsLimit,
                minuteLimit);
        return super.getAccountState(limitInfo);
    }
}
