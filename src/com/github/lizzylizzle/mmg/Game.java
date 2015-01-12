package com.github.lizzylizzle.mmg;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;


class Game  {


    /**
     * Main app falling M&MS
     * @param args arguments
     */
    public static void main(String[] args) {
        
        try {
            LookAndFeelInfo[] ex = UIManager.getInstalledLookAndFeels();

            for (LookAndFeelInfo info : ex) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        EventQueue.invokeLater(new Runnable() {
            public void run() {

                /**
                 *  Our ui Model, View and Controller
                 */
                Model model = new Model();
                View View = new View();
                Controller Controller = new Controller(model, View);

                /**
                 * RUN
                 */
                Controller.init();

            }
        });
    }
}

