package Observers;

// TODO: update in UML that it's an interface
public interface ISubscriber<UpdateType> {
    /**
     * Inform the subscriber some type of update
     */
    public abstract void giveUpdate(UpdateType update);
}
