package Observers;

public interface ISubscriber<UpdateType> {
    /**
     * Inform the subscriber some type of update
     */
    public abstract void giveUpdate(UpdateType update);
}
