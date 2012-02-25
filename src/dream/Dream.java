// this is the only file you're allowed to edit
package dream;

import sleep.Sleeper;

public class Dream {
    public void dream(final Sleeper s) {
        // remember kids, everything is better when you take a hit of lsd
        final Dream psychodelics = new LSD();
        final Thread hit = new Thread(new Runnable() {
            @Override
            public void run() {
                s.enter(psychodelics);
            }
        });
        hit.setDaemon(true); // we want to terminate
        hit.start(); // first hit's always free
        try {
            // release lock and allow psychodelics to do it's work
            s.wait();
        }
        catch(InterruptedException e) {
            // meh
        }
        
    }
    
    public static class LSD extends Dream {
        @Override
        public void dream(Sleeper s) {
            try {
                // notify on self to release lock on holder
                s.notify();
                // and here's the solution:
                // wait (indefinitely) to ensure we increment, but we don't hit our finally block in sleep's enter method
                // therefore we never get to decrement our counter, but the main Dream will exit normally, decrement
                // and then return a 1
                s.wait();
            }
            catch (InterruptedException e) {
                // meh
            }
        }
    }
}