import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

public class WindowPickDate extends Window{
    private JDateChooser calendar;
    private java.util.Date date;
    private ArrayList<JButton> arrayButtons;
    private boolean checkStart;

    public WindowPickDate(){
        checkStart = false;
        calendar = calendarOnChoose();
        arrayButtons = new ArrayList<JButton>();
        calendar.setDate(new Date());
    }

    @Override
    public void setWindow() {
        checkStart = false;
        calendar.setDate(new Date());
        date = new Date();
        doOnDate();
        addToFrame();
        setCancelButton();
        setBackButton(3);
        checkStart = true;
    }

    @Override
    protected void removeFromFrame() {
        jFrame.remove(currentTimeLabel);
        jFrame.remove(calendar);
        jFrame.remove(cancelButton);
        jFrame.remove(backButton);
        jFrame.remove(backgroundPanel);
        for (JButton arrayButton : arrayButtons) {
            jFrame.remove(arrayButton);
        }
    }

    @Override
    protected void addToFrame() {
        jFrame.add(currentTimeLabel);
        jFrame.add(calendar);
        jFrame.add(backgroundPanel);
        jFrame.add(cancelButton);
        jFrame.add(backButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    private JButton createButton(String text){
        JButton button = new JButton(text);
        int width = 100;
        int height = 50;
        button.setSize(width,height);
        button.setFont(new Font("Consolas", Font.BOLD, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticket.setTime(text + ":00");
                ticket.setHallNumber(cinema.getHallNumber(ticket.getDay() + " " + ticket.getTime()));
                removeFromFrame();
                MainGuiSystem.changeWindow(5);
            }
        });
        return button;
    }

    private void doOnDate(){
        arrayButtons.removeAll(arrayButtons);
        date = calendar.getDate();
        ArrayList<String> tmpAL = new ArrayList<String>();
        tmpAL = cinema.getTimeByDay(date,ticket.getmovieName());
        if (tmpAL.size() > 0){
            ticket.setDay(tmpAL.get(0));
            for (int i = 1; i < tmpAL.size(); ++i){
                arrayButtons.add(createButton(tmpAL.get(i)));
                jFrame.add(arrayButtons.get(i - 1));
            }
        }

        int beginButton = 472 - (100 * arrayButtons.size() + (arrayButtons.size() - 1) * 20) / 2;
        for (int j = 0; j < arrayButtons.size(); ++j){
            arrayButtons.get(j).setLocation(beginButton + j * 120, 250);
            arrayButtons.get(j).repaint();
        }
    }

    private JDateChooser calendarOnChoose(){
        JDateChooser cal = new JDateChooser();
        cal.setSize(400,50);
        cal.setLocation(272,100);
        cal.setFont(new Font("Consolas", Font.BOLD, 20));
        cal.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (checkStart){
                    if (evt.getPropertyName().equals("date")) {
                        removeFromFrame();
                        doOnDate();
                        addToFrame();
                    }
                }
            }
        });
        return cal;
    }
}
