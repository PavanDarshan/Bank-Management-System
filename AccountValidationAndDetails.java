import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountValidationAndDetails {
    //account number details getting from Transaction Table
    public ResultSet getAccountDetails(int accNo,int password,Connection connection){
        final String query = "select * from bank.transaction where (accNo=?) AND ( case when (select accNo from bank.accountmaster where accNo=? AND pw=?)is not null then True end)";;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, accNo);
            ps.setInt(2,accNo);
            ps.setInt(3,password);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
