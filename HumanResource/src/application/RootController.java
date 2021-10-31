package application;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import common_tool.TableViewFactory;
import dao.DepartmentsDAO;
import dao.EmployeesDAO;
import dao.Job_historyDAO;
import dao.JobsDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import service.HRService;
import vo.Departments;
import vo.Employees;
import vo.Jobs;

public class RootController implements Initializable{
	DepartmentsDAO ddao = new DepartmentsDAO();
	EmployeesDAO edao = new EmployeesDAO();
	Job_historyDAO jhdao = new Job_historyDAO();
	JobsDAO jdao = new JobsDAO();
	HRService service = new HRService();
	
	TableView tableView = TableViewFactory.getTable(Employees.class);
	
	@FXML
	private TextField txtEmployee_id;
	
    @FXML
    private TextField txtFirst_name;

    @FXML
    private TextField txtLast_name;
    
    @FXML
    private TextField txtHire_date;
    
    @FXML
    private TextField txtPhone_number;
    
    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<Departments> comboDepartment_id;
    
    @FXML
    private ComboBox<Jobs> comboJob_id;
    
    @FXML
    private ComboBox<Employees> comboManager_id;    

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtCommission_pct;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnClear;

    @FXML
    private Button btnSelectConditions;

    @FXML
    private Button btnSelectAll;

    @FXML
    private TextArea txtHistory;

    @FXML
    private BorderPane rightPanel;

    @FXML
    private TextField txtLast_name1;
    
    @FXML
    private Button btnSearchForLast;
    
    @FXML
    private TextField txtPhone_number1;
    
    @FXML
    private Button btnSearchForPhone;
    
    @FXML
    private TextField txtEmail1;
    
    @FXML
    private Button btnSearchForEmail;
    
    @FXML
    private ComboBox<Departments> comboDepartment_id1;
    
    @FXML
    private ComboBox<Jobs> comboJob_id1;
    
    @FXML
    private TextField txtStart_date;
    
    @FXML
    private TextField txtEnd_date;
    
    @FXML
    private Button btnSearchForPeriod;
    
    @FXML
    private Button btnSortBySalary;
    
    @FXML
    private Button btnSortByCommission;

    
    public Date str2Date(String x) {
    	String strs[] = x.split("-");
    	if(strs[1].charAt(0)=='0') strs[1] = strs[1].replace("0", "");
    	if(strs[2].charAt(0)=='0') strs[2] = strs[2].replace("0", "");
    	
    	int year = Integer.parseInt(strs[0]) - 1900;
    	int month = Integer.parseInt(strs[1]) - 1;
    	int day = Integer.parseInt(strs[2]) ;

    	return new Date(year,month,day);
    }
    
    int hisNo;
    
    public void dispHistory(String history) {
    	txtHistory.appendText("-" + (++hisNo) + ". " + history + "\n");
    }
    
