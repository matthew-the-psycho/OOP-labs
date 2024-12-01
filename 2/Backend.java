package lab2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.*;


public class Backend {
    double first_procent;
    double first_volume;
    double need_percent;
    double need_volume;

    public void setNP(double n_p) {
        need_percent = n_p;
        return;
    }

    public Backend() {
        first_procent = 96.6;
        first_volume = 0;
        need_percent = 40;
        need_volume = 0;
    }

    public Backend(double f_p, double f_v, double n_p){
        this.first_procent = f_p;
        this.first_volume = f_v;
        this.need_percent = n_p;
    }

    public double FindNeedWasser() {
        if (this.need_percent == 0) {
            throw ExceptionWasser;
        }
        this.need_volume = (this.first_procent / this.need_percent - 1) * this.first_volume;
        return this.need_volume;
    }

    public double getFullVol() {
        return this.first_volume + this.need_volume;
    }
}