package pl.javastart.task.model;

public class SubscriptionPhoneContract extends PhoneContract {
    private double serviceCharge;

    public SubscriptionPhoneContract(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Override
    boolean sendSms() {
        sentSmsCount++;
        return true;
    }

    @Override
    boolean call(int seconds) {
        callSecondsCount += seconds;
        return true;
    }

    @Override
    boolean sendMms() {
        sentMmsCount++;
        return true;
    }

    @Override
    protected String getAccountState() {
        return super.getAccountState() + getServiceCharge();
    }

    private String getServiceCharge() {
        return String.format("Kwota abonamentu: %.2f\n", serviceCharge);
    }

    @Override
    int getCallDuration(int seconds) {
        return seconds;
    }

    @Override
    boolean wasCallInterrupted(int seconds) {
        return false;
    }
}
