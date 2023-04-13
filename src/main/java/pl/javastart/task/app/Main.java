package pl.javastart.task.app;

import pl.javastart.task.model.*;

public class Main {

    public static void main(String[] args) {
        PhoneContract cardPhoneContract = new CardPhoneContract(0.6, 0.1, 0.2, 0.3);
        PhoneContract subscriptionPhoneContract = new SubscriptionPhoneContract(200);
        PhoneContract mixedPhoneContract = new MixedPhoneContract(0.6, 0.1, 0.2,
                0.3, 3, 3, 1);
        Phone phone = new Phone(subscriptionPhoneContract);

//        //Test działania smsów
//
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//        phone.sendSms();
//        phone.printAccountState();
//
//        //Test działania połączeń
//        //1
//        phone.printAccountState();
//        phone.call(30);
//        phone.printAccountState();
//        phone.call(30);
//        phone.printAccountState();
//        //2
//        phone.printAccountState();
//        phone.call(60);
//        phone.printAccountState();
//        phone.call(61);
//        phone.printAccountState();
//        phone.call(60);
//        phone.printAccountState();
//        phone.call(30);
//        //3
//        phone.printAccountState();
//        phone.call(250);
//        phone.printAccountState();

//        //Test działania mmsów
//        phone.printAccountState();
//        phone.sendMms();
//        phone.printAccountState();
//        phone.sendMms();
//        phone.printAccountState();
//        phone.sendMms();
//        phone.printAccountState();
//        phone.sendMms();
//        phone.printAccountState();
//        phone.sendMms();
//        phone.printAccountState();
//        phone.sendMms();
//        phone.printAccountState();
//        phone.sendMms();
    }
}