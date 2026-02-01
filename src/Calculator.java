import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class Calculator implements ActionListener{
    //Declarations
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, timesButton, divButton;
    JButton delButton, equButton, clrButton,negButton;
    JPanel panel;

    Font myFont = new Font("Calibre",Font.PLAIN,30);
    double num1=0,num2=0,result=0;
    char operator;

    //Encapsulation
    private int firstNumber;
    private int secondNumber;
    private BinaryOperation currentOperation;



    //constructor
      Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(243,243,243));

        JLabel programmerLabel = new JLabel("☰ Programmer");
          programmerLabel.setBounds(50,5,200,30);
          programmerLabel.setFont(new Font("Calibre",Font.BOLD,18));
          frame.add(programmerLabel);

        textField = new JTextField();
        textField.setBounds(50,50,350,80);
        textField.setFont(new Font("Calibre",Font.PLAIN,40));
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel hexLabel = new JLabel("HEX");
        hexLabel.setBounds(70,140,50,20);
        hexLabel.setFont(new Font("Calibre",Font.PLAIN,12));

        JLabel decLabel = new JLabel("DEC");
        decLabel.setBounds(70,165,50,20);
        decLabel.setFont(new Font("Calibre",Font.PLAIN,12));

        JLabel octLabel = new JLabel("OCT");
        octLabel.setBounds(70,190,50,20);
        octLabel.setFont(new Font("Calibre",Font.PLAIN,12));

        JLabel binLabel = new JLabel("BIN");
        binLabel.setBounds(70,215,50,20);
        binLabel.setFont(new Font("Calibre",Font.BOLD,12));
        binLabel.setForeground(new Color(0,120,215));

        //value displays after the labels
          JTextField hexField = new JTextField("0");
          hexField.setBounds(130,140,100,20);
          hexField.setEditable(false);
          hexField.setBorder(null);
          hexField.setBackground(frame.getBackground());

          JTextField decField = new JTextField("0");
          decField.setBounds(130,165,100,20);
          decField.setEditable(false);
          decField.setBorder(null);
          decField.setBackground(frame.getBackground());

          JTextField octField = new JTextField(("0"));
          octField.setBounds(130,190,100,20);
          octField.setEditable(false);
          octField.setBorder(null);
          octField.setBackground(frame.getBackground());

          JTextField binField = new JTextField("0");
          binField.setBounds(130,215,100,20);
          binField.setEditable(false);
          binField.setBorder(null);
          binField.setBackground(frame.getBackground());

        frame.add(hexLabel);
        frame.add(decLabel);
        frame.add(octLabel);
        frame.add(binLabel);
        frame.add(hexField);
        frame.add(decField);
        frame.add(octField);
        frame.add(binField);


        addButton = new JButton("+");
            addButton.setBackground(new Color(240,240,240));
            addButton.setForeground(Color.BLACK);
        subButton = new JButton("-");
            subButton.setBackground(new Color(240,240,240));
            subButton.setForeground(Color.BLACK);
        timesButton = new JButton("*");
            timesButton.setBackground(new Color(240,240,240));
            timesButton.setForeground(Color.BLACK);
        divButton = new JButton("/");
            divButton.setBackground(new Color(240,240,240));
            divButton.setForeground(Color.BLACK);
        equButton = new JButton("=");
            equButton.setBackground(new Color(240,240,240));
            equButton.setForeground(Color.BLACK);
        delButton = new JButton(" ⌫ ");
            delButton.setBackground(new Color(240,240,240));
            delButton.setForeground(Color.BLACK);
        clrButton = new JButton("C");
            clrButton.setBackground(new Color(240,240,240));
            clrButton.setForeground(Color.BLACK);
        negButton = new JButton("(-)");
            negButton.setBackground(new Color(240,240,240));
            negButton.setForeground(Color.BLACK);

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = timesButton;
        functionButtons[3] = divButton;
        functionButtons[4] = equButton;
        functionButtons[5] = delButton;
        functionButtons[6] = clrButton;
        functionButtons[7] = negButton;

        for(int i=0;i<8;i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for(int i=0;i<10;i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);

            if(i<=1){
                numberButtons[i].setBackground(Color.WHITE);
                numberButtons[i].setForeground(Color.BLACK);
            }
            else{
                numberButtons[i].setBackground(new Color(224,224,224));
                numberButtons[i].setForeground(new Color(150,150,150));
                numberButtons[i].setEnabled(false);
            }
        }



        JButton[] hexButtons = new JButton[6];
        String[] hexLetters = {"A","B","C","D","E","F"};
        for(int i = 0;i<6;i++){
            hexButtons[i] = new JButton(hexLetters[i]);
            hexButtons[i].setFont(myFont);
            hexButtons[i].setFocusable(false);
            hexButtons[i].setEnabled(false);
            hexButtons[i].setBackground(new Color(224,224,224));
            hexButtons[i].setForeground(new Color(150,150,150));
            hexButtons[i].setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));

        }

        panel = new JPanel();
        panel.setBounds(50,250,350,280);
        panel.setLayout(new GridLayout (6,5,5,5));
        panel.setBackground(new Color(243,243,243));
        //Row 1
          panel.add(hexButtons[0]);
          JButton lshiftButton = new JButton("<<");
          lshiftButton.setFont(new Font("Calibre",Font.PLAIN,20));
            lshiftButton.setBackground(new Color(240,240,240));
            lshiftButton.setForeground(Color.BLACK);
          panel.add(lshiftButton);
          JButton rshiftButton = new JButton(">>");
          rshiftButton.setFont(new Font("Calibre",Font.PLAIN,20));
            rshiftButton.setBackground(new Color(240,240,240));
            rshiftButton.setForeground(Color.BLACK);
          panel.add(rshiftButton);
          clrButton.setFont(myFont);
          panel.add(clrButton);
          panel.add(delButton);

          // Row 2
          panel.add(hexButtons[1]);
          JButton openBracket = new JButton("(");
            openBracket.setBackground(new Color(240,240,240));
            openBracket.setForeground(Color.BLACK);
          openBracket.setFont(myFont);
          panel.add(openBracket);

          JButton closeBracket = new JButton(")");
          closeBracket.setFont(myFont);
            closeBracket.setBackground(new Color(240,240,240));
            closeBracket.setForeground(Color.BLACK);
          panel.add(closeBracket);

          JButton modButton = new JButton("%");
          modButton.setFont(myFont);
            modButton.setBackground(new Color(240,240,240));
            modButton.setForeground(Color.BLACK);
          panel.add(modButton);
          panel.add(divButton);

          //Row 3
          panel.add(hexButtons[2]);
          panel.add(numberButtons[7]);
          panel.add(numberButtons[8]);
          panel.add(numberButtons[9]);
          panel.add(timesButton);

          //Row 4
          panel.add(hexButtons[3]);
          panel.add(numberButtons[4]);
          panel.add(numberButtons[5]);
          panel.add(numberButtons[6]);
          panel.add(subButton);

          //Row 5
          panel.add(hexButtons[4]);
          panel.add(numberButtons[1]);
          panel.add(numberButtons[2]);
          panel.add(numberButtons[3]);
          panel.add(addButton);

          //Row 6
          panel.add(hexButtons[5]);
          panel.add(negButton);
          panel.add(numberButtons[0]);
          JButton pointButton = new JButton(".");
          pointButton.setFont(myFont);
          pointButton.setEnabled(false);
          panel.add(pointButton);
          equButton.setBackground(new Color(0,120,212));
          equButton.setForeground(Color.WHITE);
          equButton.setBorder(BorderFactory.createLineBorder(new Color(0,84,166),1));
          equButton.setOpaque(true);
          panel.add(equButton);



        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);


    }


    // getter and setter methods
    private void setFirstNumber(int num){
        this.firstNumber = num;
    }
    private int getFirstNumber(){
        return this.firstNumber;
    }
    //Polymorphism
    @Override
    public void actionPerformed(ActionEvent e){
          for (int i=0;i<2;i++){
              if(e.getSource() == numberButtons[i]){
                  textField.setText(textField.getText().concat(String.valueOf(i)));
              }
          }

          if(e.getSource() == addButton){
              try {
                  num1 = Integer.parseInt(textField.getText(), 2);
                  currentOperation = new BinaryAddition();
                  textField.setText("");
              }
              catch(NumberFormatException ex){
                  textField.setText("Error");
              }
          }
          if(e.getSource() == subButton){
              try {
                  num1 = Integer.parseInt(textField.getText(), 2);
                  currentOperation = new BinarySubtraction();
                  textField.setText("");
              }
              catch(NumberFormatException ex){
                  textField.setText("Error");
              }
          }
          if(e.getSource() == timesButton){
              try {
                  num1 = Integer.parseInt(textField.getText(), 2);
                  currentOperation = new Multiplication();
                  textField.setText("");
              }
              catch(NumberFormatException ex){
                  textField.setText("Error");
              }
          }
          if(e.getSource() == divButton){
              try {
                  num1 = Integer.parseInt(textField.getText(), 2);
                  currentOperation = new Division();
                  textField.setText("");
              }
              catch(NumberFormatException ex){
                  textField.setText("Error");
              }
          }

          if(e.getSource()==equButton){
              try {
                  num2 = Integer.parseInt(textField.getText(), 2);
                  int result = currentOperation.execute((int) num1, (int) num2);
                  textField.setText(Integer.toBinaryString(result));
                  num1 = result;
              }
              catch(NumberFormatException ex){
                  textField.setText("Error");
              }
              catch(ArithmeticException ex){
                  textField.setText("Math Error");
              }



          }
          if(e.getSource() == clrButton) {
              textField.setText("");
          }

          if(e.getSource() == delButton) {
              String string = textField.getText();
              textField.setText("");
              for(int i=0;i<string.length()-1;i++){
                  textField.setText(textField.getText()+string.charAt(i));
              }
          }
          if(e.getSource() == negButton) {
              try{
                  int temp = Integer.parseInt(textField.getText(),2);
                  temp *= 1;
                  textField.setText(Integer.toBinaryString(temp & 0xFFFFFFFF));
              }
              catch(NumberFormatException ex){
                  textField.setText("Error");
              }
          }


    }

}
