// Nama     : Maulidar Rohmatus Sakinah
// NIM      : 235150701111002
// Kelas    : ASD - B

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class SkincareApp {
    // Data produk skincare
    private static List<SkincareProduct> produkData = new ArrayList<>();
    private JFrame frame;
    private JList<String> listbox;

    // Class untuk produk skincare
    static class SkincareProduct {
        String name;
        String ingredient;
        String skinType;
        boolean isSafe;

        // Daftar bahan aman untuk tipe kulit tertentus
        private static final List<String> SAFE_NORMAL = List.of("Hyaluronic Acid", "Niacinamide", "Ceramides", "Squalane", "Glycerin");
        private static final List<String> SAFE_DRY = List.of("Hyaluronic Acid", "Squalane", "Ceramides", "Aloe Vera", "Shea Butter");
        private static final List<String> SAFE_OILY = List.of("Salicylic Acid", "Niacinamide", "Tea Tree Oil", "Charcoal", "Witch Hazel");
        private static final List<String> SAFE_ACNE = List.of("Salicylic Acid", "Benzoyl Peroxide", "Tea Tree Oil", "Niacinamide", "Aloe Vera");

        // Constructor untuk inisialisasi produk
        SkincareProduct(String name, String ingredient, String skinType) {
            this.name = name;
            this.ingredient = ingredient;
            this.skinType = skinType;
            this.isSafe = checkSafety(ingredient, skinType); // Periksa keamanan bahan
        }

        // Metode untuk memeriksa keamanan bahan berdasarkan tipe kulit
        private boolean checkSafety(String ingredient, String skinType) {
            List<String> safeIngredients = switch (skinType.toLowerCase()) {
                case "normal" -> SAFE_NORMAL;
                case "kering" -> SAFE_DRY;
                case "berminyak" -> SAFE_OILY;
                case "berjerawat" -> SAFE_ACNE;
                default -> new ArrayList<>();
            };
            return safeIngredients.stream().anyMatch(ingredient::contains);
        }

        @Override
        public String toString() {
            return name + " - " + ingredient + " (Tipe Kulit: " + skinType + ", Aman: " + (isSafe ? "Ya" : "Tidak") + ")";
        }
    }

    // Konstruktor utama untuk memulai aplikasi
    public SkincareApp() {
        showStartPage(); // Menampilkan halaman awal
    }

    // Metode untuk menampilkan halaman awal
    private void showStartPage() {
        frame = new JFrame("Skincare Sorting Product App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel dengan latar belakang gambar
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("images/background.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        // Judul aplikasi
        JLabel titleLabel = new JLabel("Skincare Sorting Product App", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tombol "Leggo!" untuk melanjutkan ke halaman berikutnya
        JButton leggoButton = new JButton("Leggo!");
        leggoButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        leggoButton.setBackground(new Color(102, 204, 255));
        leggoButton.setForeground(Color.WHITE);
        leggoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leggoButton.addActionListener(e -> showHomePage()); // Tombol "Leggo!" untuk melanjutkan ke halaman berikutnya

        // Menambahkan komponen ke panel
        backgroundPanel.add(Box.createVerticalGlue());
        backgroundPanel.add(titleLabel);
        backgroundPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        backgroundPanel.add(leggoButton);
        backgroundPanel.add(Box.createVerticalGlue());

        frame.add(backgroundPanel);
        frame.setVisible(true);
    }

    // Halaman beranda
    private void showHomePage() {
        frame.getContentPane().removeAll();
        frame.setTitle("Beranda - Skincare Sorting Product App");

        // Panel latar belakang dengan gambar
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("images/homepage-background.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        // Label ucapan selamat datang
        JLabel welcomeLabel = new JLabel("Selamat datang di Skincare Sorting Product App!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(50, 20, 500, 30);
        backgroundPanel.add(welcomeLabel);

        // Papan informasi (seperti papan peringatan)
        JPanel infoBoard = new JPanel();
        infoBoard.setBackground(Color.WHITE);
        infoBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        infoBoard.setBounds(50, 80, 500, 150);
        infoBoard.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel(
                "<html><div style='text-align: center;'>Aplikasi ini membantu Anda memilih produk skincare terbaik.<br>" +
                        "Perhatikan bahan yang aman untuk kulit Anda!</div></html>", JLabel.CENTER);
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        infoLabel.setForeground(Color.BLACK);
        infoBoard.add(infoLabel, BorderLayout.CENTER);
        backgroundPanel.add(infoBoard);

        // Tombol "Mulai Uji Coba"
        JButton tryButton = new JButton("Mulai Uji Coba");
        tryButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        tryButton.setBackground(new Color(102, 204, 255));
        tryButton.setForeground(Color.WHITE);
        tryButton.setBounds(200, 250, 200, 50);
        tryButton.addActionListener(e -> showTrialPage());
        backgroundPanel.add(tryButton);

        frame.add(backgroundPanel);
        frame.revalidate();
        frame.repaint();
    }

    // Halaman uji coba produk
    private void showTrialPage() {
    frame.getContentPane().removeAll();
    frame.setTitle("Uji Coba Produk Skincare");

    // Panel with background
    JPanel backgroundPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon backgroundImage = new ImageIcon("images/trial-background.jpg");
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };
    backgroundPanel.setLayout(new BorderLayout());

    // Input Panel
    JPanel inputPanel = new JPanel();
    inputPanel.setOpaque(false);
    inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
    inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel nameLabel = new JLabel("Nama Produk:");
    nameLabel.setForeground(Color.WHITE);
    JTextField nameField = new JTextField();
    JLabel ingredientLabel = new JLabel("Ingredient:");
    ingredientLabel.setForeground(Color.WHITE);
    JTextField ingredientField = new JTextField();
    JLabel skinTypeLabel = new JLabel("Tipe Kulit:");
    skinTypeLabel.setForeground(Color.WHITE);
    JTextField skinTypeField = new JTextField();
    JButton addButton = new JButton("Tambahkan");

    // Styling add button
    addButton.setBackground(new Color(102, 204, 255));
    addButton.setForeground(Color.WHITE);
    addButton.setFont(new Font("SansSerif", Font.BOLD, 16));
    addButton.setFocusPainted(false);

    inputPanel.add(nameLabel);
    inputPanel.add(nameField);
    inputPanel.add(ingredientLabel);
    inputPanel.add(ingredientField);
    inputPanel.add(skinTypeLabel);
    inputPanel.add(skinTypeField);
    inputPanel.add(new JLabel());
    inputPanel.add(addButton);

    backgroundPanel.add(inputPanel, BorderLayout.NORTH);

    // List of products
    listbox = new JList<>();
    listbox.setFont(new Font("Monospaced", Font.PLAIN, 14));
    listbox.setForeground(Color.BLACK);
    listbox.setBackground(Color.WHITE);
    listbox.setBorder(BorderFactory.createLineBorder(new Color(102, 204, 255), 2));
    JScrollPane scrollPane = new JScrollPane(listbox);
    scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 3), 
                    "Daftar Produk",
                    0, 0, new Font("SansSerif", Font.BOLD, 14), Color.WHITE));
    backgroundPanel.add(scrollPane, BorderLayout.CENTER);

    // Button Panel
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setOpaque(false);

    JButton sortButton = new JButton("Sort Bahan");
    JButton recommendButton = new JButton("Rekomendasi");
    recommendButton.setEnabled(false);

    // Styling buttons
    JButton[] buttons = {sortButton, recommendButton};
    for (JButton button : buttons) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(102, 204, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 153, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(102, 204, 255));
            }
        });
        buttonPanel.add(button);
    }

    backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

    
    // Perbaiki di dalam tryButton ActionListener untuk memastikan data diupdate
    addButton.addActionListener(e -> {
        String name = nameField.getText();
        String ingredient = ingredientField.getText();
        String skinType = skinTypeField.getText();

        if (!name.isEmpty() && !ingredient.isEmpty() && !skinType.isEmpty()) {
            SkincareProduct product = new SkincareProduct(name, ingredient, skinType);
            produkData.add(product);
            updateListbox();
            nameField.setText("");
            ingredientField.setText("");
            skinTypeField.setText("");
            recommendButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    sortButton.addActionListener(e -> {
            String[] options = {"Keamanan Produk", "Nama Produk"};
            String choice = (String) JOptionPane.showInputDialog(
                    frame,
                    "Urutkan berdasarkan:",
                    "Pilih Metode Sort",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice != null) {
                if (choice.equals("Keamanan Produk")) {
                    produkData.sort((p1, p2) -> Boolean.compare(p2.isSafe, p1.isSafe));
                    JOptionPane.showMessageDialog(frame, "Produk diurutkan berdasarkan keamanan.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } else if (choice.equals("Nama Produk")) {
                    produkData.sort((p1, p2) -> p1.name.compareToIgnoreCase(p2.name));
                    JOptionPane.showMessageDialog(frame, "Produk diurutkan berdasarkan nama.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
                updateListbox();
            }
        });

        recommendButton.addActionListener(e -> {
            StringBuilder recommendedProducts = new StringBuilder("<html><div style='text-align: center;'>Produk yang aman untuk tipe kulit Anda:<br>");
            int index = 1;
            for (SkincareProduct product : produkData) {
                if (product.isSafe) {
                    recommendedProducts.append(index++).append(". ").append(product.name).append(" (").append(product.ingredient).append(")<br>");
                }
            }
            recommendedProducts.append("</div></html>");
            JOptionPane.showMessageDialog(frame, recommendedProducts.toString(), "Rekomendasi Produk", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.add(backgroundPanel);
        frame.revalidate();
        frame.repaint();
    }

    // Metode untuk memperbarui daftar produk
    private void updateListbox() {
        String[] productNames = new String[produkData.size()];
        for (int i = 0; i < produkData.size(); i++) {
            // Tambahkan nomor urut sebelum detail produk
            productNames[i] = (i + 1) + ". " + produkData.get(i).toString(); // Tambahkan nomor urut
        }
        listbox.setListData(productNames);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SkincareApp::new); // Menjalankan aplikasi
    }
}