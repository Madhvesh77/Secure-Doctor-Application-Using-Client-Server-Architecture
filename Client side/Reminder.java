import java.util.*;

public class Reminder 
{
    static Timer timer;

    public Reminder(int seconds) 
    {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	}

    static class RemindTask extends TimerTask 
    {
        public void run() 
        {
            Client.otp[2]+=2;
            Client.otp[3]+=3;
            timer.cancel(); //Terminate the timer thread
        }
    }
}