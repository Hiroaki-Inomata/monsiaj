/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.montsuqi.monsiaj.util;

import java.io.File;

/**
 *
 * @author mihara
 */
public class LogFileCleaner {

    private static final long VALID_TERM = 30 * 24 * 3600 * 1000; /* 30days millisec */
    public final static File LOG_DIR;

    static {
        LOG_DIR = new File(new File(new File(System.getProperty("user.home")), ".monsiaj"), "logs");
        LOG_DIR.mkdirs();
    }

    public static void cleanOld() {
        long now = System.currentTimeMillis();
        for (File f : LOG_DIR.listFiles()) {
            try {
                long elaps = now - f.lastModified();
                if (elaps > VALID_TERM) {
                    if (f.isFile()) {
                        f.delete();
                    }
                }
            } catch (SecurityException e) {
            }
        }
    }
}
