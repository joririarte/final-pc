package gui;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import searchEngine.SearchEngine;
import utils.ia.OptimizerFactory.ModoOptimizacion;

import java.awt.*;
import java.util.concurrent.*;

public class SearchGUI {
    private JFrame frame;
    private JTextField searchField;
    private JTextArea resultArea;
    private JLabel statusLabel;
    private SearchEngine searchEngine;
    private JComboBox<String> optimizerSelector;


    public SearchGUI() {
        searchEngine = new SearchEngine(ModoOptimizacion.DUMMY_OPTIMIZER);
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

        searchField = new JTextField();
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        // 🔽 NUEVO: Selector de optimización
        JPanel optimizerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel optimizerLabel = new JLabel("Modo:");
        optimizerLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        optimizerSelector = new JComboBox<>(new String[] { "Dummy Optimizer", "IA Optimizer" });
        optimizerSelector.setFont(new Font("SansSerif", Font.PLAIN, 14));
        optimizerSelector.setSelectedIndex(0); // por defecto: Dummy

        optimizerPanel.add(optimizerLabel);
        optimizerPanel.add(optimizerSelector);

        // 🔽 NUEVO: Panel para alinear el input y el combo
        JPanel upperInputPanel = new JPanel(new BorderLayout(5, 5));
        upperInputPanel.add(searchField, BorderLayout.CENTER);
        upperInputPanel.add(optimizerPanel, BorderLayout.EAST);

        topPanel.add(upperInputPanel, BorderLayout.SOUTH);

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
        
        frame.setVisible(true);

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

        optimizerSelector.addActionListener(e -> {
            int selectedIndex = optimizerSelector.getSelectedIndex();
            searchEngine = new SearchEngine(ModoOptimizacion.getModoOptimizacion(selectedIndex));
        });

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
                searchField.setEnabled(true);
                searchField.grabFocus();
            });
        }).start();
    }
}