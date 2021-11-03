import dao.AccountDAO;
import model.AccountEntity;
import model.LoginEntity;

public class AccountDAOTest {
	public static void main (String[] arg) {
		testFindByLogin1();
		testFindByLogin2();
	}
	
	public static void testFindByLogin1() {
		LoginEntity loginEntity = new LoginEntity("minato", "1234");
		AccountDAO dao = new AccountDAO();
		AccountEntity result = dao.findByLogin(loginEntity);

		System.out.println(result);
		if (result != null) {
			System.out.println(result.getUserId());
			System.out.println(result.getPass());
			System.out.println(result.getMail());
			System.out.println(result.getName());
			System.out.println(result.getAge());
			
			if (result.getUserId().equals("minato") &&
			result.getPass().equals("1234") &&
			result.getMail().equals("minato@sukkiri.com") &&
			result.getName().equals("湊 雄輔") &&
			result.getAge() == 23) {
			
				System.out.println("testFindByLogin1:成功しました");
			}
		} else {
			System.out.println("testFindByLogin1:失敗しました");
		}
	}
	
	public static void testFindByLogin2() {
		LoginEntity entity = new LoginEntity("minato", "12345");
		AccountDAO dao = new AccountDAO();
		AccountEntity result = dao.findByLogin(entity);
		
		if (result == null) {
			System.out.println("testFindByLogin2:成功しました");
		} else {
			System.out.println("testFindByLogin2:失敗しました");
		}
	}
}
