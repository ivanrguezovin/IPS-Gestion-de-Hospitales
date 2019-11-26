package es.uniovi.ips.hospital.ui.common;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import es.uniovi.ips.hospital.domain.Appointment;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.service.AppointmentService;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.util.compare.AppointmentComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

@Component
public class MedicalRecordWithoutPrescriptionPanel extends JPanel implements Shiftable {

    private static final long serialVersionUID = 5633142202939076631L;
    private final JPanel contentPanel = new JPanel();
    
    @Autowired	private AppointmentService appointmentService;
    
    private JPanel pnPatient;
    private JPanel pnTop;
    private JPanel pnTop2;
    private JPanel pnName;
    private JPanel pnId;
    private JPanel pnEmail;
	private JPanel pnHealthCard;
    private JPanel pnAddress;
    private JTable tblMedicalRecord;
    private JLabel lblName;
    private JLabel lblId;
    private JLabel lblEmail;
	private JLabel lblHealthCard;
    private JLabel lblAddress;
    private JScrollPane spMedicalRecord;

    /**
     * Create the dialog.
     */
    public MedicalRecordWithoutPrescriptionPanel() {
        setBounds(100, 100, 650, 700);
        setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(5, 0));
        contentPanel.setBackground(PaletteFactory.getBaseDark());
        contentPanel.add(getPnPatient(), BorderLayout.NORTH);
        contentPanel.add(getSpMedicalRecord(), BorderLayout.CENTER);
    }

    private JPanel getPnPatient() {
        if (pnPatient == null) {
            pnPatient = new MyBackPanel();
            pnPatient.setLayout(new GridLayout(3,2,5,5));
            pnPatient.add(getPnTop());
            pnPatient.add(getPnTop2());
            pnPatient.add(getPnAddress());
        }
        return pnPatient;
    }

    private JScrollPane getSpMedicalRecord() {
        if (spMedicalRecord == null) {
            spMedicalRecord = new JScrollPane();
            spMedicalRecord.setViewportView(getTblMedicalRecord());
            spMedicalRecord.getViewport().setBackground(PaletteFactory.getBaseDark());
        }
        return spMedicalRecord;
    }

    private JTable getTblMedicalRecord() {
        if (tblMedicalRecord == null) {
            tblMedicalRecord = new JTable();
        }
        return tblMedicalRecord;
    }
    
    private JPanel getPnTop() {
    	if (pnTop == null) {
    		pnTop = new MyBackPanel();
    		pnTop.setLayout(new GridLayout(1,2,5,5));
    		pnTop.add(getPnName());
    		pnTop.add(getPnHealthCard());
    	}
    	return pnTop;
    }
    
    private JPanel getPnTop2() {
    	if (pnTop2 == null) {
    		pnTop2 = new MyBackPanel();
    		pnTop2.setLayout(new GridLayout(1,2,5,5));
    		pnTop2.add(getPnId());
    		pnTop2.add(getPnEmail());
    	}
    	return pnTop2;
    }
    
    private JPanel getPnName() {
    	if (pnName == null) {
    		pnName = new MyFrontPanel();
    		FlowLayout flowLayout = (FlowLayout) pnName.getLayout();
    		flowLayout.setAlignment(FlowLayout.LEFT);
    		pnName.setBorder(Designer.getBorder());
    		pnName.add(getLblName());
    	}
    	return pnName;
    }

    private JLabel getLblName() {
        if (lblName == null) {
            lblName = new JLabel("Name");
        }
        return lblName;
    }
    
    private JPanel getPnId() {
    	if (pnId == null) {
    		pnId = new MyFrontPanel();
    		FlowLayout flowLayout = (FlowLayout) pnId.getLayout();
    		flowLayout.setAlignment(FlowLayout.LEFT);
    		pnId.setBorder(Designer.getBorder());
    		pnId.add(getLblId());
    	}
    	return pnId;
    }

    private JLabel getLblId() {
        if (lblId == null) {
            lblId = new JLabel("Id");
        }
        return lblId;
    }
    
    private JPanel getPnEmail() {
    	if (pnEmail == null) {
    		pnEmail = new MyFrontPanel();
    		FlowLayout flowLayout = (FlowLayout) pnEmail.getLayout();
    		flowLayout.setAlignment(FlowLayout.LEFT);
    		pnEmail.setBorder(Designer.getBorder());
    		pnEmail.add(getLblEmail());
    	}
    	return pnEmail;
    }

    private JLabel getLblEmail() {
        if (lblEmail == null) {
            lblEmail = new JLabel("Email");
        }
        return lblEmail;
    }
    
    private JPanel getPnHealthCard() {
    	if (pnHealthCard == null) {
    		pnHealthCard = new MyFrontPanel();
    		FlowLayout flowLayout = (FlowLayout) pnHealthCard.getLayout();
    		flowLayout.setAlignment(FlowLayout.LEFT);
    		pnHealthCard.setBorder(Designer.getBorder());
    		pnHealthCard.add(getLblHealthCard());
    	}
    	return pnHealthCard;
    }

    private JLabel getLblHealthCard() {
        if (lblHealthCard == null) {
            lblHealthCard = new JLabel("SSN");
        }
        return lblHealthCard;
    }
    
    private JPanel getPnAddress() {
    	if (pnAddress == null) {
    		pnAddress = new MyFrontPanel();
    		FlowLayout flowLayout = (FlowLayout) pnAddress.getLayout();
    		flowLayout.setAlignment(FlowLayout.LEFT);
    		pnAddress.setBorder(Designer.getBorder());
    		pnAddress.add(getLblAddress());
    	}
    	return pnAddress;
    }

    private JLabel getLblAddress() {
        if (lblAddress == null) {
            lblAddress = new JLabel("Address");
        }
        return lblAddress;
    }

    // METODOS Y CLASES DE INICIALIZACION ------------------------------------------------------------

    public void showHistoryOf(Patient patient) {
        lblName.setText("Patient: " + patient.getName() + " " + patient.getSurname());
        lblId.setText("Identification: " + patient.getDni());
        lblEmail.setText("Email: " + patient.getEmail());
        lblHealthCard.setText("SSN: " + patient.getHealthCardNumber());
        lblAddress.setText("Address: " + patient.getAddress().guiToString());
        EventList<Appointment> eventList = new BasicEventList<>();
        eventList.addAll(appointmentService.findAllByPatient(patient));
        SortedList<Appointment> sortedList = new SortedList<>(eventList, new AppointmentComparator());
        AdvancedTableModel<Appointment> tableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(sortedList, new RecordTableFormat());
        tblMedicalRecord.setModel(tableModel);
    }

	@Override
	public void setFocus() {
		tblMedicalRecord.requestFocus();
	}
	
	/////////////////////////////////////////////////////////////////////////////////

    private class RecordTableFormat implements TableFormat<Appointment> {

        public int getColumnCount() {
            return 3;
        }

        public String getColumnName(int column) {
            if (column == 0) return "Time";
            else if (column == 1) return "Doctor";
            else if (column == 2) return "Room";

            throw new IllegalStateException();
        }

        public Object getColumnValue(Appointment appointment, int column) {
            if (column == 0) return appointment.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            else if (column == 1) return appointment.prettifyDoctors();
            else if (column == 2) return appointment.getRoom().getLocation();
            throw new IllegalStateException();
        }
    }
}
