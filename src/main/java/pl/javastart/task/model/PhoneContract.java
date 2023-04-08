package pl.javastart.task.model;

public abstract class PhoneContract {
    protected int sentSmsCount;
    protected int sentMmsCount;
    protected int callSecondsCount;

    abstract void sendSms();

    abstract void call(int seconds);

    abstract void sendMms();

    abstract void printAccountState();

    protected String getAccountState() {
        return "=== STAN KONTA ===\n" + "Wysłanych SMSów: " + sentSmsCount + "\nWysłanych MMSów: " + sentMmsCount +
                "\nLiczba sekund rozmowy: " + callSecondsCount + "\n";
    }

    protected void smsSentPrompt() {
        System.out.println("SMS wysłany");
    }

    protected void smsSendFailureZeroBalancePrompt() {
        System.out.println("Nie udało się wysłać SMSa - brak środków");
    }

    protected void mmsSentPrompt() {
        System.out.println("MMS wysłany");
    }

    protected void mmsSendFailureZeroBalancePrompt() {
        System.out.println("Nie udało się wysłać MMSa - brak środków");
    }

    protected void callFailureZeroBalancePrompt() {
        System.out.println("Nie można wykonać połączenia - brak środków");
    }

    protected void callSuccessfulPrompt(int seconds) {
        System.out.println("Wykonano połączenie. Połączenie trwało " + seconds + " sekund");
    }

    protected void callInterruptedZeroBalancePrompt(int duration) {
        System.out.println("Przerwano połączenie - brak środków. Połączenie trwało " + duration + "sekund");
    }

    //Utilsy do przeliczeń - nie wiem czy nie powinny być gdzieś wydzielone

    protected boolean areDoublesEqual(double d1, double d2) {
        //To dodałem gdy się okazało że gdy po następnym smsie będzie 0 na koncie, to pokazywało że nie ma środków
        double epsilon = 0.000001d;
        return Math.abs(d1 - d2) < epsilon;
    }

    protected double roundToTwoDigits(double number) {
        return (int) (number * 100) / 100.0;
    }

    protected double convertSecondsToMinutes(int seconds) {
        return seconds / 60.0;
    }

    protected int convertMinutesToSeconds(double minutes) {
        return (int) Math.round(minutes * 60);
    }
}
