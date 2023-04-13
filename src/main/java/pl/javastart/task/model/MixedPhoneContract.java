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
    boolean canSendSms() {
        if (smsLimit > 0) {
            smsLimit--;
            sentSmsCount++;
            return true;
        } else {
            return super.canSendSms();
        }
    }

    @Override
    boolean canSendMms() {
        if (mmsLimit > 0) {
            mmsLimit--;
            sentMmsCount++;
            return true;
        } else {
            return super.canSendMms();
        }
    }

    @Override
    int getCallDuration(int seconds) {
        double callLengthInMinutes = convertSecondsToMinutes(seconds);
        int duration = 0;

        if (minuteLimit >= callLengthInMinutes) {
            minuteLimit -= callLengthInMinutes;
            callSecondsCount += seconds;
            duration = seconds;
        } else {
            int minuteLimitInSeconds = convertMinutesToSeconds(minuteLimit);
            int secondsRemaining = seconds - minuteLimitInSeconds;

            callSecondsCount += minuteLimitInSeconds;
            duration = minuteLimitInSeconds + super.getCallDuration(secondsRemaining);
            minuteLimit = LIMIT_USED_UP;
        }

        return duration;
    }

    @Override
    protected String getAccountState() {
        String limitInfo = String.format("Darmowe SMSy: %d\nDarmowe MMSy: %d\nDarmowe minuty: %.2f\n", smsLimit, mmsLimit,
                minuteLimit);
        return super.getAccountState(limitInfo);
    }
}