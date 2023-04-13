package pl.javastart.task.model;

public class SubscriptionPhoneContract extends PhoneContract {
    private double serviceCharge;

    public SubscriptionPhoneContract(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Override
    boolean canSendSms() {
        sentSmsCount++;
        return true;
    }

    @Override
    int getCallDuration(int seconds) {
        callSecondsCount += seconds;
        return seconds;
    }

    @Override
    boolean canSendMms() {
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
}
