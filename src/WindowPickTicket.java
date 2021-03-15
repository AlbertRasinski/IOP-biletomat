import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowPickTicket extends Window{
    private ArrayList<String> tickets;
    private ArrayList<JCheckBox> checkboxes;
    private JButton next;

    @Override
    protected void setWindow() {
        tickets = user.getTickets();
        checkboxes = new ArrayList<>();

        for (int i = 0; i < tickets.size(); ++i){
            checkboxes.add(createCheckBox(tickets.get(i),i));
            jFrame.add(checkboxes.get(i));
        }

        next = new JButton("DALEJ");
        next.setSize(120,50);
        next.setLocation(417,500);
        next.setFont(new Font("Consolas", Font.PLAIN, 20));
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromFrame();
                MainGuiSystem.changeWindow(0);
            }
        });

        addToFrame();
        setCancelButton();
        setBackButton(1);
    }

    @Override
    protected void addToFrame() {
        jFrame.add(next);
        jFrame.add(currentTimeLabel);
        jFrame.add(backgroundPanel);
        jFrame.add(cancelButton);
        jFrame.add(backButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    @Override
    protected void removeFromFrame() {
        jFrame.remove(next);
        for (JCheckBox cb : checkboxes){
            jFrame.remove(cb);
        }
        jFrame.remove(backgroundPanel);
        jFrame.remove(cancelButton);
        jFrame.remove(backButton);
        user.logOut();
    }

    private JCheckBox createCheckBox(String s,int i){
        JCheckBox cb = new JCheckBox(s);
        cb.setSize(960,30);
        cb.setLocation(0,110 + 30 * i);
        cb.setHorizontalAlignment(SwingConstants.CENTER);
        cb.setOpaque(false);
        cb.setFont(new Font("Consolas", Font.BOLD, 25));
        cb.setForeground(Color.white);
        return cb;
    }
}
