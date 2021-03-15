import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WindowPickPlace extends Window{

    private int places;
    private int rows;
    private int beginX1;
    private int beginX2;
    private int beginY;
    private ArrayList<JButton> seats;
    private JLabel kinoLabel;
    private JButton confirmButton;

    public WindowPickPlace(){
        seats = new ArrayList<JButton>();
        kinoLabel = new JLabel("EKRAN",SwingConstants.CENTER);
        kinoLabel.setBackground(Color.white);
        kinoLabel.setOpaque(true);
        kinoLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        confirmButton = confirmButtonOnClick();
    }

    @Override
    public void setWindow() {
        seats.removeAll(seats);
        cinema.getHall(ticket.getHallNumber()).setTakenPlaces(cinema.database, ticket.getCity(),ticket.getNetwork(),ticket.getDay() + " " + ticket.getTime() + ":00", ticket.getmovieName());
        rows = cinema.getHall(ticket.getHallNumber()).getRows();
        places = cinema.getHall(ticket.getHallNumber()).getPlaces();
        int size = rows * places - rows / 2;
        beginX1 = 472  - (40 * places + (places - 1) * 5) / 2;
        beginX2 = 472  - (40 * (places - 1) + (places - 2) * 5) / 2;
        beginY = 120 + 45 * rows;
        kinoLabel.setLocation(beginX1 + 45,105);
        kinoLabel.setSize((places - 2) * 45, 40);
        jFrame.add(kinoLabel);

        for (int i = 0; i < size; ++i){
            seats.add(createPlaceButton(i));
            jFrame.add(seats.get(i));
        }

        addToFrame();
        setBackButton(4);
        setCancelButton();

        cinema.getHall(ticket.getHallNumber()).reset();
        for (int i = 0; i < seats.size(); ++i){
            paintSeats(i, seats.get(i));
        }
    }

    @Override
    protected void addToFrame() {
        jFrame.add(currentTimeLabel);
        jFrame.add(confirmButton);
        jFrame.add(backgroundPanel);
        jFrame.add(backButton);
        jFrame.add(cancelButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    @Override
    protected void removeFromFrame() {
        jFrame.remove(currentTimeLabel);
        jFrame.remove(confirmButton);
        jFrame.remove(backgroundPanel);
        jFrame.remove(backButton);
        jFrame.remove(cancelButton);
        for (JButton buttons : seats){
            jFrame.remove(buttons);
        }
        jFrame.remove(kinoLabel);
    }

    private JButton createPlaceButton(int i){
        int j = (i / (2 * places - 1));

        JButton button = new JButton();
        button.setSize(40,40);

        if (((i + j) / places) % 2 == 0){
            button.setLocation(beginX1 + ((i + j) % places) * 45,beginY - ((i + j) /places) * 45);
        }else{
            button.setLocation(beginX2 + ((i + j) % places) * 45,beginY - ((i + j) /places) * 45);
        }

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cinema.getHall(ticket.getHallNumber()).getPlace(i).pickPlace();
                paintSeats(i,button);
            }
        });

        paintSeats(i, button);

        return button;
    }

    private void paintSeats(int i, JButton button){
        switch (cinema.getHall(ticket.getHallNumber()).getPlace(i).getStatus()){
            case taken:
                button.setBackground(Color.red);
                break;
            case picked:
                button.setBackground(Color.green);
                break;
            case free:
                button.setBackground(Color.white);
                break;
        }
    }

    private JButton confirmButtonOnClick(){
        JButton button = new JButton("POTWIERDŹ");
        button.setLocation(500,25);
        button.setSize(220,60);
        button.setFont(new Font("Consolas", Font.BOLD, 30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticket.setPlaces(cinema.getHall(ticket.getHallNumber()).pickedPlaces());
                removeFromFrame();
                MainGuiSystem.changeWindow(6);
            }
        });
        return button;
    }
}
