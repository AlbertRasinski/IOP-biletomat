import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public abstract class Window {
    private static final int height = 720;
    private static final int width = 960;
    protected static JFrame jFrame = new JFrame("IOP");
    protected static JPanel backgroundPanel;
    protected static JButton cancelButton;
    protected static JButton backButton;
    protected static User user;
    protected static Database database;
    protected static Cinema cinema;
    protected static Ticket ticket;
    protected static JLabel currentTimeLabel;

    protected Window(){
        database = new Database();
        user = new User(database);
        ticket = new Ticket(database);
        cinema = new Cinema(database);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width,height);
        jFrame.setResizable(false);
        jFrame.setLayout(null);

        backgroundPanel = paintPanel("1.png", width, height,0,0);
        cancelButton = new JButton();
        cancelButton.setLocation(853,15);
        cancelButton.setSize(80,80);
        setCancelButton();

        backButton = new JButton();
        backButton.setLocation(758,15);
        backButton.setSize(80,80);
        setBackButton(0);

        currentTimeLabel = new JLabel("aaaaaaaaaaaaaaaaa");
        currentTimeLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        currentTimeLabel.setForeground(Color.white);
        currentTimeLabel.setLocation(20,10);
        currentTimeLabel.setSize(500,40);

        jFrame.setVisible(true);
    }

    protected static JPanel paintPanel(String text, int sizeX, int sizeY, int locationX, int locationY){
        try {
            BufferedImage image = ImageIO.read(new File(text));
            JPanel panel = new JPanel(){
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, null);
                }
            };
            panel.setSize(sizeX,sizeY);
            panel.setLocation(locationX, locationY);
            panel.setBackground(new Color(0,0,0,255));
            return panel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void paintButton(int width, int height, int locationX, int locationY, JButton button){
        button.setSize(new Dimension(width,height));
        button.setLocation(locationX,locationY);
        button.setOpaque(false);
    }

    protected abstract void setWindow();

    protected void setCancelButton(){
        for (ActionListener al : cancelButton.getActionListeners()){
            cancelButton.removeActionListener(al);
        }
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromFrame();
                cinema.setCinemaValuesToInitial();
                MainGuiSystem.changeWindow(0);
            }
        });
        cancelButton.setOpaque(false);
    }

    protected void setBackButton(int i){
        for (ActionListener al : backButton.getActionListeners()){
            backButton.removeActionListener(al);
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromFrame();
                MainGuiSystem.changeWindow(i);
            }
        });
        backButton.setOpaque(false);
    }

    protected abstract void addToFrame();

    protected abstract void removeFromFrame();

    public static void updateTime(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Date date1 = new Date();
                    currentTimeLabel.setText(date1.toString());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentTimeLabel.validate();
                    currentTimeLabel.repaint();
                }
            }
        });
        t1.start();
    }

}