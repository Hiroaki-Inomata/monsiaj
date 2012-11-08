package org.montsuqi.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mihara
 */
public class PopupNotify {

    private static List<JDialog> dialogs = new ArrayList<JDialog>();

    public static void popup(final String summary, final String body, final Icon icon, final int timeout) {
        final JDialog dialog = new JDialog();
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JPanel panel = new JPanel(new BorderLayout(5, 5));
                panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                panel.setBackground((Color) SystemColor.menu);

                JPanel textPanel = new JPanel(new BorderLayout(5, 5));
                textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                textPanel.setBackground((Color) SystemColor.menu);

                JLabel summaryLabel = new JLabel(summary);
                summaryLabel.setFont(new Font("Serif", Font.BOLD, 16));
                JTextPane bodyText = new JTextPane();
                bodyText.setText(body);
                bodyText.setOpaque(false);
                bodyText.setEditable(false);

                textPanel.add(summaryLabel, BorderLayout.NORTH);
                textPanel.add(bodyText, BorderLayout.CENTER);
                panel.add(new JLabel(icon), BorderLayout.WEST);
                panel.add(textPanel, BorderLayout.CENTER);

                dialog.setUndecorated(true);
                dialog.add(panel);
                dialog.pack();
                Rectangle rect = panel.getBounds();
                int scrWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
                int scrHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
                int y = 0;
                int x = scrWidth - rect.width;
                if (dialogs.size() > 0) {
                    for (JDialog d2 : dialogs) {
                        if (d2.getBounds().x < x) {
                            x = d2.getBounds().x;
                        }
                    }
                    JDialog d = dialogs.get(dialogs.size() - 1);
                    Rectangle r = d.getBounds();
                    y = r.y + r.height;
                    if (y + rect.height > scrHeight) {
                        y = 0;
                        x -= rect.width;
                    }
                    if (x < 0) {
                        y = 0;
                        x = scrWidth - rect.width;
                        dialogs.clear();
                    }
                }
                dialog.setLocation(x, y);
                dialog.setVisible(true);
                dialog.setFocusable(false);
                dialog.setFocusableWindowState(false);
                dialogs.add(dialog);
            }
        });
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            public Void doInBackground() {
                try {
                    int to = 10000;
                    if (timeout > 0) {
                        to = timeout * 1000;
                    }
                    try {
                        Thread.sleep(to);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                        return null;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return null;
            }

            @Override
            public void done() {
                dialogs.remove(dialog);
                dialog.dispose();
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {

        PopupNotify.popup("Warning", "警告警告",
                GtkStockIcon.get("gtk-dialog-error"), 5);
        PopupNotify.popup("Warning", "とーーーーーーーーーてもながーーーーーーーーいの",
                GtkStockIcon.get("gtk-dialog-error"), 60);
        PopupNotify.popup("Warning", "まあふつう\n\n\n\n\n\n\n\n\n\n\n",
                GtkStockIcon.get("gtk-dialog-error"), 10);

        for (int i = 0; i < 100; i++) {
            PopupNotify.popup("Warning", "警告警告",
                    GtkStockIcon.get("gtk-dialog-error"), 2);
            try {
                Thread.sleep(100); //3000ミリ秒Sleepする
            } catch (InterruptedException e) {
            };
        }
    }
}