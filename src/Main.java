import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static List<String> tags = new ArrayList<>();
    public static int currTag = 0;
    public static boolean ok = false;

    public static void main(String[] args) throws InterruptedException {
        GridLayout dialogLayout = new GridLayout(1, 0);

        JDialog selectSize = new JDialog();
        selectSize.setSize(500, 200);

        selectSize.setLayout(dialogLayout);

        JTextField sizeField = new JTextField();

        JButton selectSizeButton = new JButton("OK");
        selectSizeButton.addActionListener(new OKListener());

        selectSize.add(new JLabel("Enter HTML Document size: "), dialogLayout);
        selectSize.add(sizeField, dialogLayout);
        selectSize.add(selectSizeButton, dialogLayout);

        selectSize.setVisible(true);

        while(!ok) {
            System.out.println(ok);
            TimeUnit.MILLISECONDS.sleep(33);
        }

        int size = Integer.parseInt(sizeField.getText());

        selectSize.dispose();

        JFrame frame = new JFrame();

        frame.setTitle("HTML Editor | By Simeon Petkov");
        frame.setSize(800, 800);

        GridLayout layout = new GridLayout(0, 3);
        frame.setLayout(layout);

        JButton aTag = new JButton("Insert Tag");
        JButton bTag = new JButton("Insert Style");
        JButton cTag = new JButton("Export");
        aTag.addActionListener(new TagButtonClicked(1));

        frame.add(aTag, layout);
        frame.add(bTag, layout);
        frame.add(cTag, layout);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);

        StringBuilder builder = new StringBuilder();

        JLabel emptyLabel = new JLabel();
        JButton upButton = new JButton("^");
        JButton downButton = new JButton("v");

        List<JLabel> toRemove = new ArrayList<>();
        List<JButton> toRemove2 = new ArrayList<>();
        List<JButton> toRemove3 = new ArrayList<>();
        int removeIndex = 0;

        for(int i=0; i<size; i++) {
            JLabel newlabel = new JLabel();
            JButton currButtonUp = new JButton("^");
            JButton currButtonDown = new JButton("v");

            frame.add(newlabel, layout);
            frame.add(currButtonUp, layout);
            frame.add(currButtonDown, layout);

            toRemove.add(newlabel);
            toRemove2.add(currButtonUp);
            toRemove3.add(currButtonDown);
        }

        int w2 = frame.getWidth();
        int h2 = frame.getHeight();

        frame.setSize(w2+1, h2);
        frame.setSize(w2, h2);

        while(true) {
            System.out.println(currTag);

            if(currTag == 1) {
                frame.remove(emptyLabel);
                frame.remove(upButton);
                frame.remove(downButton);

                removeIndex ++;

                currTag = 0;

                JDialog selectTag = new JDialog();

                selectTag.setSize(500, 200);

                JButton paragraph = new JButton("Paragraph");
                JButton title = new JButton("Title");

                paragraph.addActionListener(new TagButtonClicked(1));
                title.addActionListener(new TagButtonClicked(2));

                selectTag.setLayout(dialogLayout);

                selectTag.setTitle("Select tag");

                selectTag.add(paragraph);
                selectTag.add(title);

                selectTag.setVisible(true);

                while(currTag == 0) {
                    System.out.println(currTag);
                }

                selectTag.dispose();

                ok = false;
            }

            if(currTag == 1) {
                for(int i=0; i<toRemove.size(); i++) {
                    frame.remove(toRemove.get(i));
                    frame.remove(toRemove2.get(i));
                    frame.remove(toRemove3.get(i));
                }

                frame.add(new JTextPane());
                frame.add(new JButton("^"));
                frame.add(new JButton("v"));

                int w = frame.getWidth();
                int h = frame.getHeight();

                frame.setSize(w+1, h);
                frame.setSize(w, h);

                currTag = 0;

                for(int i=removeIndex; i<toRemove.size(); i++) {
                    frame.add(toRemove.get(i));
                    frame.add(toRemove2.get(i));
                    frame.add(toRemove3.get(i));
                }
            }

            if(currTag == 2) {
                for(int i=0; i<toRemove.size(); i++) {
                    frame.remove(toRemove.get(i));
                    frame.remove(toRemove2.get(i));
                    frame.remove(toRemove3.get(i));
                }

                JDialog setTitle = new JDialog();
                setTitle.setSize(500, 200);
                setTitle.setTitle("Set Title");

                setTitle.setLayout(dialogLayout);

                JTextField field = new JTextField();

                JButton okButton = new JButton("OK");
                okButton.addActionListener(new OKListener());

                setTitle.add(new JLabel("Enter title: "));
                setTitle.add(field);
                setTitle.add(okButton);

                setTitle.setVisible(true);

                while(!ok) {
                    System.out.println(ok);
                }

                frame.add(new JLabel(field.getText()));
                frame.add(new JButton("^"));
                frame.add(new JButton("v"));

                setTitle.dispose();

                int w = frame.getWidth();
                int h = frame.getHeight();

                frame.setSize(w+1, h);
                frame.setSize(w, h);

                currTag = 0;

                ok = false;

                for(int i=removeIndex; i<toRemove.size(); i++) {
                    frame.add(toRemove.get(i));
                    frame.add(toRemove2.get(i));
                    frame.add(toRemove3.get(i));
                }
            }

            System.out.println(currTag);

            TimeUnit.MILLISECONDS.sleep(33);
        }
    }
}

class TagButtonClicked implements ActionListener {
    public int tag = 0;

    public TagButtonClicked(int tag) {
        this.tag = tag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.currTag = this.tag;
    }
}

class OKListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.ok = true;
    }
}
