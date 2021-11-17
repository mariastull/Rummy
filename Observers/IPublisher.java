package Observers;

public interface IPublisher<UpdateType> {

    /**
     * Records the given subscriber so we know who to publish to
     * @param subscriber the new subscriber to add
     */
    public void setSubscriber(ISubscriber<UpdateType> subscriber);

    /**
     * pass along the given update to our subscriber
     */
    public void notifySubscriber(UpdateType update);
}
