package pl.javastart.task.model;

public class Phone {
    private PhoneContract phoneContract;

    public Phone(PhoneContract phoneContract) {
        this.phoneContract = phoneContract;
    }

    public void sendSms() {
        phoneContract.sendSms();
    }

    public void sendMms() {
        phoneContract.sendMms();
    }

    public void call(int seconds) {
        phoneContract.call(seconds);
    }

    public void printAccountState() {
        phoneContract.printAccountState();
    }
}
