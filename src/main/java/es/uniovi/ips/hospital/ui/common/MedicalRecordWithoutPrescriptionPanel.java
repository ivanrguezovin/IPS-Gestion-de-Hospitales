package es.uniovi.ips.hospital.ui.common;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import es.uniovi.ips.hospital.domain.MedicalRecord;
import es.uniovi.ips.hospital.domain.Patient;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;
import es.uniovi.ips.hospital.util.compare.MedicalRecordComparator;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

@Component
public class MedicalRecordWithoutPrescriptionPanel extends JPanel implements Shiftable {

    private static final long serialVersionUID = 5633142202939076631L;
    private final JPanel contentPanel = new JPanel();
    
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
        EventList<MedicalRecord> eventList = new BasicEventList<MedicalRecord>();
		eventList.addAll(patient.getMedicalRecords());
		SortedList<MedicalRecord> sortedList = new SortedList<MedicalRecord>(eventList, new MedicalRecordComparator());
		AdvancedTableModel<MedicalRecord> tableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(sortedList, new RecordTableFormat());
		tblMedicalRecord.setModel(tableModel);
		TableComparatorChooser.install(tblMedicalRecord, sortedList, TableComparatorChooser.MULTIPLE_COLUMN_MOUSE);
    }

	@Override
	public void setFocus() {
		tblMedicalRecord.requestFocus();
	}
	
	/////////////////////////////////////////////////////////////////////////////////

    private class RecordTableFormat implements TableFormat<MedicalRecord> {

	    public int getColumnCount() {
	        return 2;
	    }
	    public String getColumnName(int column) {
	        if(column == 0)      return "Day";
	        else if(column == 1) return "Description";

	        throw new IllegalStateException();
	    }
	    public Object getColumnValue(MedicalRecord record, int column) {
	        if(column == 0)      return record.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	        else if(column == 1) return record.getDescription();
	        throw new IllegalStateException();
	    }
	}
}
