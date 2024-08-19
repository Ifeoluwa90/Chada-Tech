import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("1. New york city NY (A place that have it all)", new ImageIcon(getClass().getResource("/resources/Newyork City.jpg")),"https://www.booking.com/city/us/new-york.en.html?aid=306742&pagename=new-york&label=msn-xlypjIJRlBY*k*41aOa_5A-80814264492844:tikwd-80814476429741:loc-4126:neo:mte:lp76548:dec:qsnew%20york%20city%20travel%20packages&utm_campaign=NY%3A%20New%20York%20State&utm_medium=cpc&utm_source=bing&utm_term=xlypjIJRlBY*k*41aOa_5A&msclkid=a0195afa31141fb907a73c4c6d8c1611&utm_content=NY%3A%20New%20York%20-%20UFI%3A20088325");
        addDestinationNameAndPicture("2. Honolulu HI (Ready for a Adventure Island)", new ImageIcon(getClass().getResource("/resources/Hawaii.jpeg")),"https://www.booking.com/region/us/hawaii.en.html?aid=2140958&pagename=hawaii&label=msn-Ptd_plljWBBWARjr6qrjLA-80745552493434:tikwd-80745763517822:loc-4126:neo:mte:lp76548:dec:qsbest%20hawaiian%20travel%20packages&utm_campaign=HI%3A%20Hawaii&utm_medium=cpc&utm_source=bing&utm_term=Ptd_plljWBBWARjr6qrjLA&msclkid=fce53ebff67a1b7d7aab5809abfaa013&utm_content=R%3A2996-hawaii");
        addDestinationNameAndPicture("3. Las Vegas NV (A city that never sleep)", new ImageIcon(getClass().getResource("/resources/Las Vegas.jpg")),"https://www.kayak.com/packages/las-vegas?sck=SEM&skipapp=true");
        addDestinationNameAndPicture("4. Orlando FL(Who doesn't love disney)", new ImageIcon(getClass().getResource("/resources/Orlando.jpeg")),"https://www.expedia.com/Orlando.d178294.Destination-Travel-Guides?semcid=US.UB.BING.DL-c-EN.PACKAGE&semdtl=a1361845580.b11279831961574194.r1.g1kwd-79989751931095:loc-190.i1.d1.e1c.j176548.k145058.f1.n1.l1o.h1e.m1&msclkid=872590a94708120a39a6f61f1ad68989");
        addDestinationNameAndPicture("5. Chicago IL(Love was born in this city)", new ImageIcon(getClass().getResource("/resources/Chicago.jpeg")),"https://www.expedia.com/Chicago.d178248.Destination-Travel-Guides?semcid=US.UB.BING.DL-c-EN.PACKAGE&semdtl=a1361052720.b11288627867125482.r1.g1kwd-80539385837494:loc-190.i1.d1.e1c.j176548.k148792.f1.n1.l1o.h1e.m1&msclkid=361a69db326b13b49b1d0f6d6c4f8851");
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        
        list.addListSelectionListener(
        		evt -> {
        			if(!evt.getValueIsAdjusting()) {
        			final TextAndIcon selectedModel = (TextAndIcon) list.getSelectedValue();
        			try {
						Desktop.getDesktop().browse(new URI(selectedModel.getUrl()));
					} catch (IOException | URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					      }
					 }
        		  }
        		);
        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);

        list.setCellRenderer(renderer);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon, String url) {
        TextAndIcon tai = new TextAndIcon(text, icon, url);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;
    private String url;

    public TextAndIcon(String text, Icon icon , String url) {
        this.text = text;
        this.icon = icon;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }
    
    public String getUrl() {
    	return url;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    
    public void setUrl(String url) {
    	this.url = url;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}