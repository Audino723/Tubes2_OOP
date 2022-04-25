package com.aetherwars.GUI;

import com.aetherwars.card.*;
import com.aetherwars.model.Player;
import com.aetherwars.util.CardReader;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import java.util.ArrayList;

public class GUI {
    // Main Frame
    Player p1, p2;
    int turn = 0;
    int phase = -1;
    int current_player = 2;
    int current_mana = 0;
    JFrame window;
    Container container;
    CommandHandler commandHandler = new CommandHandler();

    // Healthbar
    JPanel HealthBarPanel1, HealthBarPanel2;
    JProgressBar HealthBar1;
    ReverseProgressBar HealthBar2;
    JLabel p1Name, p2Name;

    // Turn
    // CirclePanel TurnPanel #Ngebug Printnya
    JPanel TurnPanel;
    JLabel TurnLabel, CurrentTurnLabel;

    // Board
    JPanel Board1, PA0, PA1, PA2, PA3, PA4, PA5;
    JButton P1Card[] = new JButton[7];
    JPanel Board2, PB0, PB1, PB2, PB3, PB4, PB5;
    JButton P2Card[] = new JButton[7];
    JButton A0Button, A1Button, A2Button, A3Button, A4Button, A5Button;
    JButton B0Button, B1Button, B2Button, B3Button, B4Button, B5Button;

    // Phase
    JPanel PhasePanel, NextPhasePanel;
    JLabel DrawPhaseLabel, PlanPhaseLabel, AttackPhaseLabel, EndPhaseLabel;
    JButton NextPhaseButton;
    PhaseHandler phaseHandler = new PhaseHandler();
    // Draw Phase
    JPanel DrawPhasePanel, Draw1Panel, Draw2Panel, Draw3Panel, SkipDrawPanel;
    JButton Draw1Button, Draw2Button, Draw3Button, SkipDrawButton;
    JButton[] DrawCard = new JButton[4];
    JPanel HandFullPanel;
    JLabel HandFullNotice;

    // Hand
    JPanel HandPanel, Hand1Panel, Hand2Panel, Hand3Panel, Hand4Panel, Hand5Panel;
    JButton Hand1Button, Hand2Button, Hand3Button, Hand4Button, Hand5Button;
    JButton HandCard[] = new JButton[5];

    // Hover
    JPanel HoverPanel, HoverImagePanel, HoverTextPanel;
    JLabel HoverImageLabel;
    JTextArea HoverTextArea;

    // Throw Card
    JPanel ThrowPanel;
    JButton ThrowButton;

    // Deck
    JPanel DeckPanel;
    JLabel DeckLabel;

    // Mana
    JPanel ManaPanel;
    JLabel ManaLabel;

    // Button buat testing healthbar
    JButton button;
    JPanel buttonpanel;
    DamageHandler damageHandler = new DamageHandler();

