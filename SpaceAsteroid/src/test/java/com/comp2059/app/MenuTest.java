package com.comp2059.app;

import javafx.application.Application;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


class MenuTest {
    private volatile boolean success = false;
    /**
     * Test that a JavaFX application launches.
     */
    @Test
    public void testMain(){
        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    Application.launch(Menu.class);
                    success = true;
                }catch (Throwable t){
                    if(t.getCause() !=null && t.getCause().getClass().equals(InterruptedException.class)){
                        success = true;
                        return;
                    }
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,null,t);

                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Don't mind if wake up early
            //throw new RuntimeException(e);
        }
        thread.interrupt();
        try{
            thread.join(3000);
        } catch (InterruptedException e) {
            // Don't mind if wake up early
            //throw new RuntimeException(e);
        }
        assertTrue(success);
    }
}