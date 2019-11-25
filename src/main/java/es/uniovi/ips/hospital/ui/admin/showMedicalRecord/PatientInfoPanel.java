package es.uniovi.ips.hospital.ui.admin.showMedicalRecord;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.PatientService;
import es.uniovi.ips.hospital.ui.common.MedicalRecordDialogWithoutPrescription;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.ui.util.filter.PatientTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.PatientCellRenderer;
import es.uniovi.ips.hospital.util.compare.PatientComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class PatientInfoPanel extends JPanel implements Shiftable {

    private static final long serialVersionUID = 8434535298528019736L;
    private final JPanel contentPanel = new JPanel();
    
    @Autowired	private MedicalRecordDialogWithoutPrescription medicalRecordDialogWithoutPrescription;
    @Autowired	private PatientService patientService;

    private JPanel panel;
    private JTextField textField;
    private TextMatcherEditor<Patient> textMatcherEditor;
    private JScrollPane scrollPane;
    private JList<Patient> list;
    private FilterList<Patient> patients;
    private JButton btnShowInfo;

    /**
     * Create the dialog.
     */
    public PatientInfoPanel() {
        setPreferredSize(new Dimension(700, 500));
        setBounds(100, 100, 700, 292);
        setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(PaletteFactory.getBaseDark());
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(5, 5));
        contentPanel.add(getPanel(), BorderLayout.CENTER);     
        contentPanel.add(getBtnShowInfo(), BorderLayout.SOUTH);
    }
    private JPanel getPanel() {
    	if (panel == null) {
    		panel = new MyFrontPanel();
    		panel.setBorder(Designer.getBorder());
    		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    		panel.add(Box.createRigidArea(new Dimension(0, 5)));
    		panel.add(getTextField());
    		panel.add(Box.createRigidArea(new Dimension(0, 5)));
    		panel.add(getScrollPane());
    	}
    	return panel;
    }
    
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setMaximumSize(new Dimension(2147483647, 200));
            textMatcherEditor = new TextComponentMatcherEditor<Patient>(textField, new PatientTextFilterator());
		}
		return textField;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JList<Patient> getList() {
		if (list == null) {
			list = new JList<Patient>();
            list.addMouseListener(new DoubleClickSelector());
            list.setCellRenderer(new PatientCellRenderer());
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return list;
	}
	private JButton getBtnShowInfo() {
		if (btnShowInfo == null) {
			btnShowInfo = new MyButton("Show info");
			btnShowInfo.addActionListener(e -> showMedicalRecord(list.getSelectedValue()));
		}
		return btnShowInfo;
	}
	
	//////////////////////////////////////////////////////////////////////
	
	public void fillList() {
        EventList<Patient> patientlist = new BasicEventList<Patient>();
        patientlist.addAll(patientService.findAllPatient());
        SortedList<Patient> sortedList = new SortedList<Patient>(patientlist, new PatientComparator());
        patients = new FilterList<Patient>(sortedList, textMatcherEditor);
        list.setModel(GlazedListsSwing.eventListModelWithThreadProxyList(patients));
    }

	@Override
	public void setFocus() {
		list.requestFocus();
	}
    
    private void showMedicalRecord(Patient patient) {
        System.out.println(patient);
        medicalRecordDialogWithoutPrescription.showHistoryOf(patient);
    }
	
	//////////////////////////////////////////////////////////////////////
	
	private class DoubleClickSelector extends MouseAdapter {

        @SuppressWarnings("rawtypes")
        @Override
        public void mouseClicked(MouseEvent e) {
        	JList list = (JList) e.getSource();
            if (e.getClickCount() == 2) {
                Patient selected = (Patient) list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
            	showMedicalRecord(selected);
            }
        }
    }
}