    public GUI(Player player1, Player player2) throws IOException, URISyntaxException {
        // Crate Frame
        this.p1 = player1;
        this.p2 = player2;

        ImageIcon image = new ImageIcon(Define.IMG_PATH + "logo.png");
        this.window = new JFrame();
        this.window.setSize(1080, 720);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setTitle("NgOOP 2.0");
        this.window.setIconImage(image.getImage());
        this.window.setResizable(false);
        this.window.setLayout(null);
        // this.container = this.window.getContentPane();

        // Player
        // 1==========================================================================================
        // Create Healthbar
        this.HealthBarPanel1 = new JPanel();
        this.HealthBarPanel1.setBounds(5, 30, 400, 80);
        // this.container.add(this.HealthBarPanel1);

        this.HealthBar1 = new JProgressBar(0, 80);
        this.HealthBar1.setPreferredSize(new Dimension(400, 40));
        this.HealthBar1.setForeground(Color.GREEN);
        this.HealthBar1.setValue(80);
        this.HealthBarPanel1.add(this.HealthBar1);

        this.p1Name = new JLabel("Lumine (Aether's Sister)");
        this.HealthBarPanel1.add(this.p1Name);

        // board
        this.Board1 = new JPanel();
        this.Board1.setBounds(5, 100, 520, 270);
        // this.Board1.setBackground(Color.BLACK);
        this.Board1.setLayout(null);

        this.PA0 = new JPanel();
        this.A0Button = new JButton();
        createCardinBoard(this.PA0, this.A0Button, 0, 90, "A0");
        this.A0Button.setIcon(scaleImage(new ImageIcon(Define.IMG_PATH + "player1.png"), 120, 120));
        this.PA0.add(this.A0Button);
        this.Board1.add(this.PA0);

        this.PA1 = new JPanel();
        this.A1Button = new JButton();
        createCardinBoard(this.PA1, this.A1Button, 130, 10, "A1");
        this.A1Button.setIcon(scaleImage(image, 50, 50));
        this.A1Button.setText("<html>EI<br>Lvl/Exp: 1[1/10]<br>Attack: 1<br>Health: 1</html>");
        this.PA1.add(this.A1Button);
        this.Board1.add(this.PA1);

        this.PA2 = new JPanel();
        this.A2Button = new JButton();
        createCardinBoard(this.PA2, this.A2Button, 260, 10, "A2");
        this.PA2.add(this.A2Button);
        this.Board1.add(this.PA2);

        this.PA3 = new JPanel();
        this.A3Button = new JButton();
        createCardinBoard(this.PA3, this.A3Button, 130, 140, "A3");
        this.PA3.add(this.A3Button);
        this.Board1.add(this.PA3);

        this.PA4 = new JPanel();
        this.A4Button = new JButton();
        createCardinBoard(this.PA4, this.A4Button, 260, 140, "A4");
        this.PA4.add(this.A4Button);
        this.Board1.add(this.PA4);

        this.PA5 = new JPanel();
        this.A5Button = new JButton();
        createCardinBoard(this.PA5, this.A5Button, 390, 80, "A5");
        this.PA5.add(this.A5Button);
        this.Board1.add(this.PA5);

        // PLAYER
        // 2==========================================================================================
        // Create Healthbar
        this.HealthBarPanel2 = new JPanel();
        this.HealthBarPanel2.setBounds(660, 30, 400, 80);

        this.HealthBar2 = new ReverseProgressBar(0, 80);
        this.HealthBar2.setPreferredSize(new Dimension(400, 40));
        this.HealthBar2.setForeground(Color.GREEN);
        this.HealthBar2.setValue(80);
        this.HealthBarPanel2.add(this.HealthBar2);

        this.p2Name = new JLabel("Ayaka (Aether's Future Wife)");
        this.HealthBarPanel2.add(this.p2Name);

        // board
        this.Board2 = new JPanel();
        this.Board2.setBounds(540, 100, 520, 270);
        // this.Board2.setBackground(Color.BLACK);
        this.Board2.setLayout(null);

        this.PB0 = new JPanel();
        this.B0Button = new JButton();
        createCardinBoard(this.PB0, this.B0Button, 400, 90, "B0");
        this.B0Button.setIcon(scaleImage(new ImageIcon(Define.IMG_PATH + "player2.png"), 120, 120));
        this.PB0.add(this.B0Button);
        this.Board2.add(this.PB0);

        this.PB1 = new JPanel();
        this.B1Button = new JButton();
        createCardinBoard(this.PB1, this.B1Button, 270, 10, "B1");
        this.PB1.add(this.B1Button);
        this.Board2.add(this.PB1);

        this.PB2 = new JPanel();
        this.B2Button = new JButton();
        createCardinBoard(this.PB2, this.B2Button, 140, 10, "B2");
        this.PB2.add(this.B2Button);
        this.Board2.add(this.PB2);

        this.PB3 = new JPanel();
        this.B3Button = new JButton();
        createCardinBoard(this.PB3, this.B3Button, 270, 140, "B3");
        this.PB3.add(this.B3Button);
        this.Board2.add(this.PB3);

        this.PB4 = new JPanel();
        this.B4Button = new JButton();
        createCardinBoard(this.PB4, this.B4Button, 140, 140, "B4");
        this.PB4.add(this.B4Button);
        this.Board2.add(this.PB4);

        this.PB5 = new JPanel();
        this.B5Button = new JButton();
        createCardinBoard(this.PB5, this.B5Button, 10, 80, "B5");
        this.PB5.add(this.B5Button);
        this.Board2.add(this.PB5);

        // All
        // ===============================================================================================
        // Turn
        // this.TurnPanel = new CirclePanel();
        this.TurnPanel = new JPanel();
        this.TurnPanel.setBounds(485, 5, 90, 90);
        this.TurnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.TurnPanel.setLayout(new BorderLayout());

        this.TurnLabel = new JLabel("<html>Turn<br><center>" + this.turn + "</center></html>");
        this.TurnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.TurnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.TurnPanel.add(this.TurnLabel);

        // Phase
        this.PhasePanel = new JPanel();
        this.PhasePanel.setBounds(5, 380, 940, 30);
        this.PhasePanel.setLayout(new GridLayout(1, 4));

        this.DrawPhaseLabel = new JLabel("Draw");
        createPhase(this.DrawPhaseLabel);
        this.DrawPhaseLabel.setBackground(Color.decode("#1D63DC"));
        this.DrawPhaseLabel.setForeground(Color.WHITE);
        this.PhasePanel.add(this.DrawPhaseLabel);

        this.PlanPhaseLabel = new JLabel("Plan");
        createPhase(this.PlanPhaseLabel);
        this.PhasePanel.add(this.PlanPhaseLabel);

        this.AttackPhaseLabel = new JLabel("Attack");
        createPhase(this.AttackPhaseLabel);
        this.PhasePanel.add(this.AttackPhaseLabel);

        this.EndPhaseLabel = new JLabel("End");
        createPhase(this.EndPhaseLabel);
        this.PhasePanel.add(this.EndPhaseLabel);

        this.PhasePanel.setVisible(true);

        this.NextPhasePanel = new JPanel();
        this.NextPhasePanel.setBounds(960, 380, 100, 30);
        this.NextPhasePanel.setLayout(new BorderLayout());

        this.NextPhaseButton = new JButton(">>");
        this.NextPhaseButton.setFont(new Font("Arial", Font.BOLD, 20));
        this.NextPhaseButton.setForeground(Color.WHITE);
        this.NextPhaseButton.setBackground(Color.decode("#1D63DC"));
        this.NextPhaseButton.setFocusPainted(false);
        this.NextPhaseButton.addActionListener(phaseHandler);

        this.NextPhasePanel.add(this.NextPhaseButton);

        // DrawPhase
        this.DrawPhasePanel = new JPanel();
        this.DrawPhasePanel.setBounds(0, 0, 1080, 720);
        this.DrawPhasePanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        this.DrawPhasePanel.setLayout(null);
        this.DrawPhasePanel.setVisible(false);

        this.Draw1Panel = new JPanel();
        this.Draw1Button = new JButton();
        createDrawChoice(this.Draw1Panel, this.Draw1Button, 50, 210, "D1");
        this.Draw1Panel.add(this.Draw1Button);

        this.Draw2Panel = new JPanel();
        this.Draw2Button = new JButton();
        createDrawChoice(this.Draw2Panel, this.Draw2Button, 320, 210, "D2");
        this.Draw2Panel.add(this.Draw2Button);

        this.Draw3Panel = new JPanel();
        this.Draw3Button = new JButton();
        createDrawChoice(this.Draw3Panel, this.Draw3Button, 580, 210, "D3");
        this.Draw3Panel.add(this.Draw3Button);

        this.SkipDrawPanel = new JPanel();
        this.SkipDrawButton = new JButton();
        createDrawChoice(this.SkipDrawPanel, this.SkipDrawButton, 860, 210, "D0");
        this.SkipDrawButton.setText("SKIP");
        this.SkipDrawPanel.add(this.SkipDrawButton);

        this.DrawPhasePanel.add(this.Draw1Panel);
        this.DrawPhasePanel.add(this.Draw2Panel);
        this.DrawPhasePanel.add(this.Draw3Panel);
        this.DrawPhasePanel.add(this.SkipDrawPanel);

        this.HandFullPanel = new JPanel();
        this.HandFullPanel.setBounds(260, 100, 540, 270);
        this.HandFullPanel.setLayout(new GridLayout(1,1));

        this.HandFullNotice = new JLabel();
        this.HandFullNotice.setFont(new Font("Arial", Font.BOLD, 20));
        this.HandFullNotice.setText("<html><center>Hand Penuh, Silahkan Pilih kartu untuk dibuang!</center></html>");
        this.HandFullNotice.setHorizontalAlignment(SwingConstants.CENTER);
        this.HandFullPanel.add(this.HandFullNotice);
        this.HandFullPanel.setVisible(false);

        // Hand
        this.HandPanel = new JPanel();
        this.HandPanel.setBounds(5, 445, 500, 200);
        this.HandPanel.setLayout(new GridLayout(1, 5));
        this.HandPanel.setBackground(Color.black);

        this.Hand1Panel = new JPanel();
        this.Hand1Button = new JButton();
        createHand(this.Hand1Panel, this.Hand1Button, "H1");
        this.Hand1Button.setIcon(scaleImage(image, 70, 70));
        this.Hand1Button.setText("<html>EI<br>Attack: 1<br>Health: 1</html>");
        this.Hand1Panel.add(this.Hand1Button);
        this.HandPanel.add(this.Hand1Panel);

        this.Hand2Panel = new JPanel();
        this.Hand2Button = new JButton();
        createHand(this.Hand2Panel, this.Hand2Button, "H2");
        this.Hand2Panel.add(this.Hand2Button);
        this.HandPanel.add(this.Hand2Panel);

        this.Hand3Panel = new JPanel();
        this.Hand3Button = new JButton();
        createHand(this.Hand3Panel, this.Hand3Button, "H3");
        this.Hand3Panel.add(this.Hand3Button);
        this.HandPanel.add(this.Hand3Panel);

        this.Hand4Panel = new JPanel();
        this.Hand4Button = new JButton();
        createHand(this.Hand4Panel, this.Hand4Button, "H4");
        this.Hand4Panel.add(this.Hand4Button);
        this.HandPanel.add(this.Hand4Panel);

        this.Hand5Panel = new JPanel();
        this.Hand5Button = new JButton();
        createHand(this.Hand5Panel, this.Hand5Button, "H5");
        this.Hand5Panel.add(this.Hand5Button);
        this.HandPanel.add(this.Hand5Panel);

        // Hover
        this.HoverPanel = new JPanel();
        this.HoverPanel.setBounds(515, 425, 430, 250);
        // this.HoverPanel.setBackground(Color.black);
        this.HoverPanel.setLayout(null);

        this.HoverImagePanel = new JPanel();
        this.HoverImagePanel.setBounds(0, 0, 200, 250);
        this.HoverImagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.HoverImagePanel.setLayout(new BorderLayout());
        this.HoverImageLabel = new JLabel();
        this.HoverImagePanel.add(this.HoverImageLabel);
        this.HoverPanel.add(this.HoverImagePanel);

        this.HoverTextPanel = new JPanel();
        this.HoverTextPanel.setBounds(210, 0, 220, 250);
        this.HoverTextPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.HoverTextPanel.setLayout(new BorderLayout());
        this.HoverTextArea = new JTextArea();
        this.HoverTextArea.setLineWrap(true);
        this.HoverTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        this.HoverTextPanel.add(this.HoverTextArea);
        this.HoverPanel.add(this.HoverTextPanel);

        // Throw Card
        this.ThrowPanel = new JPanel();
        this.ThrowPanel.setBounds(960, 425, 100, 70);
        this.ThrowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.ThrowPanel.setLayout(new BorderLayout());

        this.ThrowButton = new JButton("<html>Throw<br><center>Card</center></html>");
        this.ThrowButton.setFont(new Font("Arial", Font.BOLD, 20));
        this.ThrowButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.ThrowButton.setForeground(Color.WHITE);
        this.ThrowButton.setBackground(Color.RED);
        this.ThrowButton.setFocusPainted(false);
        this.ThrowButton.addActionListener(this.commandHandler);
        this.ThrowButton.setActionCommand("TC");
        this.ThrowPanel.add(this.ThrowButton);

        // Deck
        this.DeckPanel = new JPanel();
        this.DeckPanel.setBounds(960, 505, 100, 70);
        this.DeckPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.DeckPanel.setLayout(new BorderLayout());

        this.DeckLabel = new JLabel("<html>Deck<br><center>" + "40" + "/40</center></html>");
        this.DeckLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.DeckLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.DeckPanel.add(this.DeckLabel);

        // Mana
        this.ManaPanel = new JPanel();
        this.ManaPanel.setBounds(960, 585, 100, 70);
        this.ManaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.ManaLabel = new JLabel("<html>Mana<br><center>" + Math.min(this.turn, 10) + "/" + Math.min(this.turn, 10)
                + "</center></html>");
        this.ManaLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.ManaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.ManaPanel.add(this.ManaLabel);

        // Tersting healthbar================================================
        this.buttonpanel = new JPanel();
        this.buttonpanel.setBounds(5, 0, 100, 30);
        // this.container.add(this.buttonpanel);

        this.button = new JButton("Forfeit");
        this.button.setBackground(Color.RED);
        this.button.setPreferredSize(new Dimension(100, 30));
        this.button.setFocusPainted(false);
        this.button.addActionListener(damageHandler);
        this.buttonpanel.add(this.button);

        this.window.add(this.HandFullPanel);
        this.window.add(this.DrawPhasePanel);
        this.window.add(this.Board1);
        this.window.add(this.Board2);
        this.window.add(this.HealthBarPanel2);
        this.window.add(this.HealthBarPanel1);
        this.window.add(this.TurnPanel);
        this.window.add(this.PhasePanel);
        this.window.add(this.NextPhasePanel);
        this.window.add(this.HandPanel);
        this.window.add(this.HoverPanel);
        this.window.add(this.ThrowPanel);
        this.window.add(this.DeckPanel);
        this.window.add(this.ManaPanel);
        this.window.add(this.buttonpanel);
        this.window.setVisible(true);

        this.P1Card[0] = this.A0Button;
        this.P1Card[1] = this.A1Button;
        this.P1Card[2] = this.A2Button;
        this.P1Card[3] = this.A3Button;
        this.P1Card[4] = this.A4Button;
        this.P1Card[5] = this.A5Button;

        this.P2Card[0] = this.B0Button;
        this.P2Card[1] = this.B1Button;
        this.P2Card[2] = this.B2Button;
        this.P2Card[3] = this.B3Button;
        this.P2Card[4] = this.B4Button;
        this.P2Card[5] = this.B5Button;

        this.HandCard[0] = this.Hand1Button;
        this.HandCard[1] = this.Hand2Button;
        this.HandCard[2] = this.Hand3Button;
        this.HandCard[3] = this.Hand4Button;
        this.HandCard[4] = this.Hand5Button;

        this.DrawCard[0] = this.SkipDrawButton;
        this.DrawCard[1] = this.Draw1Button;
        this.DrawCard[2] = this.Draw2Button;
        this.DrawCard[3] = this.Draw3Button;
        showEndPhase();
        nextPhase();
    }

