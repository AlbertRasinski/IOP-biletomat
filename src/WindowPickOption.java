import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowPickOption extends Window{
    private JPanel buyLoginPanel;
    private JButton buyLoginButton;
    private JPanel buyLoggedOutPanel;
    private JButton buyLoggedOutButton;
    private JPanel reservedPanel;
    private JButton reservedButton;

    public WindowPickOption(){
        buyLoginPanel = paintPanel("3.png", 300,150,8,200);
        buyLoggedOutPanel = paintPanel("4.png", 300,150,323,200);
        reservedPanel = paintPanel("5.png", 300,150,638,200);

        buyLoginButton = buyLogOnClick();
        paintButton(300,150,8,200, buyLoginButton);

        buyLoggedOutButton = buyLoggedOutOnClick();
        paintButton(300,150,323,200, buyLoggedOutButton);

        reservedButton = reservedOnClick();
        paintButton(300,150,638,200, reservedButton);


    }

    @Override
    public void setWindow() {
        cinema.setCinemaValuesToInitial();
        setCancelButton();
        addToFrame();
    }

    @Override
    protected void removeFromFrame() {
        jFrame.remove(currentTimeLabel);
        jFrame.remove(buyLoginPanel);
        jFrame.remove(buyLoggedOutPanel);
        jFrame.remove(reservedPanel);
        jFrame.remove(backgroundPanel);
        jFrame.remove(buyLoginButton);
        jFrame.remove(buyLoggedOutButton);
        jFrame.remove(reservedButton);
    }

    @Override
    protected void addToFrame() {
        jFrame.add(currentTimeLabel);
        jFrame.add(buyLoginPanel);
        jFrame.add(buyLoggedOutPanel);
        jFrame.add(reservedPanel);
        jFrame.add(backgroundPanel);
        jFrame.add(buyLoginButton);
        jFrame.add(buyLoggedOutButton);
        jFrame.add(reservedButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    private JButton buyLogOnClick(){
        JButton tmpButton = new JButton();
        tmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setLoginOption(1);
                removeFromFrame();
                MainGuiSystem.changeWindow(1);
            }
        });
        return tmpButton;
    }

    private JButton buyLoggedOutOnClick(){
        JButton tmpButton = new JButton();
        tmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromFrame();
                MainGuiSystem.changeWindow(3);
            }
        });
        return tmpButton;
    }

    private JButton reservedOnClick(){
        JButton tmpButton = new JButton();
        tmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setLoginOption(2);
                removeFromFrame();
                MainGuiSystem.changeWindow(1);
            }
        });
        return tmpButton;
    }
}
