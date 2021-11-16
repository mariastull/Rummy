package Observers;

public interface IPublisher {
    Subscriber subscriber = null;

    public void addSubscriber(Subscriber subscriber);

    public void notifySubscriber();
}
