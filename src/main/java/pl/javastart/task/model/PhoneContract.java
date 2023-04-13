package pl.javastart.task.model;

public abstract class PhoneContract {
    protected int sentSmsCount;
    protected int sentMmsCount;
    protected int callSecondsCount;

    abstract boolean canSendSms();

    abstract int getCallDuration(int seconds);

    abstract boolean canSendMms();

    protected String getAccountState() {
        return "=== STAN KONTA ===\n" + "Wysłanych SMSów: " + sentSmsCount + "\nWysłanych MMSów: " + sentMmsCount +
                "\nLiczba sekund rozmowy: " + callSecondsCount + "\n";
    }

    protected double convertSecondsToMinutes(int seconds) {
        return seconds / 60.0;
    }

    protected int convertMinutesToSeconds(double minutes) {
        return (int) Math.round(minutes * 60);
    }
}
