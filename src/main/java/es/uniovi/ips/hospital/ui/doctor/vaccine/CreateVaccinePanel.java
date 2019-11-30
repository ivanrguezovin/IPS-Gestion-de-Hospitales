package es.uniovi.ips.hospital.ui.doctor.vaccine;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.domain.Vaccine.VaccineType;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.VaccineService;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyDateChooser;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.swing.BoxLayout;

@Component
public class CreateVaccinePanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 5459106730873677461L;
	private JPanel contentPanel = new JPanel();

	@Autowired
	private VaccineService vs;
	@Autowired
	private PatientService ps;

	private JPanel panel;
	private JPanel pnType;
	private JLabel lblType;
	private JComboBox<VaccineType> cbType;
	private JPanel pnDescription;
	private JLabel lblDescription;
	private JTextArea tADescription;
	private JPanel pnHealthcard;
	private JLabel lblHealthcard;
	private JTextField tFHealthCard;
	private JPanel pnDate;
	private JLabel lblDate;
	private JDateChooser dateChooser;
	private JButton btnCreate;
	
	private List<Vaccine> vacunas = new ArrayList<>();

	/**
	 * Create the dialog.
	 */
	public CreateVaccinePanel() {
		setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(650, 700));
		setMinimumSize(new Dimension(650, 700));
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPanel(), BorderLayout.CENTER);
		contentPanel.add(getBtnCreate(), BorderLayout.SOUTH);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new MyBackPanel();
			GridBagLayout gbl_pn = new GridBagLayout();
			gbl_pn.columnWeights = new double[] { };
			gbl_pn.columnWidths = new int[] { 100, 450, 100 };
			gbl_pn.rowHeights = new int[] { 75, 50, 50, 50, 50, 50, 50, 200, 75 };
			panel.setLayout(gbl_pn);
			GridBagConstraints gbc_pnType = new GridBagConstraints();
			gbc_pnType.insets = new Insets(5, 5, 5, 5);
			gbc_pnType.fill = GridBagConstraints.BOTH;
			gbc_pnType.gridx = 1;
			gbc_pnType.gridy = 1;
			panel.add(getPnType(), gbc_pnType);
			GridBagConstraints gbc_pnHealthcard = new GridBagConstraints();
			gbc_pnHealthcard.insets = new Insets(0, 0, 5, 0);
			gbc_pnHealthcard.fill = GridBagConstraints.BOTH;
			gbc_pnHealthcard.gridx = 1;
			gbc_pnHealthcard.gridy = 3;
			panel.add(getPnHealthcard(), gbc_pnHealthcard);
			GridBagConstraints gbc_pnDate = new GridBagConstraints();
			gbc_pnDate.insets = new Insets(0, 0, 5, 0);
			gbc_pnDate.fill = GridBagConstraints.BOTH;
			gbc_pnDate.gridx = 1;
			gbc_pnDate.gridy = 5;
			panel.add(getPnDate(), gbc_pnDate);
			GridBagConstraints gbc_pnDescription = new GridBagConstraints();
			gbc_pnDescription.insets = new Insets(0, 0, 5, 0);
			gbc_pnDescription.fill = GridBagConstraints.BOTH;
			gbc_pnDescription.gridx = 1;
			gbc_pnDescription.gridy = 7;
			panel.add(getPnDescription(), gbc_pnDescription);
		}
		return panel;
	}
	
	private JPanel getPnType() {
		if (pnType == null) {
			pnType = new MyFrontPanel();
			pnType.setBorder(Designer.getBorder());
			pnType.add(getLblType());
			pnType.add(getCbType());
		}
		return pnType;
	}

	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel("Type");
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblType;
	}

	private JComboBox<VaccineType> getCbType() {
		if (cbType == null) {
			cbType = new JComboBox<VaccineType>();
			cbType.addItem(VaccineType.VIVAS_ATENUADAS);
			cbType.addItem(VaccineType.INACTIVADAS);
			cbType.addItem(VaccineType.CON_TOXOIDES);
			cbType.addItem(VaccineType.SUBUNIDADES_RECOMBINANTES_POLISACÁRIDAS_Y_COMBINADAS);
		}
		return cbType;
	}
	
	private JPanel getPnHealthcard() {
		if (pnHealthcard == null) {
			pnHealthcard = new MyFrontPanel();
			pnHealthcard.setBorder(Designer.getBorder());
			pnHealthcard.add(getLblHealthcard());
			pnHealthcard.add(getTFHealthCard());
		}
		return pnHealthcard;
	}

	private JLabel getLblHealthcard() {
		if (lblHealthcard == null) {
			lblHealthcard = new JLabel("Patient´s health card");
			lblHealthcard.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHealthcard;
	}

	private JTextField getTFHealthCard() {
		if (tFHealthCard == null) {
			tFHealthCard = new JTextField();
			tFHealthCard.setColumns(10);
		}
		return tFHealthCard;
	}
	
	private JPanel getPnDate() {
		if (pnDate == null) {
			pnDate = new MyFrontPanel();
			pnDate.setBorder(Designer.getBorder());
			pnDate.add(getLblDate());
			pnDate.add(getDateChooser());
		}
		return pnDate;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
			lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDate;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new MyDateChooser();
		}
		return dateChooser;
	}
	
	private JPanel getPnDescription() {
		if (pnDescription == null) {
			pnDescription = new MyFrontPanel();
			pnDescription.setBorder(Designer.getBorder());
			pnDescription.setLayout(new BoxLayout(pnDescription, BoxLayout.Y_AXIS));
			pnDescription.add(getLblDescription());
			pnDescription.add(getTADescription());
		}
		return pnDescription;
	}

	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("Description");
			lblDescription.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDescription;
	}

	private JTextArea getTADescription() {
		if (tADescription == null) {
			tADescription = new JTextArea();
			tADescription.setWrapStyleWord(true);
			tADescription.setLineWrap(true);
		}
		return tADescription;
	}

	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new MyButton("Create");
			btnCreate.setMnemonic('c');
			btnCreate.addActionListener(e -> createVaccine());
		}
		return btnCreate;
	}

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * @return Today at 00:00:00.000
	 */
	public static Date today() {
		return trunc(new Date());
	}

	public static boolean isBefore(Date date, Date reference) {
		return date.compareTo(reference) < 0;
	}

	/**
	 * Truncates a date by setting hh:mm:ss to 00:00:00. For example for date
	 * "12/02/2012 13:24:34" returns "12/02/2012 00:00:00.000" It is useful for
	 * comparing dates in the same day.
	 * 
	 * @param date
	 * @return
	 */
	public static Date trunc(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	//////////////////////////////////////////////////////////////////

	public List<Vaccine> getVacunas() {
		return vacunas;
	}

	@Override
	public void setFocus() {
		cbType.requestFocus();
	}
	
	private void createVaccine() {
		if(getCbType().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Select vaccine type");
		}else if(getTFHealthCard().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Type a health card number");
		}else if(!getTFHealthCard().getText().matches("[0-9]+") && getTFHealthCard().getText().length() != 10) {
			JOptionPane.showMessageDialog(this, "Type a correct health card number");
		}else if(getTADescription().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Type a description");
		}else if(isBefore(getDateChooser().getDate(),today())) {
			JOptionPane.showMessageDialog(this, "Date must not be before today. Today is " + new Date());
		}else {
			String desc = getTADescription().getText();
			VaccineType vt = (VaccineType) getCbType().getSelectedItem();
			LocalDateTime ldt = convertToLocalDateTimeViaInstant(getDateChooser().getDate());
			String hcn = tFHealthCard.getText();
			List<Patient> patients = ps.findAllPatient();
			Patient patient = null;
			for(Patient p: patients) {
				if(p.getHealthCardNumber().equals(hcn)) {
					patient = p;
				}
			}
			if(patient != null) {
				Vaccine v = new Vaccine(vt,desc,ldt,false,patient);
				vs.createVaccine(v);
				JOptionPane.showMessageDialog(null, "Vaccine added to " + patient.getDni());
				tADescription.setText("");
				tFHealthCard.setText("");
				getDateChooser().setDate(null);
			}else {
				JOptionPane.showMessageDialog(this, "Patient does not exist");
			}
			
		}
	}
}
