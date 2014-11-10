package Tests.hotlineTesting;

import Priject.hotlineTesting.actors.Users;
import Priject.hotlineTesting.pages.RegisterPage;
import Priject.hotlineTesting.utils.Log4Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by boris on 05.11.14.
 */
public class RegistrationTestNegative extends FunctionTest{

    @DataProvider
    public Object[][] testData(){
        return new Object[][] {
                new Object[] {"http://hotline.ua/", userInfo("param@gmail.com",  "test", "test"), true}
        };
    }
    public HashMap<String, String> userInfo(String email, String nicName, String password){
        HashMap hm = new HashMap();
        hm.put("email", email);
        hm.put("nicName", nicName);
        hm.put("password", password);
        return hm;
    }


    @Test(dataProvider = "testData")
    public void testRegistrationNegative(String strUrl, HashMap hm, boolean isGenerateEmail){
        System.out.println(Log4Test.info("Start : testRegistrationNegative"));
        driver.get(strUrl);
        Users user = new Users(hm, !isGenerateEmail);
        RegisterPage rp = new RegisterPage(user, driver);
        rp.openPage();
        rp.fillRegistrationForm();
        Assert.assertFalse(rp.isRegiststrationSuccses(), Log4Test.error("Can registration with non valid input data"));
        System.out.println(Log4Test.info("Finish successful : testRegistrationNegative"));

    }

}