    @FXML
    void insert(ActionEvent event) {
    		
    	try {
    		
			edao.insert(getVo());
			
			int id = edao.getMax();
				
			Employees vo = edao.select(id);
				
			tableView.getItems().add(vo);
				
			dispHistory("Insert complete.\n" + "\t> Inserted employee " + vo.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void sortByCommission(ActionEvent event) {
    	List<Employees> result = service.getEmpListOrderbyCommission();
    	
    	clear(new ActionEvent());
    	
    	tableView.getItems().addAll(result);
    	
    	dispHistory("Descending Sort Complete.\n" + "\t> Sorted column name: COMMISSION PCT");
    }
    
    @FXML
    void sortBySalary(ActionEvent event) {
    	List<Employees> result = service.getEmpListOrderbyPay();
    	
    	clear(new ActionEvent());
    	
    	tableView.getItems().addAll(result);
    	
    	dispHistory("Descending Sort Complete.\n" + "\t> Sorted column name: SALARY");
    }
    
    @FXML
    void searchForEmail(ActionEvent event) {
    	String email = txtEmail1.getText();
    	
    	List<Employees> result = service.getEmpListByEmail(email);
    	
    	clear(new ActionEvent());
    	
    	tableView.getItems().addAll(result);
    	dispHistory("Quick search complete.\n" + "\t> Searched email account: " + email);
    }
    
    @FXML
    void searchForLast(ActionEvent event) {
    	String last_name = txtLast_name1.getText();
    	
    	List<Employees> result = service.getEmpListByLast_name(last_name);
    	
    	clear(new ActionEvent());
    	
    	tableView.getItems().addAll(result);
    	dispHistory("Quick search complete.\n" + "\t> Searched last name: " + last_name);
    }
    
    @FXML
    void searchForPhone(ActionEvent event) {
    	String phone_num = txtPhone_number1.getText();
    	
    	List<Employees> result = service.getEmpListByPhone(phone_num);
    	
    	clear(new ActionEvent());
    	
    	tableView.getItems().addAll(result);
    	dispHistory("Quick search complete.\n" + "\t> Searched phone number:\n" + "\t   " + "\""+phone_num + "\"");
    }
    
    @FXML
    void searchForPeriod(ActionEvent event) {
    	Date a = str2Date(txtStart_date.getText());
    	Date b = str2Date(txtEnd_date.getText());
    	
    	List<Employees> retsult = service.getEmpListByHireDate(a, b);
    	
    	clear(new ActionEvent());
    	
    	tableView.getItems().addAll(retsult);
    	dispHistory("Quick search complete.\n" + "\t> Searched period: \n" + "\t " + a.toString() + " ~ " + b.toString());
    }
    
    private Employees getVo() {
    	
    	Employees vo = new Employees();
    	
    	vo.setFirst_name(txtFirst_name.getText());
    	
    	vo.setLast_name(txtLast_name.getText());
    	
    	vo.setEmail(txtEmail.getText());
    	
    	vo.setHire_date(str2Date(txtHire_date.getText()));
    	
    	vo.setPhone_number(txtPhone_number.getText());
    	
    	vo.setJob_id(comboJob_id.getSelectionModel().getSelectedItem().getJob_id());
    	
    	vo.setSalary(Integer.parseInt(txtSalary.getText()));
    	
    	vo.setCommission_pct(Double.parseDouble(txtCommission_pct.getText()));
    	
    	vo.setManager_id(comboManager_id.getSelectionModel().getSelectedItem().getEmployee_id());
    	
    	vo.setDepartment_id(comboDepartment_id.getSelectionModel().getSelectedItem().getDepartment_id());
    	
    	return vo;
    }
    
    @FXML
    void clear(ActionEvent event) {
    	tableView.getItems().clear();
    	
    	txtEmployee_id.setText(null);
    	
    	txtFirst_name.setText(null);
    	
    	txtLast_name.setText(null);
    	
    	comboDepartment_id.setValue(null);  // 콤보박스 클리어.
    	
    	txtPhone_number.setText(null);
    	
    	txtEmail.setText(null);
    	
    	txtSalary.setText(null);
    	
    	comboJob_id.setValue(null);  // 콤보박스 클리어.
    	
    	txtHire_date.setText(null);
    	
    	comboManager_id.setValue(null);  // 콤보박스 클리어.
    	
    	txtCommission_pct.setText(null);
    	
    	dispHistory("Clear complete.");
    }

    @FXML
    void delete(ActionEvent event) {
    	
    	int selNum = tableView.getSelectionModel().getSelectedIndex();
    	Employees emp = (Employees) tableView.getSelectionModel().getSelectedItem();
    	int empId = emp.getEmployee_id();
    	
    	try {
    		// 고아레코드 제거하기(선행)
			jhdao.deleteOrphanRecord(empId);
			
			edao.delete(empId);
			tableView.getItems().remove(selNum);
			
			dispHistory("Delete complete.\n" + "\t> deleted employee ID: " + empId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
    	
    @FXML
    void selectAll(ActionEvent event) {
    	try {
			rightPanel.setCenter(tableView);
			clear(event);
    		//tableView.getItems().clear();
			tableView.getItems().addAll(edao.selectAll());
			
			dispHistory("All data search complete.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void selectByConditions(ActionEvent event) {
    	String conditions = JOptionPane.showInputDialog("'Where' + conditions");
    	try {
    		List<Employees> data = edao.selectByConditions(conditions);
    		tableView.getItems().clear();
    		tableView.getItems().addAll(data);
    		
    		dispHistory("Data condition search completed.\n" + "\t>applied condition: \n" + "\t   " + "\""+conditions+"\"");
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void update(ActionEvent event) {
    		
		try {
			Employees vo = getVo();
			vo.setEmployee_id(Integer.parseInt(txtEmployee_id.getText()));
			edao.update(vo);
			int selNum = tableView.getSelectionModel().getSelectedIndex();
			tableView.getItems().set(selNum, vo);
			
			dispHistory("Update Complete.\n" + "\t> Updated employee " + vo.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			
			comboDepartment_id.getItems().addAll(ddao.selectAll());
			
			comboDepartment_id1.getItems().addAll(ddao.selectAll());
			
			comboJob_id.getItems().addAll(jdao.selectAll());

			comboJob_id1.getItems().addAll(jdao.selectAll());
			
			comboManager_id.getItems().addAll(edao.selectAll());
			
			setTable();
			
			tableView.getItems().addAll(edao.selectAll());
			
			comboDepartment_id1.valueProperty().addListener(new ChangeListener<Departments>() {

				@Override
				public void changed(ObservableValue<? extends Departments> observable, Departments oldValue,
						Departments newValue) {
					List<Employees> result = service.getEmpListByDep(newValue.getDepartment_id());
					
					clear(new ActionEvent());
					
					tableView.getItems().addAll(result);
					
				}
				
			});
			
			comboJob_id1.valueProperty().addListener(new ChangeListener<Jobs>() {

				@Override
				public void changed(ObservableValue<? extends Jobs> observable, Jobs oldValue, Jobs newValue) {

					List<Employees> result = service.getEmpListByJobid(newValue.getJob_id());
					
					clear(new ActionEvent());
					
					tableView.getItems().addAll(result);
					
				}
				
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispHistory("UI Initialize Complete.");
		
	}
	public void setTable() throws SQLException{
		dispHistory("TableView Setting Complete.");
		
		tableView.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				try {
					Employees selected = (Employees) tableView.getSelectionModel().getSelectedItem();
					
					txtEmployee_id.setText(selected.getEmployee_id() + "");
					
					txtFirst_name.setText(selected.getFirst_name());
					
					txtLast_name.setText(selected.getLast_name());
					
					comboDepartment_id.getSelectionModel().select(ddao.select(selected.getDepartment_id()));
					
					txtPhone_number.setText(selected.getPhone_number());
					
					txtEmail.setText(selected.getEmail());
					
					txtSalary.setText(selected.getSalary() + "");
					
					comboJob_id.getSelectionModel().select(jdao.select(selected.getJob_id()));
					
					txtHire_date.setText(selected.getHire_date().toString());
					
					comboManager_id.getSelectionModel().select(edao.select(selected.getManager_id()));
					
					txtCommission_pct.setText(selected.getCommission_pct() + "");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	
		});
		rightPanel.setCenter(tableView);
		
	}

}
