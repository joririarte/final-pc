import javax.swing.SwingUtilities;

import gui.SearchGUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchGUI gui = new SearchGUI();
            gui.createAndShowGUI();
        });
    }
}