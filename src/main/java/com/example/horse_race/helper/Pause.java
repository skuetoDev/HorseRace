package com.example.horse_race.helper;

public class Pause {

    /**
     * Static metod to select how many seconds the pause should be WITHOUT line break
     *
     * @param seg Int seconds for the pause
     */
    public static void pauseSelection(int seg) {
        int mili = seg * 1000;
        System.out.print("");
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method to select how many seconds the pause should be WITH line break
     *
     * @param seg Int seconds for the pause
     */
    public static void pauseLineBreak(int seg) {
        int mili = seg * 1000;
        System.out.println();
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method to select hoy many half seconds  the pause shoul be WITHOUT line break
     *
     * @param halfSeg ints who represent one half
     */
    public static void halfPause(int halfSeg) {
        int mili = halfSeg * 500;
        System.out.print("");
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Static method to print 3 dots between half seconds WITHOUT line break
     */
    public static void dotsPause() {
        System.out.print(".");
        halfPause(1);
        System.out.print(".");
        halfPause(1);
        System.out.print(". ");
    }

    /**
     * Static method to print 3 dots between half seconds WITH line break at end
     */
    public static void dotsLineBreak() {
        System.out.print(".");
        halfPause(1);
        System.out.print(".");
        halfPause(1);
        System.out.println(".");
    }
}
