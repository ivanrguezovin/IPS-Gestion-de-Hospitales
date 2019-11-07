package es.uniovi.ips.hospital.ui.admin.schedule;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JCalendar;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.domain.Schedule;
import es.uniovi.ips.hospital.domain.Staff;
import es.uniovi.ips.hospital.exception.BusinessException;
import es.uniovi.ips.hospital.exception.InputException;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.service.ScheduleService;
import es.uniovi.ips.hospital.ui.util.filter.StaffTextFilterator;
import es.uniovi.ips.hospital.ui.util.render.StaffCellRenderer;
import es.uniovi.ips.hospital.util.compare.StaffComparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ventana prototipo de la gesti�n de horarios
 *
 * @author Ricardo Soto (uo265710)
 */
@Component
public class ManageWorkScheduleDialog extends JDialog {

    private static final long serialVersionUID = 1490803176753932725L;
    //private static final String[] DAYS = {"l", "m", "x", "j", "v", "s", "d"};
    private final JPanel contentPanel = new JPanel();
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private NurseService nurseService;
    @Autowired
    private ScheduleService scheduleService;
    private JPanel pnCenter;
    private JPanel pnSouth;
    private JButton btnAdd;
    private JPanel pnDays;
    private JPanel pnCalendars;
    private JPanel pnTimeSelectors;
    private JCheckBox checkBoxL;
    private JCheckBox checkBoxM;
    private JCheckBox checkBoxX;
    private JCheckBox checkBoxJ;
    private JCheckBox checkBoxV;
    private JCheckBox checkBoxS;
    private JCheckBox checkBoxD;
    private JPanel pnStartTime;
    private JPanel pnEndTime;
    private JLabel lblStartTime;
    private JLabel lblEndTime;
    private JPanel pnStartDate;
    private JPanel pnEndDate;
    private JCalendar calStartDate;
    private JCalendar calEndDate;
    private JPanel pnWest;
    private JTextField txtFilter;
    private MatcherEditor<Staff> textMatcherEditor;
    private JTabbedPane tbPnStaffList;
    private JList<Doctor> lstDoctor;
    private JList<Nurse> lstNurse;
    private JList<Staff> lstSelected;
    private FilterList<Doctor> doctorList;
    private FilterList<Nurse> nurseList;
    private FilterList<Staff> selectedList;
    private TimePicker tpStart;
    private TimePicker tpEnd;
    private JScrollPane spSelected;
    private JScrollPane spDoctors;
    private JScrollPane spNurses;
    private JButton btnSelect;
    private EntriesSelector selector;
    private EntriesRemover remover;
    private JCheckBox checkBoxAll;

    /**
     * Create the dialog.
     */
    public ManageWorkScheduleDialog() {
    	setTitle("Administrator: create a new schedule");
    	setModal(true);
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(getPnCenter(), BorderLayout.CENTER);
        contentPanel.add(getPnSouth(), BorderLayout.SOUTH);
        contentPanel.add(getPnWest(), BorderLayout.WEST);
    }

    // METODOS DE CREACION DE LA INTERFAZ ------------------------------------------------------------

