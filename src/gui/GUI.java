package gui;

import book.Book;
import library.Library;
import library.LibraryManager;
import user.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GUI extends JFrame {

    public GUI(Library library) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Библиотека");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Пользователи");

        DefaultMutableTreeNode studentsNode = new DefaultMutableTreeNode("Студенты", true);
        DefaultMutableTreeNode teachersNode = new DefaultMutableTreeNode("Преподаватели", true);
        for (User user : library.getUsers()) {
            DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(user.getFullName());
            addBooksToNode(userNode, user);
            if (user instanceof Student) {
                studentsNode.add(userNode);
            } else if (user instanceof Teacher) {
                teachersNode.add(userNode);
            }
        }
        rootNode.add(studentsNode);
        rootNode.add(teachersNode);

        JTree tree = new JTree(rootNode);
        JScrollPane treeScrollPane = new JScrollPane(tree);

        JLabel imageLabel = new JLabel();
        ImageIcon icon = createImageIcon();
        assert icon != null;
        imageLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(500, 650, Image.SCALE_DEFAULT)));

        JLabel titleLabel = new JLabel("Библиотека", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(imageLabel);
        rightPanel.add(centerPanel, BorderLayout.CENTER);

        JButton newGenerationButton = new JButton("Новая генерация");
        newGenerationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    LibraryManager libraryManager = new LibraryManager("Моя библиотека");
                    libraryManager.generateBooks();
                    Library newLibrary = libraryManager.getLibrary();

                    Main.generateTeachers(libraryManager.getLibrarian());
                    Main.generateStudents(libraryManager.getLibrarian());
                    for (User user : newLibrary.getUsers()) {
                        Main.takeBooksFromLibrary(user, libraryManager.getLibrarian(), newLibrary);
                    }

                    GUI gui = new GUI(newLibrary);
                    gui.setVisible(true);
                });
            }
        });

        getContentPane().add(newGenerationButton, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, rightPanel);
        splitPane.setDividerLocation(900);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    private void addBooksToNode(DefaultMutableTreeNode userNode, User user) {
        for (Book book : user.getSubscription().getBooksOnHand()) {
            userNode.add(new DefaultMutableTreeNode(book.getName()));
        }
    }

    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager("Моя библиотека");
        libraryManager.generateBooks();
        Library library = libraryManager.getLibrary();

        Main.generateTeachers(libraryManager.getLibrarian());
        Main.generateStudents(libraryManager.getLibrarian());
        for (User user : library.getUsers()) {
            Main.takeBooksFromLibrary(user, libraryManager.getLibrarian(), library);
        }

        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI(library);
            gui.setVisible(true);
        });
    }

    protected static ImageIcon createImageIcon() {
        URL imgURL = GUI.class.getResource("library.jpg");
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Файл не найден: " + "library.jpg");
            return null;
        }
    }
}