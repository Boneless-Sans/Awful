package com.boneless.projects;

import com.boneless.projects.utils.SystemUI;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator extends JFrame {
    private final StringBuilder calc = new StringBuilder();
    private final StringBuilder mathExpression = new StringBuilder();
    private final String[] symbols = { "/", "*", "-", "+" };
    List<JButton> symbolButtons = new ArrayList<>();

    public Calculator() {
        init();
        setVisible(true);
    }

    public void init() {
        setSize(300, 400);
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JLabel text = new JLabel("0");
        text.setFont(new Font("Arial", Font.PLAIN, 25));
        textPanel.add(text);
        textPanel.setPreferredSize(new Dimension(50,35));

        SystemUI.set();
        JButton dot = new JButton(".");
        JPanel buttons = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFocusable(false);
            button.setFocusPainted(false);
            button.addActionListener(e -> {
                calc.append(button.getText());
                text.setText(calc.toString());
                enableButtons(symbolButtons);
                dot.setEnabled(true);
            });
            buttons.add(button);
        }

        JButton clear = new JButton("C");
        clear.setFocusable(false);
        clear.addActionListener(e -> {
            if(text.getText() != null) {
                calc.deleteCharAt(calc.length() - 1);
                text.setText(calc.toString());
                enableButtons(symbolButtons);
                System.out.println("null?");
            }
        });

        JButton zero = new JButton("0");
        zero.setFocusable(false);
        zero.addActionListener(e -> {
            calc.append(0);
            text.setText(calc.toString());
            enableButtons(symbolButtons);
        });

        dot.setFocusable(false);
        dot.setEnabled(false);
        dot.addActionListener(e -> {
            calc.append(".");
            text.setText(calc.toString());
            dot.setEnabled(false);
        });

        buttons.add(clear);
        buttons.add(zero);
        buttons.add(dot);

        JPanel math = new JPanel(new GridLayout(4, 1));
        for (int i = 0; i < 4; i++) {
            JButton symbolButton = new JButton(symbols[i]);
            symbolButton.setFocusable(false);
            symbolButton.setEnabled(false);
            int finalI = i;
            symbolButton.addActionListener(e -> {
                mathExpression.append(symbols[finalI]);
                calc.append(" ").append(symbols[finalI]).append(" ");
                text.setText(calc.toString());
                disableButtons(symbolButtons);
            });
            math.add(symbolButton);
            symbolButtons.add(symbolButton);
        }

        JButton doMath = new JButton("=");
        doMath.setFocusable(false);
        doMath.addActionListener(e -> {
            text.setText(null);
        });

        add(textPanel, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(math, BorderLayout.EAST);
        add(doMath, BorderLayout.SOUTH);
    }

    public static int evaluateMathExpression(String mathExpression) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        if (engine == null) {
            throw new IllegalStateException("No suitable scripting engine found.");
        }

        try {
            Object result = engine.eval(mathExpression);
            if (result instanceof Number) {
                return ((Number) result).intValue();
            } else {
                throw new IllegalArgumentException("Invalid math expression");
            }
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error evaluating math expression", e);
        }
    }

    private static void disableButtons(List<JButton> buttons) {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private static void enableButtons(List<JButton> buttons) {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
