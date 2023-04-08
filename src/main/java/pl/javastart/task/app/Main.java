package pl.javastart.task.app;

import pl.javastart.task.model.*;

public class Main {

    public static void main(String[] args) {
        PhoneContract cardPhoneContract = new CardPhoneContract(0.6, 0.1, 0.2, 0.3);
        PhoneContract subscriptionPhoneContract = new SubscriptionPhoneContract(200);
        PhoneContract mixedPhoneContract = new MixedPhoneContract(0.6, 0.1, 0.2,
                0.3, 3, 3, 1);
        Phone phone = new Phone(mixedPhoneContract);

        phone.printAccountState();
        phone.call(250);
        phone.printAccountState();
    }
}