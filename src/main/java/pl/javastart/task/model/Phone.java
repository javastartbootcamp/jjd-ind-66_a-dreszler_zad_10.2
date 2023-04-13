package pl.javastart.task.model;

public class Phone {
    private PhoneContract phoneContract;

    public Phone(PhoneContract phoneContract) {
        this.phoneContract = phoneContract;
    }

    public void sendSms() {
        if (phoneContract.canSendSms()) {
            smsSentPrompt();
        } else {
            smsSendFailure();
        }
    }

    public void sendMms() {
        if (phoneContract.canSendMms()) {
            mmsSentPrompt();
        } else {
            mmsSendFailure();
        }
    }

    public void call(int seconds) {
        int callDuration = phoneContract.getCallDuration(seconds);

        if (callDuration == seconds) {
            callSuccessfulPrompt(seconds);
        } else if (callDuration == 0) {
            callFailure();
        } else {
            callInterrupted(callDuration);
        }
    }

    public void printAccountState() {
        String accountState = phoneContract.getAccountState();
        System.out.println(accountState);
    }

    private void smsSentPrompt() {
        System.out.println("SMS wysłany");
    }

    private void smsSendFailure() {
        System.out.println("Nie udało się wysłać SMSa - brak środków");
    }

    private void mmsSentPrompt() {
        System.out.println("MMS wysłany");
    }

    private void mmsSendFailure() {
        System.out.println("Nie udało się wysłać MMSa - brak środków");
    }

    private void callFailure() {
        System.out.println("Nie można wykonać połączenia - brak środków");
    }

    private void callSuccessfulPrompt(int seconds) {
        System.out.println("Wykonano połączenie. Połączenie trwało " + seconds + " sekund");
    }

    private void callInterrupted(int duration) {
        System.out.println("Przerwano połączenie - brak środków. Połączenie trwało " + duration + " sekund");
    }
}
