package handlere;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Spoer_om_tid_HandlerTest.class, 
				Ugyldig_pakke_HandlerTest.class, 
				Spoer_om_tags_HandlerTest.class,
				Spoer_om_login_HandlerTest.class,
				Spoer_om_bilder_HandlerTest.class,
				Admin_spoer_om_bilder_HandlerTest.class})
public class AllTests {

}
