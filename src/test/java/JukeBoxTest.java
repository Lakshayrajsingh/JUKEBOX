import com.search_song.SearchMethods;
import com.search_song.SearchSong;
import com.user_sign_in.ConnectionToDatabase;
import com.user_sign_in.UserDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JukeBoxTest
{
    SearchMethods search;

    @Before
    public void setUp()
    {
        search=new SearchMethods(0,null,null,null,null,null);
    }

    @After
    public void tearDown()
    {
        search=null;
    }
    @Test
    public void toCheckIfListIsCreatedOrNot() throws SQLException
    {
        List<SearchMethods> list=search.creatingList();
        assertEquals(4,list.size());
    }


}
