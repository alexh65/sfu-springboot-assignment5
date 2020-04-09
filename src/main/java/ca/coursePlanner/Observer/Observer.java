package ca.coursePlanner.Observer;

import ca.coursePlanner.wrappers.ApiWatcherWrapper;

public interface Observer {
    void stateChanged(String event);
}