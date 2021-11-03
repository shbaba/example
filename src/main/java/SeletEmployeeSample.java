import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class SeletEmployeeSample {
	public static void main(String[] args) {
		// employeeテーブルの前レコードを取得
		EmployeeDAO empDao = new EmployeeDAO();
		List<Employee> empList = empDao.findAll();
		
		// データベースに接続
		for (Employee emp : empList) {
			System.out.println("id:" + emp.getId());
			System.out.println("名前:" + emp.getName());
			System.out.println("年齢:" + emp.getAge() + "¥r¥n");
		}
	}
}
