import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowSummary extends Window{
    private JPanel poster;
    private JLabel titleLabel;
    private JLabel summaryLabel;
    private JLabel cinemaLabel;
    private JLabel hallLabel;
    private JLabel dateLabel;
    private JLabel ticketsLabel;
    private JButton confirmButton;

    public WindowSummary(){
        poster = new JPanel();
        poster.setSize(140,200);
        poster.setLocation(150,150);
        poster.setBackground(new Color(0,0,0,255));

        summaryLabel = new JLabel("PODSUMOWANIE", SwingConstants.CENTER);
        summaryLabel.setFont(new Font("Consolas", Font.BOLD, 70));
        summaryLabel.setForeground(Color.white);
        summaryLabel.setLocation(0,50);
        summaryLabel.setSize(960,80);

        titleLabel = new JLabel("FILM: ");
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        titleLabel.setForeground(Color.white);
        titleLabel.setLocation(320,80);
        titleLabel.setSize(960,80);

        cinemaLabel = new JLabel("KINO: ");
        cinemaLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        cinemaLabel.setForeground(Color.white);
        cinemaLabel.setLocation(320,130);
        cinemaLabel.setSize(960,80);

        hallLabel = new JLabel("SALA: ");
        hallLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        hallLabel.setForeground(Color.white);
        hallLabel.setLocation(320,230);
        hallLabel.setSize(960,80);

        dateLabel = new JLabel("DATA: ");
        dateLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        dateLabel.setForeground(Color.white);
        dateLabel.setLocation(320,180);
        dateLabel.setSize(960,80);

        ticketsLabel = new JLabel("BILETY: ");
        ticketsLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        ticketsLabel.setForeground(Color.white);
        ticketsLabel.setLocation(320,280);
        ticketsLabel.setSize(960,80);

        confirmButton = confirmButtonOnClick();
    }

    @Override
    public void setWindow() {
        Image img = Toolkit.getDefaultToolkit().createImage(cinema.getChosenMoviePoster(ticket.getmovieName()));
        ImageIcon icon = new ImageIcon(img);
        JLabel lPhoto = new JLabel();
        lPhoto.setIcon(icon);
        poster.add(lPhoto);

        titleLabel.setText("FILM: " + ticket.getmovieName());
        cinemaLabel.setText("KINO: " + ticket.getCity() + ", " + ticket.getNetwork() + ", " + ticket.getAddress());
        hallLabel.setText("SALA: " + ticket.getHallNumber());
        dateLabel.setText("DATA: " + ticket.getDay() + " " + ticket.getTime());
        String ticketString = "BILETY:";
        for (String s : ticket.getPlaces()){
            ticketString += " ";
            ticketString += s;
        }
        ticketsLabel.setText(ticketString);

        setCancelButton();
        setBackButton(5);
        addToFrame();
    }

    @Override
    protected void addToFrame() {
        jFrame.add(confirmButton);
        jFrame.add(currentTimeLabel);
        jFrame.add(poster);
        jFrame.add(summaryLabel);
        jFrame.add(cinemaLabel);
        jFrame.add(hallLabel);
        jFrame.add(dateLabel);
        jFrame.add(ticketsLabel);
        jFrame.add(backgroundPanel);
        jFrame.add(cancelButton);
        jFrame.add(backButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    @Override
    protected void removeFromFrame() {
        jFrame.remove(confirmButton);
        jFrame.remove(currentTimeLabel);
        jFrame.remove(poster);
        jFrame.remove(summaryLabel);
        jFrame.remove(cinemaLabel);
        jFrame.remove(hallLabel);
        jFrame.remove(dateLabel);
        jFrame.remove(ticketsLabel);
        jFrame.remove(backgroundPanel);
        jFrame.remove(cancelButton);
        jFrame.remove(backButton);
    }

    private JButton confirmButtonOnClick(){
        JButton button = new JButton("POTWIERDŹ I ZAPŁAĆ");
        button.setLocation(250,450);
        button.setSize(400,100);
        button.setFont(new Font("Consolas", Font.BOLD, 35));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticket.setIdScreening(cinema.getIdScreening(ticket.getmovieName(),ticket.getDay() + " " + ticket.getTime()));
                if (user.isLoggedIn()){
                    ticket.orderTicket(user.getId());
                }else{
                    ticket.orderTicket(0);
                }

                removeFromFrame();
                MainGuiSystem.changeWindow(0);
            }
        });
        return button;
    }
}
