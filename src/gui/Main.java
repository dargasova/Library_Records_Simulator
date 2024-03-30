package gui;

import book.educational.EducationalLiterature;
import book.fiction.FictionLiterature;
import library.Library;
import library.Subscription;
import user.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.net.URL;

public class Main extends JFrame {

    public Main(Library library) {
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

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, rightPanel);
        splitPane.setDividerLocation(900);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    private void addBooksToNode(DefaultMutableTreeNode userNode, User user) {
        Subscription subscription = user.getSubscription();
        for (EducationalLiterature eduBook : subscription.getEducationalBooksOnHand()) {
            userNode.add(new DefaultMutableTreeNode(eduBook.getName()));
        }
        for (FictionLiterature ficBook : subscription.getFictionBooksOnHand()) {
            userNode.add(new DefaultMutableTreeNode(ficBook.getName()));
        }
    }

    public static void main(String[] args) {
        Library library = Manager.initializeLibrary();

        for (User user : library.getUsers()) {
            Manager.takeBooksFromLibrary(user, library);
        }

        SwingUtilities.invokeLater(() -> {
            Main main = new Main(library);
            main.setVisible(true);
        });
    }

    protected static ImageIcon createImageIcon() {
        URL imgURL = Main.class.getResource("library.jpg");
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Файл не найден: " + "library.jpg");
            return null;
        }
    }
}