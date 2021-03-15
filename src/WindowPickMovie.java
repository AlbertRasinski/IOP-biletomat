import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowPickMovie extends Window{
    private JPanel movie1Panel;
    private JPanel movie2Panel;
    private JPanel movie3Panel;
    private JPanel movie4Panel;
    private JPanel movie5Panel;
    private JLabel movie1Label;
    private JLabel movie2Label;
    private JLabel movie3Label;
    private JLabel movie4Label;
    private JLabel movie5Label;
    private JButton movie1Button;
    private JButton movie2Button;
    private JButton movie3Button;
    private JButton movie4Button;
    private JButton movie5Button;

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton leftButton;
    private JButton rightButton;

    private JLabel currentCinema;
    private ArrayList<Movie> uniqueMovies;

    private int currentPosition;

    private JButton changeCinemaButton;

    public WindowPickMovie(){
        uniqueMovies = new ArrayList<Movie>();

        currentCinema = new JLabel("Wybrane kino: " + cinema.getCinema());
        currentCinema.setSize(960,30);
        currentCinema.setLocation(20,40);
        currentCinema.setFont(new Font("Consolas", Font.BOLD, 20));
        currentCinema.setForeground(Color.white);

        changeCinemaButton = changeCinemaOnClick("ZMIEŃ KINO");
        changeCinemaButton.setSize(150, 50);
        changeCinemaButton.setLocation(20, 75);
        changeCinemaButton.setFont(new Font("Consolas", Font.BOLD, 19));

        movie1Panel = new JPanel();
        setPanelParams(movie1Panel,1);
        movie2Panel = new JPanel();
        setPanelParams(movie2Panel,2);
        movie3Panel = new JPanel();
        setPanelParams(movie3Panel,3);
        movie4Panel = new JPanel();
        setPanelParams(movie4Panel,4);
        movie5Panel = new JPanel();
        setPanelParams(movie5Panel,5);

        leftButton = leftOnclick();
        paintButton(71, 127, 0, 216,leftButton);
        rightButton = rightOnclick();
        paintButton(71, 127, 873, 216,rightButton);

        movie1Button = movieOnClick(0);
        paintButton(140, 200, 87,180,movie1Button);
        movie2Button = movieOnClick(1);
        paintButton(140, 200, 245, 180,movie2Button);
        movie3Button = movieOnClick(2);
        paintButton(140, 200, 403, 180,movie3Button);
        movie4Button = movieOnClick(3);
        paintButton(140, 200, 561, 180,movie4Button);
        movie5Button = movieOnClick(4);
        paintButton(140, 200, 719, 180,movie5Button);

        leftPanel = paintPanel("left.png",71,127,5,216);
        rightPanel = paintPanel("right.png",71,127,868,216);

        movie1Label = new JLabel("",SwingConstants.CENTER);
        movie1Label.setSize(140,30);
        movie1Label.setLocation(87,390);
        movie1Label.setFont(new Font("Consolas", Font.BOLD, 12));
        movie1Label.setForeground(Color.white);
        movie2Label = new JLabel("",SwingConstants.CENTER);
        movie2Label.setSize(140,30);
        movie2Label.setLocation(245,390);
        movie2Label.setFont(new Font("Consolas", Font.BOLD, 12));
        movie2Label.setForeground(Color.white);
        movie3Label = new JLabel("",SwingConstants.CENTER);
        movie3Label.setSize(140,30);
        movie3Label.setLocation(403,390);
        movie3Label.setFont(new Font("Consolas", Font.BOLD, 12));
        movie3Label.setForeground(Color.white);
        movie4Label = new JLabel("",SwingConstants.CENTER);
        movie4Label.setSize(140,30);
        movie4Label.setLocation(561,390);
        movie4Label.setFont(new Font("Consolas", Font.BOLD, 12));
        movie4Label.setForeground(Color.white);
        movie5Label = new JLabel("",SwingConstants.CENTER);
        movie5Label.setSize(140,30);
        movie5Label.setLocation(719,390);
        movie5Label.setFont(new Font("Consolas", Font.BOLD, 12));
        movie5Label.setForeground(Color.white);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    @Override
    public void setWindow() {
        addToFrame();
        setCancelButton();
        setBackButton(0);

        currentCinema.setText("Wybrane kino: " + cinema.getCinema());
        cinema.setCinemaHalls();
        cinema.setScreenings();

        currentPosition = 0;

        uniqueMovies = cinema.getUniqueMovies();

        leftPanel.setVisible(false);
        leftButton.setVisible(false);
        if (uniqueMovies.size() <= 5){
            rightPanel.setVisible(false);
            rightButton.setVisible(false);
        }else{
            rightPanel.setVisible(true);
            rightButton.setVisible(true);
        }

        drawMovies();
    }

    @Override
    protected void removeFromFrame() {
        jFrame.remove(currentTimeLabel);
        jFrame.remove(changeCinemaButton);
        jFrame.remove(movie1Panel);
        jFrame.remove(movie2Panel);
        jFrame.remove(movie3Panel);
        jFrame.remove(movie4Panel);
        jFrame.remove(movie5Panel);
        jFrame.remove(movie1Label);
        jFrame.remove(movie2Label);
        jFrame.remove(movie3Label);
        jFrame.remove(movie4Label);
        jFrame.remove(movie5Label);
        jFrame.remove(currentCinema);
        jFrame.remove(backgroundPanel);
        jFrame.remove(leftButton);
        jFrame.remove(rightButton);
        jFrame.remove(leftPanel);
        jFrame.remove(rightPanel);
        jFrame.remove(movie1Button);
        jFrame.remove(movie2Button);
        jFrame.remove(movie3Button);
        jFrame.remove(movie4Button);
        jFrame.remove(movie5Button);
        jFrame.remove(cancelButton);
        jFrame.remove(backButton);
    }

    @Override
    protected void addToFrame() {
        jFrame.add(currentTimeLabel);
        jFrame.add(changeCinemaButton);
        jFrame.add(movie1Panel);
        jFrame.add(movie2Panel);
        jFrame.add(movie3Panel);
        jFrame.add(movie4Panel);
        jFrame.add(movie5Panel);
        jFrame.add(leftPanel);
        jFrame.add(rightPanel);
        jFrame.add(movie1Label);
        jFrame.add(movie2Label);
        jFrame.add(movie3Label);
        jFrame.add(movie4Label);
        jFrame.add(movie5Label);
        jFrame.add(currentCinema);
        jFrame.add(backgroundPanel);
        jFrame.add(leftButton);
        jFrame.add(rightButton);
        jFrame.add(movie1Button);
        jFrame.add(movie2Button);
        jFrame.add(movie3Button);
        jFrame.add(movie4Button);
        jFrame.add(movie5Button);
        jFrame.add(cancelButton);
        jFrame.add(backButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    private JButton changeCinemaOnClick(String text){
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromFrame();
                MainGuiSystem.changeWindow(2);
            }
        });
        return button;
    }

    private void drawMovies(){
        movie1Panel.removeAll();
        movie2Panel.removeAll();
        movie3Panel.removeAll();
        movie4Panel.removeAll();
        movie5Panel.removeAll();
        movie1Label.setText("");
        movie2Label.setText("");
        movie3Label.setText("");
        movie4Label.setText("");
        movie5Label.setText("");
        if (uniqueMovies.size() <= 4){
            switch (uniqueMovies.size()){
                case 4:
                    movie4Panel.add(convertByteToJLabel(uniqueMovies.get(3).getPoster()));
                    movie4Label.setText(uniqueMovies.get(3).getMovieName());
                case 3:
                    movie3Panel.add(convertByteToJLabel(uniqueMovies.get(2).getPoster()));
                    movie3Label.setText(uniqueMovies.get(2).getMovieName());
                case 2:
                    movie2Panel.add(convertByteToJLabel(uniqueMovies.get(1).getPoster()));
                    movie2Label.setText(uniqueMovies.get(1).getMovieName());
                case 1:
                    movie1Panel.add(convertByteToJLabel(uniqueMovies.get(0).getPoster()));
                    movie1Label.setText(uniqueMovies.get(0).getMovieName());
                case 0:
            }
        }else if (((currentPosition + 1) * 5) > uniqueMovies.size()){
            if (currentPosition * 5 <= uniqueMovies.size()){
                movie1Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5).getPoster()));
                movie1Label.setText(uniqueMovies.get(currentPosition * 5).getMovieName());
            }
            if (currentPosition * 5 + 1 < uniqueMovies.size()){
                movie2Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 1).getPoster()));
                movie2Label.setText(uniqueMovies.get(currentPosition * 5 + 1).getMovieName());
            }
            if (currentPosition * 5 + 2 < uniqueMovies.size()){
                movie3Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 2).getPoster()));
                movie3Label.setText(uniqueMovies.get(currentPosition * 5 + 2).getMovieName());
            }
            if (currentPosition * 5 + 3 < uniqueMovies.size()){
                movie4Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 3).getPoster()));
                movie4Label.setText(uniqueMovies.get(currentPosition * 5 + 3).getMovieName());
            }
            if (currentPosition * 5 + 4 < uniqueMovies.size()){
                movie5Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 4).getPoster()));
                movie5Label.setText(uniqueMovies.get(currentPosition * 5 + 4).getMovieName());
            }
        }else{
            movie1Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5).getPoster()));
            movie1Label.setText(uniqueMovies.get(currentPosition * 5).getMovieName());
            movie2Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 1).getPoster()));
            movie2Label.setText(uniqueMovies.get(currentPosition * 5 + 1).getMovieName());
            movie3Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 2).getPoster()));
            movie3Label.setText(uniqueMovies.get(currentPosition * 5 + 2).getMovieName());
            movie4Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 3).getPoster()));
            movie4Label.setText(uniqueMovies.get(currentPosition * 5 + 3).getMovieName());
            movie5Panel.add(convertByteToJLabel(uniqueMovies.get(currentPosition * 5 + 4).getPoster()));
            movie5Label.setText(uniqueMovies.get(currentPosition * 5 + 4).getMovieName());
        }
    }

    private void setPanelParams(JPanel panel, int x){
        panel.setSize(140,200);
        panel.setLocation((x - 1) * 158 + 87,180);
        panel.setBackground(new Color(0,0,0,255));
    }

    private JButton leftOnclick(){
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                --currentPosition;
                drawMovies();
                if (currentPosition == 0){
                    leftButton.setVisible(false);
                    leftPanel.setVisible(false);
                }
                rightButton.setVisible(true);
                rightPanel.setVisible(true);
                rightButton.repaint();
                leftButton.repaint();
                rightPanel.repaint();
                leftPanel.repaint();
            }
        });
        return button;
    }

    private JButton rightOnclick(){
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ++currentPosition;
                drawMovies();
                if ((currentPosition + 1) * 5 >= uniqueMovies.size()){
                    rightButton.setVisible(false);
                    rightPanel.setVisible(false);
                }
                leftButton.setVisible(true);
                leftPanel.setVisible(true);
                rightButton.repaint();
                leftButton.repaint();
                rightPanel.repaint();
                leftPanel.repaint();
            }
        });
        return button;
    }

    private JButton movieOnClick(int i){
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticket.setMovieName(uniqueMovies.get(currentPosition * 5 + i).getMovieName());
                removeFromFrame();
                MainGuiSystem.changeWindow(4);
            }
        });
        return button;
    }

    private JLabel convertByteToJLabel(byte[] posterByte){
        Image img = Toolkit.getDefaultToolkit().createImage(posterByte);
        ImageIcon icon = new ImageIcon(img);
        JLabel lPhoto = new JLabel();
        lPhoto.setIcon(icon);
        return lPhoto;
    }
}