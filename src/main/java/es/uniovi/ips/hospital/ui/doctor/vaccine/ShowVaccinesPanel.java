package es.uniovi.ips.hospital.ui.doctor.vaccine;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.domain.Vaccine;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.service.VaccineService;
import es.uniovi.ips.hospital.ui.doctor.DoctorDialog;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

@Component
public class ShowVaccinesPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 8070245607906932070L;
	private JPanel contentPanel = new JPanel();

	@Autowired
	private VaccineService vs;
	@Autowired
	private PatientService ps;
	@Autowired
	private DoctorDialog doctorDialog;

	private JPanel pnList;
	private JPanel pnData;
	private JLabel lblHealthcard;
	private JTextField tfSearchHealthCard;
	private JScrollPane scrollPane;
	private JList<Vaccine> listVaccines;
	private JButton btnSearch;
	private JPanel pnButtons;
	private JButton btnMarkAsApplied;
	private JButton btnEdit;

	private Doctor doctor;

	/**
	 * Create the dialog.
	 */
	public ShowVaccinesPanel() {
		setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(650, 700));
		setMinimumSize(new Dimension(650, 700));
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPnList(), BorderLayout.CENTER);
	}

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static boolean isAfter(Date date, Date reference) {
		return date.compareTo(reference) > 0;
	}

	public static boolean isBefore(Date date, Date reference) {
		return date.compareTo(reference) < 0;
	}

	/**
	 * @return Today at 00:00:00.000
	 */
	public static Date today() {
		return trunc(new Date());
	}

	/**
	 * @return Today at hh:mm:ss.mmm, for example 12/12/2012 12:10:23.021
	 */
	public static Date now() {
		return new Date();
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

	public static Date fromDdMmYyyy(int dd, int mm, int yyyy) {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.DAY_OF_MONTH, dd);
		c.set(Calendar.MONTH, mm - 1); // base 0
		c.set(Calendar.YEAR, yyyy);

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	private JPanel getPnList() {
		if (pnList == null) {
			pnList = new MyBackPanel();
			pnList.setLayout(new BorderLayout(0, 0));
			pnList.add(getPnData(), BorderLayout.NORTH);
			pnList.add(getScrollPane(), BorderLayout.CENTER);
			pnList.add(getPnButtons(), BorderLayout.SOUTH);
		}
		return pnList;
	}

	private JPanel getPnData() {
		if (pnData == null) {
			pnData = new MyFrontPanel();
			pnData.setBorder(Designer.getBorder());
			pnData.add(getLblHealthcard());
			pnData.add(getTfSearchHealthCard());
			pnData.add(getBtnSearch());
		}
		return pnData;
	}

	private JLabel getLblHealthcard() {
		if (lblHealthcard == null) {
			lblHealthcard = new JLabel("Patient's health card number");
			lblHealthcard.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHealthcard;
	}

	private JTextField getTfSearchHealthCard() {
		if (tfSearchHealthCard == null) {
			tfSearchHealthCard = new JTextField();
			tfSearchHealthCard.setColumns(10);
		}
		return tfSearchHealthCard;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListVaccines());
			scrollPane.getViewport().setBackground(PaletteFactory.getMainDark());
		}
		return scrollPane;
	}

	private JList<Vaccine> getListVaccines() {
		if (listVaccines == null) {
			listVaccines = new JList<Vaccine>();
			listVaccines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listVaccines;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tfSearchHealthCard.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill the field");
					}
					Patient patient = null;
					for (Patient p : ps.findAllPatient()) {
						if (p.getHealthCardNumber().equals(tfSearchHealthCard.getText())) {
							patient = p;
						}
					}
					if (patient == null) {
						JOptionPane.showMessageDialog(null, "Patient does not exist");
					}
					List<Vaccine> vaccinesPatient = vs.findByPatient(patient);
					Vaccine[] v = new Vaccine[vaccinesPatient.size()];
					int i = 0;
					for (Vaccine c : vaccinesPatient) {
						v[i] = c;
						i++;
					}
					listVaccines.setModel(new AbstractListModel<Vaccine>() {
						/**
						 * 
						 */
						private static final long serialVersionUID = -1222956312762187769L;
						Vaccine[] values = v;

						public int getSize() {
							return values.length;
						}

						public Vaccine getElementAt(int index) {
							return values[index];
						}
					});
				}
			});
		}
		return btnSearch;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setLayout(new GridLayout(0, 2, 0, 0));
			pnButtons.add(getBtnEdit());
			pnButtons.add(getBtnMarkAsApplied());
		}
		return pnButtons;
	}

	private JButton getBtnMarkAsApplied() {
		if (btnMarkAsApplied == null) {
			btnMarkAsApplied = new MyButton("Modify Applied");
			btnMarkAsApplied.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listVaccines.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Select an element from the list");
					} else {
						Vaccine va = listVaccines.getSelectedValue();
						if (va.isApplied()) {
							va.setApplied(false);
						} else {
							va.setApplied(true);
						}
						vs.createVaccine(va);
						Patient patient = va.getPatient();
						List<Vaccine> vaccinesPatient = vs.findByPatient(patient);
						Vaccine[] v = new Vaccine[vaccinesPatient.size()];
						int i = 0;
						for (Vaccine c : vaccinesPatient) {
							v[i] = c;
							i++;
						}
						listVaccines.setModel(new AbstractListModel<Vaccine>() {
							/**
							 * 
							 */
							private static final long serialVersionUID = -1222956312762187769L;
							Vaccine[] values = v;

							public int getSize() {
								return values.length;
							}

							public Vaccine getElementAt(int index) {
								return values[index];
							}
						});
						if (va.isApplied()) {
							JOptionPane.showMessageDialog(null, "Vaccine applied to patient " + patient.getDni());
						} else {
							JOptionPane.showMessageDialog(null, "Vaccine not applied to patient " + patient.getDni());
						}

					}

				}
			});
		}
		return btnMarkAsApplied;
	}

	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new MyButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listVaccines.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Select an element from the list");
					} else {
						doctorDialog.launchEditVaccine(listVaccines.getSelectedValue());
					}
				}
			});
		}
		return btnEdit;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public void setFocus() {
		tfSearchHealthCard.requestFocus();
	}

}
