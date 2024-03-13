package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame {

    JFrame frame= new JFrame("Tema2");
    String[] option ={"SHORTEST_QUEUE","SHORTEST_TIME"};
    public int butonn=0;
    JComboBox<String> dropDown= new JComboBox<String>(option);
    private JLabel titlu= new JLabel("Introduceti informatiile:");
    private JTextField inputN= new JTextField(100);
    private JTextField inputQ = new JTextField(100);
    private  JTextField inputTimeLimit= new JTextField(100);
    private JTextField inputMinArrival = new JTextField(100);
    private JTextField inputMaxArrival = new JTextField(100);
    private JTextField inputMinService = new JTextField(100);
    private JTextField inputMaxService = new JTextField(100);
    private JLabel labelN= new JLabel("N:");
    private JLabel labelQ=new JLabel("Q:");
    private JLabel labelTimeLimit= new JLabel("Time Limit:");
    private JLabel labelMinArrival= new JLabel("Arrival: Min:");
    private  JLabel labelMaxArrival= new JLabel("and Max:");
    private  JLabel labelMinService= new JLabel("Service: Min:");
    private  JLabel labelMaxService= new JLabel("and Max:");
    private  JButton buton= new JButton("Start Simulation");
    String n,q,minA,minS,maxS,maxA,timp;
    public SimulationFrame()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(20,20,255,350);
        JPanel content = new JPanel();
        Font font= titlu.getFont();

        titlu.setBounds(20,35,200,15);
        titlu.setFont(font.deriveFont(15.0f));

        labelN.setBounds(30,70,35,25);
        inputN.setBounds(50,70,35,25);

        labelQ.setBounds(30,100,35,25);
        inputQ.setBounds(50,100,35,25);

        labelTimeLimit.setBounds(20,130,80,25);
        inputTimeLimit.setBounds(85,130,35,25);

        labelMinArrival.setBounds(20,160,80,25);
        inputMinArrival.setBounds(90,160,35,25);
        labelMaxArrival.setBounds(130,160,80,25);
        inputMaxArrival.setBounds(183,160,35,25);

        labelMinService.setBounds(20,190,80,25);
        inputMinService.setBounds( 97,190,35,25);
        labelMaxService.setBounds(134,190,80,25);
        inputMaxService.setBounds(187,190,35,25);

        dropDown.setBounds(20,230,200,25);

        buton.setBounds(20,270,200,30);
        frame.add(dropDown);
        frame.add(buton);
        frame.add(titlu);
        frame.add(inputMaxService);
        frame.add(labelMaxService);
        frame.add(inputMinService);
        frame.add(labelMinService);
        frame.add(inputMaxArrival);
        frame.add(labelMaxArrival);
        frame.add(inputMinArrival);
        frame.add(labelMinArrival);
        frame.add(labelTimeLimit);
        frame.add(inputTimeLimit);
        frame.add(labelQ);
        frame.add(inputQ);
        frame.add(labelN);
        frame.add(inputN);
        frame.add(content);
        buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                butonn = 1;
            }
        });
    }

    public String getDropDown() {
        return (String) dropDown.getSelectedItem();
    }
    public String getInputN() {
        return inputN.getText();
    }
    public String getInputQ() {
        return inputQ.getText();
    }
    public String getInputTimeLimit() {
        return inputTimeLimit.getText();
    }
    public String getInputMinArrival() {
        return inputMinArrival.getText();
    }
    public String getInputMaxArrival() {
        return inputMaxArrival.getText();
    }
    public String getInputMinService() {
        return inputMinService.getText();
    }
    public String getInputMaxService() {
        return inputMaxService.getText();
    }


}
