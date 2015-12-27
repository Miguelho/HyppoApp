package miguel.hyppoapp;

import android.content.pm.PackageInfo;
import android.test.ApplicationTestCase;
import android.test.MoreAsserts;
import android.test.mock.MockContext;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import miguel.hyppoapp.Application.HypoAppApplication;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class)
//@RunWith(MockitoJUnitRunner.class)

public class HypoAppApplicationTest extends ApplicationTestCase<HypoAppApplication> {

    MockContext mMockContext = new MockContext();

    private HypoAppApplication application;
    public HypoAppApplicationTest() {
        super(HypoAppApplication.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
        application = getApplication();


    }

    public void testCorrectVersion() throws Exception {
        PackageInfo info = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        assertNotNull(info);
        MoreAsserts.assertMatchesRegex("\\d\\.\\d", info.versionName);
    }

    @Test
    public void fetchDataLogin() {
        Login_Activity login_activity=new Login_Activity();
        List<ParseObject> listaEstados = login_activity.fetchDataLogin();
        assertNotEquals(listaEstados.size(),0);
    }

    @Before
    public void initialize(){
        //mMockContext = Mockito.mock(Context.class);
        //Mockito.when(mMockContext.getApplicationContext()).thenReturn(mMockContext);

        //when(mMockContext.getApplicationInfo()).thenReturn();
        //HypoAppApplication hypoAppApplication= new HypoAppApplication();
//this.getInstrumentation().getContext()
        //hypoAppApplication.onCreate();
        Parse.enableLocalDatastore(this.getContext());
        Parse.initialize(this.getContext(), "1z2PrpWKAivqAg2uzz4B3c4DfsWAfMlquXxuvbmP", "uQ0Ec5xuA4FdD6K5XZ2Aq41rHo8aG8ivNfN47nfh");
        ParseHandler parseHandler = ParseHandler.getInstance();
        try {
            parseHandler.loadData();
        } catch (ParseException e) {
            Log.e("Error loadData()", "error while loading data.");
            e.printStackTrace();
        }
    }

    @Test
    public void testOnCreate() throws Exception {

    }
}