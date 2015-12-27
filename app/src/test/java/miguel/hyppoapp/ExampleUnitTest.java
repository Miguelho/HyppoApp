package miguel.hyppoapp;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import miguel.hyppoapp.Application.HypoAppApplication;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk=21,application = HypoAppApplication.class,manifest = "src/main/AndroidManifest.xml")
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        //if(System.getenv("ROBOLECTRIC") == null) {
        //}
        assertEquals(4, 2 + 2);
        Context context= RuntimeEnvironment.application.getApplicationContext();
    }
}