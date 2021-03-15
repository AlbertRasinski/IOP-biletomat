import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class WindowPickCinema extends Window {
    private JComboBox<String> cityComboBox;
    private JComboBox<String> networkComboBox;
    private JComboBox<String> cinemaComboBox;
    private JLabel cityLabel;
    private JLabel networkLabel;
    private JLabel cinemaLabel;
    private String city_;
    private String network_;
    private String cinema_;

    public WindowPickCinema() {
        cinema.setCinemaValues("","","");

        cityLabel = new JLabel("MIASTO:", SwingConstants.CENTER);
        cityLabel.setFont(new Font("Consolas", Font.BOLD, 40));
        cityLabel.setForeground(Color.white);
        cityLabel.setLocation(225,100);
        cityLabel.setSize(500,40);
        networkLabel = new JLabel("SIEĆ KIN:", SwingConstants.CENTER);
        networkLabel.setFont(new Font("Consolas", Font.BOLD, 40));
        networkLabel.setForeground(Color.white);
        networkLabel.setLocation(225,230);
        networkLabel.setSize(500,40);
        cinemaLabel = new JLabel("KINO:", SwingConstants.CENTER);
        cinemaLabel.setFont(new Font("Consolas", Font.BOLD, 40));
        cinemaLabel.setForeground(Color.white);
        cinemaLabel.setLocation(225,360);
        cinemaLabel.setSize(500,40);
    }

    @Override
    public void setWindow() {
        cityComboBox = cityOnChoose();
        cityComboBox.setSize(500, 40);
        cityComboBox.setLocation(225, 140);
        cityComboBox.setFont(new Font("Consolas", Font.BOLD, 20));
        cityComboBox.addItem("");

        networkComboBox = networkOnChoose();
        networkComboBox.setSize(500, 40);
        networkComboBox.setLocation(225, 270);
        networkComboBox.setFont(new Font("Consolas", Font.BOLD, 20));
        networkComboBox.addItem("");

        cinemaComboBox = cinemaOnChoose();
        cinemaComboBox.setSize(500, 40);
        cinemaComboBox.setLocation(225, 400);
        cinemaComboBox.setFont(new Font("Consolas", Font.BOLD, 20));
        cinemaComboBox.addItem("");

        cityComboBox.setEnabled(true);
        networkComboBox.setEnabled(false);
        cinemaComboBox.setEnabled(false);

        addToFrame();
        setCancelButton();
        setBackButton(3);



        ResultSet result = cinema.findCity();
        try {
            while (result.next()) {
                cityComboBox.addItem(result.getString("Miasto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void removeFromFrame() {
        jFrame.remove(currentTimeLabel);
        jFrame.remove(cityComboBox);
        jFrame.remove(networkComboBox);
        jFrame.remove(cinemaComboBox);
        jFrame.remove(backgroundPanel);
        jFrame.remove(cancelButton);
        jFrame.remove(backButton);
        jFrame.remove(cityLabel);
        jFrame.remove(networkLabel);
        jFrame.remove(cinemaLabel);
    }

    @Override
    protected void addToFrame() {
        jFrame.add(currentTimeLabel);
        jFrame.add(cityLabel);
        jFrame.add(networkLabel);
        jFrame.add(cinemaLabel);
        jFrame.add(cityComboBox);
        jFrame.add(networkComboBox);
        jFrame.add(cinemaComboBox);
        jFrame.add(backgroundPanel);
        jFrame.add(cancelButton);
        jFrame.add(backButton);
        SwingUtilities.updateComponentTreeUI(jFrame);
    }

    private JComboBox<String> cityOnChoose(){
        JComboBox<String> box = new JComboBox<String>();
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                city_ = box.getSelectedItem().toString();
                ResultSet result = cinema.findNetwork(city_);
                try {
                    while (result.next()) {
                        networkComboBox.addItem(result.getString("Nazwa"));
                    }
                    cityComboBox.setEnabled(false);
                    networkComboBox.setEnabled(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        return box;
    }

    private JComboBox<String> networkOnChoose(){
        JComboBox<String> box = new JComboBox<String>();
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                network_ = box.getSelectedItem().toString();
                ResultSet result = cinema.findAddress(city_,network_);

                try {
                    while (result.next()) {
                        cinemaComboBox.addItem(result.getString("Miasto") + " " + result.getString("Nazwa") + " " + result.getString("Adres"));
                    }
                    networkComboBox.setEnabled(false);
                    cinemaComboBox.setEnabled(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        return box;
    }

    private JComboBox<String> cinemaOnChoose(){
        JComboBox<String> box = new JComboBox<String>();
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cinema_ = box.getSelectedItem().toString();
                String[] arr = cinema_.split(" ");
                String address = "";
                for (int i = 2; i < arr.length; ++i){
                    address += arr[i];
                    address += " ";
                }
                if (address.length() > 0){
                    address = address.substring(0, address.length() - 1);
                }
                if (!cinema_.equals("")){
                    cinema.setCinemaValues(city_, network_, address);
                    ticket.setCity(city_);
                    ticket.setNetwork(network_);
                    ticket.setAddress(address);
                    removeFromFrame();
                    MainGuiSystem.changeWindow(3);
                }
            }
        });
        return box;
    }
}