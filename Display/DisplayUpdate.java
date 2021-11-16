package Display;
import Observers.Update;

public enum DisplayUpdate { //extends Update {
    ShowStartButton,
    HandUpdated,
    DiscardUpdated,
    ShowIsComputerTurn,
    ShowIsPlayerTurn,
    AskForGrabChoice,
    AskForDiscardChoice,
    ShowWin,
    ShowLoss
}