    private JPanel getPnCenter() {
        if (pnCenter == null) {
            pnCenter = new JPanel();
            pnCenter.setBorder(new TitledBorder(null, "Schedule", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnCenter.setLayout(new BorderLayout(0, 0));
            pnCenter.add(getPnDays(), BorderLayout.NORTH);
            pnCenter.add(getPnCalendars(), BorderLayout.CENTER);
            pnCenter.add(getPnTimeSelectors(), BorderLayout.SOUTH);
        }
        return pnCenter;
    }

    private JPanel getPnSouth() {
        if (pnSouth == null) {
            pnSouth = new JPanel();
            FlowLayout flowLayout = (FlowLayout) pnSouth.getLayout();
            flowLayout.setAlignment(FlowLayout.RIGHT);
            pnSouth.add(getBtnAdd());
        }
        return pnSouth;
    }

    private JButton getBtnAdd() {
        if (btnAdd == null) {
            btnAdd = new JButton("Add");
            btnAdd.addActionListener(new DataSaver());
            btnAdd.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return btnAdd;
    }

    private JPanel getPnDays() {
        if (pnDays == null) {
            pnDays = new JPanel();
            pnDays.setLayout(new GridLayout(0, 7, 0, 0));
            pnDays.add(getCheckBoxL());
            pnDays.add(getCheckBoxM());
            pnDays.add(getCheckBoxX());
            pnDays.add(getCheckBoxJ());
            pnDays.add(getCheckBoxV());
            pnDays.add(getCheckBoxS());
            pnDays.add(getCheckBoxD());
            pnDays.add(Box.createRigidArea(new Dimension(10, 10)));
            pnDays.add(Box.createRigidArea(new Dimension(10, 10)));
            pnDays.add(Box.createRigidArea(new Dimension(10, 10)));
            pnDays.add(getCheckBoxAll());
        }
        return pnDays;
    }

    private JPanel getPnCalendars() {
        if (pnCalendars == null) {
            pnCalendars = new JPanel();
            pnCalendars.setLayout(new GridLayout(0, 2, 0, 0));
            pnCalendars.add(getPnStartDate());
            pnCalendars.add(getPnEndDate());
        }
        return pnCalendars;
    }

    private JPanel getPnTimeSelectors() {
        if (pnTimeSelectors == null) {
            pnTimeSelectors = new JPanel();
            pnTimeSelectors.setLayout(new GridLayout(0, 2, 0, 0));
            pnTimeSelectors.add(getPnStartTime());
            pnTimeSelectors.add(getPnEndTime());
        }
        return pnTimeSelectors;
    }

    private JCheckBox getCheckBoxL() {
        if (checkBoxL == null) {
            checkBoxL = new JCheckBox("L");
        }
        return checkBoxL;
    }

    private JCheckBox getCheckBoxM() {
        if (checkBoxM == null) {
            checkBoxM = new JCheckBox("M");
        }
        return checkBoxM;
    }

    private JCheckBox getCheckBoxX() {
        if (checkBoxX == null) {
            checkBoxX = new JCheckBox("X");
        }
        return checkBoxX;
    }

    private JCheckBox getCheckBoxJ() {
        if (checkBoxJ == null) {
            checkBoxJ = new JCheckBox("J");
        }
        return checkBoxJ;
    }

    private JCheckBox getCheckBoxV() {
        if (checkBoxV == null) {
            checkBoxV = new JCheckBox("V");
        }
        return checkBoxV;
    }

    private JCheckBox getCheckBoxS() {
        if (checkBoxS == null) {
            checkBoxS = new JCheckBox("S");
        }
        return checkBoxS;
    }

    private JCheckBox getCheckBoxD() {
        if (checkBoxD == null) {
            checkBoxD = new JCheckBox("D");
        }
        return checkBoxD;
    }

    private JCheckBox getCheckBoxAll() {
        if (checkBoxAll == null) {
            checkBoxAll = new JCheckBox("All");
            checkBoxAll.addActionListener(actionEvent -> changeCheckBoxes());
        }
        return checkBoxAll;
    }

    private JPanel getPnStartTime() {
        if (pnStartTime == null) {
            pnStartTime = new JPanel();
            pnStartTime.add(getLblStartTime());
            pnStartTime.add(getTpStart());
        }
        return pnStartTime;
    }

    private JPanel getPnEndTime() {
        if (pnEndTime == null) {
            pnEndTime = new JPanel();
            pnEndTime.add(getLblEndTime());
            pnEndTime.add(getTpEnd());
        }
        return pnEndTime;
    }

    private JLabel getLblStartTime() {
        if (lblStartTime == null) {
            lblStartTime = new JLabel("Start time:");
        }
        return lblStartTime;
    }

    private JLabel getLblEndTime() {
        if (lblEndTime == null) {
            lblEndTime = new JLabel("End time:");
        }
        return lblEndTime;
    }

    private JPanel getPnStartDate() {
        if (pnStartDate == null) {
            pnStartDate = new JPanel();
            pnStartDate.setBorder(new TitledBorder(null, "Start date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnStartDate.setLayout(new BoxLayout(pnStartDate, BoxLayout.X_AXIS));
            pnStartDate.add(getCalStartDate());
        }
        return pnStartDate;
    }

    private JPanel getPnEndDate() {
        if (pnEndDate == null) {
            pnEndDate = new JPanel();
            pnEndDate.setBorder(new TitledBorder(null, "End date:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnEndDate.setLayout(new BoxLayout(pnEndDate, BoxLayout.Y_AXIS));
            pnEndDate.add(getCalEndDate());
        }
        return pnEndDate;
    }

    private JCalendar getCalStartDate() {
        if (calStartDate == null) {
            calStartDate = new JCalendar();
        }
        return calStartDate;
    }

    private JCalendar getCalEndDate() {
        if (calEndDate == null) {
            calEndDate = new JCalendar();
        }
        return calEndDate;
    }

    private TimePicker getTpStart() {
        if (tpStart == null) {
            tpStart = new TimePicker();
        }
        return tpStart;
    }

    private TimePicker getTpEnd() {
        if (tpEnd == null) {
            tpEnd = new TimePicker();
        }
        return tpEnd;
    }

    private JPanel getPnWest() {
        if (pnWest == null) {
            pnWest = new JPanel();
            pnWest.setMinimumSize(new Dimension(200, 10));
            pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
            pnWest.add(Box.createRigidArea(new Dimension(0, 10)));
            pnWest.add(getTxtFilter());
            pnWest.add(Box.createRigidArea(new Dimension(0, 10)));
            pnWest.add(getTbPnStaffList());
            pnWest.add(Box.createRigidArea(new Dimension(0, 10)));
            pnWest.add(getBtnSelect());
            pnWest.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        return pnWest;
    }

    private JTextField getTxtFilter() {
        if (txtFilter == null) {
            txtFilter = new JTextField();
            txtFilter.setMaximumSize(new Dimension(2147483647, 200));
            txtFilter.setColumns(10);
            textMatcherEditor = new TextComponentMatcherEditor<Staff>(txtFilter, new StaffTextFilterator());
        }
        return txtFilter;
    }

    private JTabbedPane getTbPnStaffList() {
        if (tbPnStaffList == null) {
            tbPnStaffList = new JTabbedPane(JTabbedPane.TOP);
            tbPnStaffList.addTab("Doctors", null, getSpDoctors(), null);
            tbPnStaffList.addTab("Nurses", null, getSpNurses(), null);
            tbPnStaffList.addTab("Selected", null, getSpSelected(), null);
        }
        return tbPnStaffList;
    }

    private JScrollPane getSpDoctors() {
        if (spDoctors == null) {
            spDoctors = new JScrollPane();
            spDoctors.setViewportView(getLstDoctor());
        }
        return spDoctors;
    }

    private JList<Doctor> getLstDoctor() {
        if (lstDoctor == null) {
            lstDoctor = new JList<Doctor>();
            lstDoctor.addMouseListener(new DoubleClickSelector());
            lstDoctor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            lstDoctor.setCellRenderer(new StaffCellRenderer());
        }
        return lstDoctor;
    }

    private JScrollPane getSpNurses() {
        if (spNurses == null) {
            spNurses = new JScrollPane();
            spNurses.setViewportView(getLstNurse());
        }
        return spNurses;
    }

    private JList<Nurse> getLstNurse() {
        if (lstNurse == null) {
            lstNurse = new JList<Nurse>();
            lstNurse.addMouseListener(new DoubleClickSelector());
            lstNurse.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            lstNurse.setCellRenderer(new StaffCellRenderer());
        }
        return lstNurse;
    }

    private JScrollPane getSpSelected() {
        if (spSelected == null) {
            spSelected = new JScrollPane();
            spSelected.setViewportView(getLstSelected());
        }
        return spSelected;
    }

    private JList<Staff> getLstSelected() {
        if (lstSelected == null) {
            lstSelected = new JList<Staff>();
            lstSelected.addMouseListener(new DoubleClickRemover());
            SortedList<Staff> staffSortedList = new SortedList<Staff>(new BasicEventList<Staff>(), new StaffComparator());
            selectedList = new FilterList<Staff>(staffSortedList, textMatcherEditor);
            lstSelected.setModel(GlazedListsSwing.eventListModelWithThreadProxyList(selectedList));
            lstSelected.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            lstSelected.setCellRenderer(new StaffCellRenderer());
        }
        return lstSelected;
    }

    private JButton getBtnSelect() {
        if (btnSelect == null) {
            btnSelect = new JButton("Select");
            selector = new EntriesSelector();
            remover = new EntriesRemover();
            btnSelect.addActionListener(selector);
            tbPnStaffList.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (((JTabbedPane) e.getSource()).getSelectedIndex() == 2) {
                        btnSelect.removeActionListener(selector);
                        btnSelect.addActionListener(remover);
                        btnSelect.setText("Remove");
                    } else {
                        btnSelect.removeActionListener(remover);
                        btnSelect.addActionListener(selector);
                        btnSelect.setText("Select");
                    }
                }
            });
        }
        return btnSelect;
    }

    // METODOS DE COMPROBACION Y EJECUCION ------------------------------------------------------------

    public void fillLists() {
        // Doctor List
        EventList<Doctor> doctorlist = new BasicEventList<Doctor>();
        doctorlist.addAll(doctorService.findAllDoctors());
        SortedList<Doctor> doctorSortedList = new SortedList<Doctor>(doctorlist, new StaffComparator());
        doctorList = new FilterList<Doctor>(doctorSortedList, textMatcherEditor);
        lstDoctor.setModel(GlazedListsSwing.eventListModelWithThreadProxyList(doctorList));
        // Nurse list
        EventList<Nurse> nurselist = new BasicEventList<Nurse>();
        nurselist.addAll(nurseService.findAllNurses());
        SortedList<Nurse> nurseSortedList = new SortedList<Nurse>(nurselist, new StaffComparator());
        nurseList = new FilterList<Nurse>(nurseSortedList, textMatcherEditor);
        lstNurse.setModel(GlazedListsSwing.eventListModelWithThreadProxyList(nurseList));
    }

    /**
     * Almacena la informaci�n introducida en un grupo de dto para su introducci�n en la base de datos
     */
    private void saveData() {
        try {
            checkInput();
            List<Staff> selectedStaff = new ArrayList<Staff>();
            List<Schedule> schedules = new ArrayList<Schedule>();
            // Selecciona los empleados
            selectedList.forEach(x -> selectedStaff.add(x));
            // Comprueba que se ha seleccionado alguno
            if (selectedStaff.isEmpty())
                throw new InputException("You must select at least one employee");
            // Rellena una lista de horarios a añadir
            Map<LocalDateTime, LocalDateTime> daySchedules = generateDaySchedules();
            for (LocalDateTime startTime : daySchedules.keySet())
                selectedStaff.forEach(x -> schedules.add(new Schedule(startTime, daySchedules.get(startTime), x)));
            // Guarda los horarios
            scheduleService.updateSchedules(schedules);
            JOptionPane.showMessageDialog(this, "All the schedules were updated successfully");
        } catch (InputException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private Map<LocalDateTime, LocalDateTime> generateDaySchedules() {
        Map<LocalDateTime, LocalDateTime> schedules = new HashMap<LocalDateTime, LocalDateTime>();
        LocalDateTime startTime = getStartTime();
        LocalDateTime endTime = getEndTime();
        LocalDateTime currentTime = startTime;
        boolean[] days = getWeekDays();
        while (currentTime.isBefore(endTime)) {
            if (days[currentTime.getDayOfWeek().getValue() - 1])
                if (startTime.toLocalTime().isBefore(endTime.toLocalTime()))
                    schedules.put(currentTime, LocalDateTime.of(currentTime.toLocalDate(), endTime.toLocalTime()));
                else
                    schedules.put(currentTime.plusDays(1), LocalDateTime.of(currentTime.toLocalDate(), endTime.toLocalTime()));
            currentTime = currentTime.plusDays(1);
        }
        return schedules;
    }

    /**
     * Comprueba todos los campos introducidos
     *
     * @throws BusinessException
     */
    private void checkInput() throws InputException {
        checkDays();
        checkDates();
        checkTimes();
    }

    /**
     * Comprueba que se ha seleccionado al menos un d�a de trabajo durante la semana
     *
     * @throws BusinessException en caso de no seleccionar ninguno
     */
    private void checkDays() throws InputException {
        for (int i = 0; i < 7; i++)
            if (((JCheckBox) pnDays.getComponent(i)).isSelected())
                return;
        throw new InputException("You must select at least one working week day");
    }

    /**
     * Comprueba que el d�a final no sea anterior al inicial
     *
     * @throws BusinessException
     */
    private void checkDates() throws InputException {
        if (calEndDate.getDate().compareTo(calStartDate.getDate()) <= 0)
            throw new InputException("End date can't be before the starting date");
    }

    /**
     * Comprueba que se han introducido horas de comienzo y final de la jornada
     *
     * @throws BusinessException
     */
    private void checkTimes() throws InputException {
        if (tpStart.getTime() == null || tpEnd.getTime() == null)
            throw new InputException("You must select at least and starting and ending hour");
        if (tpStart.getTime().equals(tpEnd.getTime()))
            throw new InputException("Starting and ending time can't be the same");
    }

    /**
     * @return Fecha y hora de inicio del horario
     */
    private LocalDateTime getStartTime() {
        LocalDate startDate = calStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime startTime = tpStart.getTime();
        return LocalDateTime.of(startDate, startTime);
    }

    /**
     * @return Fecha y hora de inicio del horario
     */
    private LocalDateTime getEndTime() {
        LocalDate endDate = calEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime endTime = tpEnd.getTime();
        return LocalDateTime.of(endDate, endTime);
    }

    /**
     * @return array con los d�as que trabaja el empleado en el horario seleccionado
     */
    private boolean[] getWeekDays() {
        boolean[] days = new boolean[7];
        for (int i = 0; i < 7; i++)
            days[i] = ((JCheckBox) pnDays.getComponent(i)).isSelected();
        return days;
    }

    /**
     * Marca o desmarca todas las checkboxes
     */
    private void changeCheckBoxes() {
        for (int i = 0; i < 7; i++)
            ((JCheckBox) pnDays.getComponent(i)).setSelected(checkBoxAll.isSelected());
    }


    // ACTION LISTENERS -----------------------------------------------------------------------------------------

    /**
     * Gestiona el uso del bot�n Ok
     *
     * @author Ricardo Soto (uo265710)
     */
    private class DataSaver implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveData();
        }

    }

    /**
     * Guarda los empleados seleccionados en la lista selected
     *
     * @author Ricardo Soto (uo265710)
     */
    private class EntriesSelector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Doctor> doctors = lstDoctor.getSelectedValuesList();
            List<Nurse> nurses = lstNurse.getSelectedValuesList();
            for (Doctor dto : doctors) {
                selectedList.add(dto);
                doctorList.remove(dto);
            }
            for (Nurse dto : nurses) {
                selectedList.add(dto);
                nurseList.remove(dto);
            }
        }
    }

    /**
     * Elimina los empleados seleccionados en selected y los devuelve a sus listas originales
     *
     * @author Ricardo Soto (uo265710)
     */
    private class EntriesRemover implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Staff> selected = lstSelected.getSelectedValuesList();
            for (Staff dto : selected) {
                selectedList.remove(dto);
                if (dto instanceof Doctor)
                    doctorList.add((Doctor) dto);
                else
                    nurseList.add((Nurse) dto);
            }
        }
    }

    /**
     * A�ade el elemento seleccionado con el doble click a la lista selected
     *
     * @author Ricardo Soto (uo265710)
     */
    private class DoubleClickSelector extends MouseAdapter {

        @SuppressWarnings("rawtypes")
        @Override
        public void mouseClicked(MouseEvent e) {
            JList list = (JList) e.getSource();
            if (e.getClickCount() == 2) {
                Staff selected = (Staff) list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
                selectedList.add(selected);
                if (selected instanceof Doctor)
                    doctorList.remove((Doctor) selected);
                else
                    nurseList.remove((Nurse) selected);
            }
        }
    }

    /**
     * Elimina el elemento seleccionado con el doble click y lo devuelve a su lista pertinente
     *
     * @author Ricardo Soto (uo265710)
     */
    private class DoubleClickRemover extends MouseAdapter {

        @SuppressWarnings("unchecked")
        @Override
        public void mouseClicked(MouseEvent e) {
            JList<Staff> list = (JList<Staff>) e.getSource();
            if (e.getClickCount() == 2) {
                Staff selected = (Staff) list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
                selectedList.remove(selected);
                if (selected instanceof Doctor)
                    doctorList.add((Doctor) selected);
                else
                    nurseList.add((Nurse) selected);
            }
        }
    }
}
