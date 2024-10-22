package helper;

public class Pause {
    public static void pauseSelection( int seg){
        int mili = seg * 1000;
        System.out.print("");
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pauseLineBreak( int seg){
        int mili = seg * 1000;
        System.out.println();
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void halfPause( int halfSeg){
        int mili = halfSeg * 500;
        System.out.print("");
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void dotsPause(){
        System.out.print(".");
        halfPause(1);
        System.out.print(".");
        halfPause(1);
        System.out.print(".");
    }

    public static void dotsLineBreak(){
        System.out.print(".");
        halfPause(1);
        System.out.print(".");
        halfPause(1);
        System.out.println(".");
    }
}
