import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener; // Remove the unused import statement

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class SlideShow extends JFrame {

	//Declare Variables
	private JPanel slidePane = new JPanel();
	private JPanel textPane = new JPanel();
	private JPanel buttonPane = new JPanel();
	private JPanel southPane = new JPanel();
	private CardLayout card = new CardLayout();
	private CardLayout cardText = new CardLayout();
	private JButton btnPrev = new JButton();
	private JButton btnNext = new JButton();
	
    // Get Destination Information, Image and URL
	private List<Slide> slides = Arrays.asList(
		new Slide(
        "<font size='5'>#1 Shamanic Energy Healing & Detox Retreat in Virginia,USA</font> <br>To reawaken your inner spark and integrate spiritual sensitivity into everyday life.",
		"/resources/D1.jpg",
		"https://bookretreats.com/r/3-day-shamanic-energy-healing-detox-retreat-in-virginia-usa"),
		new Slide(
        "<font size='5'>#2 Goddess Detox Retreat in California, USA </font> <br>Escape the high-stress, fast-paced hustle and bustle of modern life",
		"/resources/D2.jpeg",
		"https://bookretreats.com/r/3-day-goddess-detox-retreat-in-california-usa"),
		new Slide(
        "<font size='5'>#3 Renew & Restore Retreat For Women in Hawaii, USA </font> <br>The ideal location to unplug and enjoy the beauty of nature",
		"/resources/D3.jpeg",
		"https://bookretreats.com/r/7-day-renew-restore-retreat-for-women-in-hawaii-us"),
		new Slide(
        "<font size='5'>#4 Divine Raw Energy Retreat</font> <br>Peace and quiet for the mind, refreshing energy for the body",
		"/resources/D4.jpeg",
		"https://bookretreats.com/r/3-day-divine-raw-energy-retreat-fulfill-and-reset-your-life-us"),
		new Slide(
        "<font size='5'>#5 Happiness' Meditation (Weekday) Retreat</font> <br>A place where you can care for yourself without distractions",
		"/resources/D5.jpeg",
		"https://bookretreats.com/r/3-day-happiness-meditation-weekday-retreat-in-the-us")
	);

	/**
	 * Create the application.
	 */
	public SlideShow() throws HeadlessException {
		initComponent();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponent() {
		//Initialize variables to empty objects
		
		textPane.setBackground(Color.YELLOW);
		textPane.setBounds(5, 470, 790, 50);
		textPane.setVisible(true);
		southPane.setLayout(new BoxLayout(southPane, BoxLayout.Y_AXIS));

		//Setup frame attributes
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Top 5 Destinations SlideShow");
		getContentPane().setLayout(new BorderLayout(10, 50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Setting the layouts for the panels
		slidePane.setLayout(card);
		textPane.setLayout(cardText);
		
		//logic to add each of the slides and text
		slides.forEach(s -> {
			final JLabel imageLabel = new JLabel(s.getImage());
			imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(s.getUrl()));
					} catch (java.io.IOException e) {
						System.out.println(e.getMessage());
					}
				}
			});
			slidePane.add(imageLabel);
			textPane.add(new JLabel(s.getDescription()));
		});

		getContentPane().add(slidePane, BorderLayout.CENTER);
		southPane.add(textPane);

		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		btnPrev.setText("Previous");
		btnPrev.addActionListener((ActionEvent e) -> goPrevious());

		buttonPane.add(btnPrev);

		btnNext.setText("Next");
		btnNext.addActionListener((ActionEvent e) -> goNext());
		buttonPane.add(btnNext);

		southPane.add(buttonPane);

		getContentPane().add(southPane, BorderLayout.SOUTH);
	}

	/**
	 * Previous Button Functionality
	 */
	private void goPrevious() {
		card.previous(slidePane);
		cardText.previous(textPane);
	}
	
	/**
	 * Next Button Functionality
	 */
	private void goNext() {
		card.next(slidePane);
		cardText.next(textPane);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> (new SlideShow ()).setVisible(true));
	}

	class Slide {
		private final String description;
		private final String image;
		private final String url;

		public Slide(final String description, final String image, final String url) {
			super();
			this.description = "<html><body>" + description + "</body></html>";
			this.image = "<html><body><img width= '800' height='500' src='" + getClass().getResource(image) + "'</body></html>";
			this.url = url;
		}

		public String getDescription() {
			return description;
		}
		public String getImage() {
			return image;
		}
		public String getUrl() {
			return url;
		}

	}
}
