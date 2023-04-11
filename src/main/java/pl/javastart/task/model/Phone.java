package pl.javastart.task.model;

public class Phone {
    private PhoneContract phoneContract;

    public Phone(PhoneContract phoneContract) {
        this.phoneContract = phoneContract;
    }

    public void sendSms() {
        if (phoneContract.sendSms()) {
            smsSentPrompt();
        } else {
            smsSendFailure();
        }
    }

    public void sendMms() {
        if (phoneContract.sendMms()) {
            mmsSentPrompt();
        } else {
            mmsSendFailure();
        }
    }

    public void call(int seconds) {
        if (phoneContract.call(seconds)) {
            int callDuration = phoneContract.getCallDuration(seconds);
            if (phoneContract.wasCallInterrupted(seconds))
                callInterrupted(callDuration);
            else
                callSuccessfulPrompt(seconds);
        } else {
            callFailure();
        }
    }

    public void printAccountState() {
        System.out.println(phoneContract.getAccountState());
    }

    protected void smsSentPrompt() {
        System.out.println("SMS wysłany");
    }

    protected void smsSendFailure() {
        System.out.println("Nie udało się wysłać SMSa - brak środków");
    }

    protected void mmsSentPrompt() {
        System.out.println("MMS wysłany");
    }

    protected void mmsSendFailure() {
        System.out.println("Nie udało się wysłać MMSa - brak środków");
    }

    protected void callFailure() {
        System.out.println("Nie można wykonać połączenia - brak środków");
    }

    protected void callSuccessfulPrompt(int seconds) {
        System.out.println("Wykonano połączenie. Połączenie trwało " + seconds + " sekund");
    }

    protected void callInterrupted(int duration) {
        System.out.println("Przerwano połączenie - brak środków. Połączenie trwało " + duration + "sekund");
    }
}
