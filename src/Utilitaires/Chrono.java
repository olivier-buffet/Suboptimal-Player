package Utilitaires;

/**
 * Created by Xavier on 17/03/14.
 */
public class Chrono {

    protected long time;
    protected boolean run;

    public Chrono(){}

    public void start(){
        if(!run){
            time=System.currentTimeMillis();
            run=true;
        }
    }

    public void stop(){
        if(run){
            time=System.currentTimeMillis()-time;
            run=false;
        }
    }

    public long getTime(){
        return time;
    }

    public String toString(){
        long ms=time%1000;
        long s=(time/1000)%60;
        long m=(time/60000)%60;
        return m+"'"+s+"\""+((ms<100)?("0"+((ms<10)?"0":"")):"")+ms;
    }

    public static void main(String args[]){
        Chrono c=new Chrono();
        c.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.stop();
        System.out.print(c+"\n"+c.getTime());
    }
}
