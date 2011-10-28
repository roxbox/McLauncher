package net.minecraft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LauncherFrame extends Frame {
	public static final int VERSION = 13;
	private static final long serialVersionUID = 1L;
	public Map<String, String> customParameters = new HashMap();
	public Launcher launcher;
	public LoginForm loginForm;

	public LauncherFrame() {
		super("Minecraft Launcher");

		setBackground(Color.BLACK);
		this.loginForm = new LoginForm(this);
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(this.loginForm, "Center");

		p.setPreferredSize(new Dimension(854, 480));

		setLayout(new BorderLayout());
		add(p, "Center");

		pack();
		setLocationRelativeTo(null);
		try {
			setIconImage(ImageIO.read(LauncherFrame.class
					.getResource("favicon.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				new Thread() {
					public void run() {
						try {
							Thread.sleep(30000L);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("FORCING EXIT!");
						System.exit(0);
					}
				}.start();
				if (LauncherFrame.this.launcher != null) {
					LauncherFrame.this.launcher.stop();
					LauncherFrame.this.launcher.destroy();
				}
				System.exit(0);
			}
		});
	}

	public void playCached(String userName) {
		try {
			if ((userName == null) || (userName.length() <= 0)) {
				userName = "Player";
			}
			this.launcher = new Launcher();
			this.launcher.customParameters.putAll(this.customParameters);
			this.launcher.customParameters.put("userName", userName);
			this.launcher.init();
			removeAll();
			add(this.launcher, "Center");
			validate();
			this.launcher.start();
			this.loginForm = null;
			setTitle("Minecraft");
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.toString());
		}
	}

	public void login(String userName, String password) {
		try {
			String parameters = "user=" + URLEncoder.encode(userName, "UTF-8")
					+ "&password=" + URLEncoder.encode(password, "UTF-8")
					+ "&version=" + 13;
			String result = Util.excutePost("https://login.minecraft.net/",
					parameters);
			if (result == null) {
				showError("Can't connect to minecraft.net");
				this.loginForm.setNoNetwork();
				return;
			}
			if (!result.contains(":")) {
				if (result.trim().equals("Bad login")) {
					showError("Login failed");
				} else if (result.trim().equals("Old version")) {
					this.loginForm.setOutdated();
					showError("Outdated launcher");
				} else {
					showError(result);
				}
				this.loginForm.setNoNetwork();
				return;
			}
			String[] values = result.split(":");

			this.launcher = new Launcher();
			this.launcher.customParameters.putAll(this.customParameters);
			this.launcher.customParameters.put("userName", values[2].trim());
			this.launcher.customParameters.put("latestVersion",
					values[0].trim());
			this.launcher.customParameters.put("downloadTicket",
					values[1].trim());
			this.launcher.customParameters.put("sessionId", values[3].trim());
			this.launcher.init();

			removeAll();
			add(this.launcher, "Center");
			validate();
			this.launcher.start();
			this.loginForm.loginOk();
			this.loginForm = null;
			setTitle("Minecraft");
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.toString());
			this.loginForm.setNoNetwork();
		}
	}

	private void showError(String error) {
		removeAll();
		add(this.loginForm);
		this.loginForm.setError(error);
		validate();
	}

	public boolean canPlayOffline(String userName) {
		Launcher launcher = new Launcher();
		launcher.customParameters.putAll(this.customParameters);
		launcher.init(userName, null, null, null);
		return launcher.canPlayOffline();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception localException) {
		}
		LauncherFrame launcherFrame = new LauncherFrame();
		launcherFrame.setVisible(true);
		launcherFrame.customParameters.put("stand-alone", "true");

		if (args.length >= 3) {
			String ip = args[2];
			String port = "25565";
			if (ip.contains(":")) {
				String[] parts = ip.split(":");
				ip = parts[0];
				port = parts[1];
			}

			launcherFrame.customParameters.put("server", ip);
			launcherFrame.customParameters.put("port", port);
		}

		if (args.length >= 1) {
			launcherFrame.loginForm.userName.setText(args[0]);
			if (args.length >= 2) {
				launcherFrame.loginForm.password.setText(args[1]);
				launcherFrame.loginForm.doLogin();
			}
		}
	}
}