import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WindowLogin extends Window{
    private JTextField login;
    private JPasswordField password;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel errorLabel;

    private JButton next;

    public WindowLogin(){
        login = new JTextField();
        password = new JPasswordField();

        login.setSize(400,50);
        login.setLocation(280,150);
        login.setFont(new Font("Consolas", Font.PLAIN, 30));

        password.setLocation(280,450);
        password.setSize(400,50);
        password.setLocation(280,300);
        password.setFont(new Font("Consolas", Font.PLAIN, 30));

        next = new JButton("DALEJ");
        next.setSize(120,50);
        next.setLocation(417,400);
        next.setFont(new Font("Consolas", Font.PLAIN, 20));
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(user.login(login.getText(),password.getText()));
                if (user.login(login.getText(),password.getText())){
                    removeFromFrame();
                    if (user.getLoginOption() == 1){
                        MainGuiSystem.changeWindow(3);
                    }else{
                        MainGuiSystem.changeWindow(7);
                    }
                }else{
                    errorLabel.setText("Błędne dane");
                }
            }
        });

        loginLabel = new JLabel("LOGIN:");
        loginLabel.setSize(400,60);
        loginLabel.setLocation(280,100);
        loginLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        loginLabel.setForeground(Color.white);

        passwordLabel = new JLabel("HASLO:");
        passwordLabel.setSize(400,60);
        passwordLabel.setLocation(280,250);
        passwordLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        passwordLabel.setForeground(Color.white);

        errorLabel = new JLabel("");
        errorLabel.setSize(400,60);
        errorLabel.setLocation(550,400);
        errorLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        errorLabel.setForeground(Color.red);
    }

    @Override
    protected void setWindow() {
        setCancelButton();
        setBackButton(0);
        addToFrame();
    }

    @Override
    protected void removeFromFrame() {
        login.setText("");
        password.setText("");
        errorLabel.setText("");
        jFrame.remove(currentTimeLabel);
        jFrame.remove(login);
        jFrame.remove(password);
        jFrame.remove(loginLabel);
        jFrame.remove(passwordLabel);
        jFrame.remove(errorLabel);
        jFrame.remove(next);
        jFrame.remove(backgroundPanel);
        jFrame.remove(cancelButton);
        jFrame.remove(backButton);
    }

    @Override
    protected void addToFrame() {
        jFrame.add(currentTimeLabel);
        jFrame.add(login);
        jFrame.add(password);
        jFrame.add(loginLabel);
        jFrame.add(errorLabel);
        jFrame.add(passwordLabel);
        jFrame.add(next);
        jFrame.add(backgroundPanel);
        jFrame.add(cancelButton);
        jFrame.add(backButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }
}
