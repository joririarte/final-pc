package gui;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import searchEngine.SearchEngine;

import java.awt.*;
import java.util.concurrent.*;

public class SearchGUI {
    private JFrame frame;
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton searchButton;
    private JLabel statusLabel;
    private SearchEngine searchEngine;

    public SearchGUI() {
        searchEngine = new SearchEngine();
    }

    public void createAndShowGUI() {
        frame = new JFrame("Buscador Concurrente Optimizado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JLabel titleLabel = new JLabel("🔍 Buscador de Registros", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        searchField = new JTextField();
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        searchButton = new JButton("Buscar");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        inputPanel.add(searchField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);

        topPanel.add(inputPanel, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setEditable(false);
        if(!this.searchEngine.getData().isEmpty())
            resultArea.setText(String.join("\n", this.searchEngine.getData()));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        statusLabel = new JLabel("Escribe una consulta y presiona 'Buscar'", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        frame.add(statusLabel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> performSearch());
        searchField.addActionListener(e -> performSearch());
        frame.setVisible(true);
    }

    private void performSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            if(!this.searchEngine.getData().isEmpty())
                resultArea.setText(String.join("\n", this.searchEngine.getData()));
            return;
        }

        resultArea.setText("");
        statusLabel.setText("Buscando coincidencias para: '" + query + "'...");
        searchButton.setEnabled(false);
        searchField.setEnabled(false);

        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            ConcurrentHashMap<Integer, String> results = searchEngine.search(query);
            long duration = System.currentTimeMillis() - startTime;

            SwingUtilities.invokeLater(() -> {
                if(results == null) {
                    resultArea.setText("Ocurrio un error en la busqueda.");
                    return;
                }
                if (results.isEmpty()) {
                    resultArea.setText("No se encontraron resultados.");
                } else {
                    resultArea.setText(String.join("\n", results.values()));
                }
                statusLabel.setText("Resultados encontrados: " + results.size() + " | Tiempo: " + duration + " ms");
                searchButton.setEnabled(true);
                searchField.setEnabled(true);
                searchField.grabFocus();
            });
        }).start();
    }
}