    private ImageIcon scaleImage(ImageIcon unscaled_image, int width, int height) {
        Image image = unscaled_image.getImage();
        Image scaled_image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled_image);
    }

    private void createCardinBoard(JPanel panel, JButton button, int x, int y, String name) {
        panel.setBounds(x, y, 120, 120);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new BorderLayout());
        button.setText(name);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setFocusPainted(false);
        button.addActionListener(commandHandler);
        if (!name.equals("A0") && !name.equals("B0")) {
            button.addMouseListener(new HoverHandler(name));
        }
        button.setActionCommand(name);
    }

    private void createPhase(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setOpaque(true);
    }

    private void createHand(JPanel panel, JButton button, String name) {
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new BorderLayout());
        button.setText(name);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.addActionListener(commandHandler);
        button.addMouseListener(new HoverHandler(name));
        button.setActionCommand(name);
    }

    private void createDrawChoice(JPanel panel, JButton button, int x, int y, String name) {
        panel.setBounds(x, y, 150, 300);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new BorderLayout());
        button.setText(name);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setFocusPainted(false);
        button.addActionListener(commandHandler);
        button.setActionCommand(name);
    }

    public void update() {
        Player player;
        if (this.current_player == 1) {
            player = this.p1;
        } else {
            player = this.p2;
        }
        this.TurnLabel.setText("<html>Turn<br><center>" + this.turn + "</center></html>");
        this.DeckLabel.setText(("<html>Deck<br><center>" + player.getDeck().getNeff() + "/40</center></html>"));
        this.ManaLabel
                .setText(("<html>Mana<br><center>" + player.getMana() + "/" + this.current_mana + "</center></html>"));
    }

    public void updateHand() {
        if (this.current_player == 1) {
            for (int i = 0; i < 5; i++) {
                Card card = this.p1.getHand().getCard(i);
                if (card != null) {
                    this.HandCard[i].setText(card.getName());
                    this.HandCard[i]
                            .setIcon(scaleImage(new ImageIcon(Define.IMG_PATH + card.getImagePath()), 100, 100));
                    this.HandCard[i].setVisible(true);
                } else {
                    this.HandCard[i].setVisible(false);
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                Card card = this.p2.getHand().getCard(i);
                if (card != null) {
                    this.HandCard[i].setText(card.getName());
                    this.HandCard[i]
                            .setIcon(scaleImage(new ImageIcon(Define.IMG_PATH + card.getImagePath()), 100, 100));
                } else {
                    this.HandCard[i].setText("");
                }
            }
        }
    }

    class ReverseProgressBar extends JProgressBar {
        ReverseProgressBar(int a, int b) {
            super(a, b);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.scale(-1, 1); // Flips over y-axis
            g2d.translate(-getWidth(), 0); // Moves back to old position.
            super.paintComponent(g2d);
        }
    }

    public class CirclePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        }
    }

    public class DamageHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            damageReceived();
        }
    }

    public void damageReceived() {
        // this.p1.health = this.p1.health - 10;
        // this.HealthBar1.setValue(this.p1.health);
    }

    public class PhaseHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextPhase();
        }
    }

    public void nextPhase() {
        this.commandHandler.command1 = null;
        this.commandHandler.command2 = null;
        clearCardChoosen();
        this.phase += 1;
        switch (this.phase % 8) {
            case 0:
                changePlayer();
                this.turn += 1;
                this.p1.startTurn(this.turn);
                this.current_mana = this.p1.getMana();
                updateHand();
                showDrawPhase();
                showDrawChoice();
                break;
            case 1:
                showPlanPhase();
                break;
            case 2:
                showAttackPhase();
                break;
            case 3:
                showEndPhase();
                break;
            case 4:
                changePlayer();
                this.p2.startTurn(this.turn);
                this.current_mana = this.p2.getMana();
                updateHand();
                showDrawPhase();
                showDrawChoice();
                break;
            case 5:
                showPlanPhase();
                break;
            case 6:
                showAttackPhase();
                break;
            case 7:
                showEndPhase();
                break;
            default:
                break;
        }
        updateHand();
        update();
    }

    public void changePlayer() {
        if (this.current_player == 1) {
            this.current_player = 2;
        } else {
            this.current_player = 1;
        }
    }

    public void showDrawPhase() {
        this.DrawPhaseLabel.setBackground(Color.decode("#1D63DC"));
        this.DrawPhaseLabel.setForeground(Color.WHITE);
        this.EndPhaseLabel.setBackground(Color.WHITE);
        this.EndPhaseLabel.setForeground(Color.BLACK);
        this.DrawPhasePanel.setVisible(true);
        this.NextPhaseButton.setEnabled(false);
        this.ThrowButton.setEnabled(false);
        this.HoverPanel.setVisible(false);
        this.window.repaint();
    }

    public void showDrawFullPhase(){
        this.DrawPhasePanel.setVisible(false);
        for (int index = 0; index < 5; index++) {
            this.HandCard[index].setEnabled(true);
        }
    }

    public void showPlanPhase() {
        int i;
        this.DrawPhasePanel.setVisible(false);
        for (i = 0; i < 6; i++) {
            if (current_player == 1) {
                this.P1Card[i].setEnabled(true);
            } else {
                this.P2Card[i].setEnabled(true);
            }
            if (i < 5) {
                this.HandCard[i].setEnabled(true);
            }
        }
        this.NextPhaseButton.setEnabled(true);
        this.ThrowButton.setEnabled(true);
        this.HoverPanel.setVisible(true);
        this.DrawPhaseLabel.setBackground(Color.WHITE);
        this.DrawPhaseLabel.setForeground(Color.BLACK);
        this.PlanPhaseLabel.setBackground(Color.decode("#1D63DC"));
        this.PlanPhaseLabel.setForeground(Color.WHITE);
    }

    public void showAttackPhase() {
        for (int i = 0; i < 6; i++) {
            if (current_player != 1) {
                this.P1Card[i].setEnabled(true);
            } else {
                this.P2Card[i].setEnabled(true);
            }
        }
        for (int i = 0; i < 5; i++) {
            this.HandCard[i].setEnabled(false);
        }
        this.PlanPhaseLabel.setBackground(Color.WHITE);
        this.PlanPhaseLabel.setForeground(Color.BLACK);
        this.AttackPhaseLabel.setBackground(Color.decode("#1D63DC"));
        this.AttackPhaseLabel.setForeground(Color.WHITE);
    }

    public void showEndPhase() {
        for (int i = 0; i < 6; i++) {
            this.P1Card[i].setEnabled(false);
            this.P2Card[i].setEnabled(false);
            if (i < 5) {
                this.HandCard[i].setEnabled(false);
            }
        }
        this.AttackPhaseLabel.setBackground(Color.WHITE);
        this.AttackPhaseLabel.setForeground(Color.BLACK);
        this.EndPhaseLabel.setBackground(Color.decode("#1D63DC"));
        this.EndPhaseLabel.setForeground(Color.WHITE);
    }

    public void showDrawChoice(){
        Player player;
        if(current_player==1){
            player = p1;
        }
        else{
            player = p2;
        }
        ArrayList<Card> choice = player.showDrawnDeck();
        for (int i = 0; i < 3; i++) {
            this.DrawCard[i+1].setEnabled(false);
        }
        for (int i = 0; i < 3;i++) {
            Card card = choice.get(i);
            this.DrawCard[i+1].setText(card.getName()+"\n"+card.getType());
            this.DrawCard[i+1].setIcon(scaleImage(new ImageIcon(Define.IMG_PATH + card.getImagePath()), 100, 100));
            this.DrawCard[i+1].setEnabled(true);
        }
    }

    public class CommandHandler implements ActionListener {
        String command1;
        String command2;

        CommandHandler() {
            this.command1 = null;
            this.command2 = null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;
            Player player;
            if(current_player==1){
                player = p1;
            }
            else{
                player = p2;
            }
            if (this.command1 == null) {
                this.command1 = e.getActionCommand();
                System.out.println(this.command1);
                if (this.command1.equals("TC")) {
                    this.command1 = null;
                } else if (this.command1.charAt(0) == 'D') {
                    if(player.getHand().isFull()){
                        if(this.command1.charAt(1) != '0'){
                            showDrawFullPhase();
                            HandFullPanel.setVisible(true);
                            NextPhaseButton.setEnabled(false);
                            ThrowButton.setEnabled(false);
                        }
                        else{
                            nextPhase();
                            this.command1 = null;
                        }               
                    }
                    else{
                        if(this.command1.charAt(1) != '0'){
                            player.drawDeck(this.command1);
                        }   
                        nextPhase();
                        this.command1 = null;
                    }      
                }
                if (command1 != null) {
                    cardChoosen(this.command1);
                    i = command1.charAt(1) - 49;
                    if (command1.charAt(0) == 'H') {
                        if (current_player == 1) {
                            if (player.chooseCardonHand(i)=='C') {
                                disableSiezedBoard();
                            } else {
                                disableEmptyBoard();
                            }
                        }
                    }
                    if (command1.charAt(0) == 'A') {

                    }
                    if (command1.charAt(0) == 'B') {

                    }
                }
            } else {
                this.command2 = e.getActionCommand();
                if(this.command1.charAt(0)=='D'){
                    player.replaceHandFromDraw(this.command1 +" + "+ this.command2);
                    HandFullPanel.setVisible(false);
                    nextPhase();
                }
                System.out.println(this.command1 + " + " + this.command2);
                this.command1 = null;
                this.command2 = null;
                enableBoard(current_player);
                clearCardChoosen();
            }
        }
    }

    public void cardChoosen(String path) {
        int i = path.charAt(1) - 48;
        if (path.charAt(0) == 'H') {
            this.HandCard[i - 1].setBorder(BorderFactory.createLineBorder(Color.decode("#1D63DC"), 4));
        }
        if (path.charAt(0) == 'A') {
            this.P1Card[i].setBorder(BorderFactory.createLineBorder(Color.decode("#1D63DC"), 4));
        }
        if (path.charAt(0) == 'B') {
            this.P2Card[i].setBorder(BorderFactory.createLineBorder(Color.decode("#1D63DC"), 4));
        }
    }

    public void clearCardChoosen() {
        for (int i = 0; i < 5; i++) {
            this.HandCard[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.P1Card[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.P2Card[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        this.P1Card[5].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.P2Card[5].setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void disableEmptyBoard() {
        for (int i = 0; i < 5; i++) {
            if (this.current_player == 1) {
                if (this.p1.getBoard().getCharacter(i) == null) {
                    this.P1Card[i + 1].setEnabled(false);
                }
            } else {
                if (this.p2.getBoard().getCharacter(i) == null) {
                    this.P2Card[i + 1].setEnabled(false);
                }
            }
        }
    }

    public void disableSiezedBoard() {
        for (int i = 0; i < 5; i++) {
            if (this.current_player == 1) {
                if (this.p1.getBoard().getCharacter(i) != null) {
                    this.P1Card[i + 1].setEnabled(false);
                }
            } else {
                if (this.p2.getBoard().getCharacter(i) != null) {
                    this.P2Card[i + 1].setEnabled(false);
                }
            }
        }
    }

    public void enableBoard(int player_ke) {
        if (player_ke == 1) {
            for (int i = 0; i < 5; i++) {
                this.P1Card[i+1].setEnabled(true);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                this.P2Card[i+1].setEnabled(true);
            }
        }
    }

    public class HoverHandler implements MouseListener {
        String path;

        HoverHandler(String path) {
            this.path = path;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            showHover(this.path);
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public void showHover(String path) {
        Player player;
        if (current_player == 1) {
            player = p1;
        } else {
            player = p2;
        }
        int i = path.charAt(1) - 49;
        if (path.charAt(0) == 'H') {
            if (player.getHand().getCard(i) != null) {
                this.HoverTextArea.setText(player.getHand().getCard(i).getName() + "\n"
                        + player.getHand().getCard(i).getType() + "\n" + player.getHand().getCard(i).getDesc());
                this.HoverImageLabel.setIcon(scaleImage(
                        new ImageIcon(Define.IMG_PATH + player.getHand().getCard(i).getImagePath()), 200, 200));
            }
        }
        if (path.charAt(0) == 'A') {
            if (p1.getBoard().getCharacter(i) != null) {
                if (i > 0) {
                    this.HoverTextArea.setText(p1.getBoard().getCharacter(i).getName() + "\n"
                            + p1.getBoard().getCharacter(i).getType() + "\n" + p1.getBoard().getCharacter(i).getDesc());
                    this.HoverImageLabel.setIcon(scaleImage(
                            new ImageIcon(Define.IMG_PATH + p1.getBoard().getCharacter(i).getImagePath()), 200, 200));
                }
            }
        }
        if (path.charAt(0) == 'B') {
            if (p2.getBoard().getCharacter(i) != null) {
                if (i > 0) {
                    this.HoverTextArea.setText(p2.getBoard().getCharacter(i).getName() + "\n"
                            + p2.getBoard().getCharacter(i).getType() + "\n" + p2.getBoard().getCharacter(i).getDesc());
                    this.HoverImageLabel.setIcon(scaleImage(
                            new ImageIcon(Define.IMG_PATH + p2.getBoard().getCharacter(i).getImagePath()), 200, 200));
                }
            }
        }
    }

}
