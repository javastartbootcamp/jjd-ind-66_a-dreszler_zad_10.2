package pl.javastart.task.model;

public class SubscriptionPhoneContract extends PhoneContract {
    private double serviceCharge;

    public SubscriptionPhoneContract(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @Override
    void sendSms() {
        sentSmsCount++;
        smsSentPrompt();
    }

    @Override
    void call(int seconds) {
        callSecondsCount += seconds;
        callSuccessfulPrompt(seconds);
    }

    @Override
    void sendMms() {
        sentMmsCount++;
        mmsSentPrompt();
    }

    @Override
    void printAccountState() {
        System.out.println(getAccountState());
    }

    @Override
    protected String getAccountState() {
        return super.getAccountState() + getServiceCharge();
    }

    protected String getServiceCharge() {
        return String.format("Kwota abonamentu: %.2f\n", serviceCharge);
    }
}
