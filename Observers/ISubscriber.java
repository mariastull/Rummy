package Observers;

// TODO: update in UML to an interface
public interface ISubscriber<UpdateType> {
    /**
     * Inform the subscriber some type of update
     */
    public abstract void giveUpdate(UpdateType update);
}
