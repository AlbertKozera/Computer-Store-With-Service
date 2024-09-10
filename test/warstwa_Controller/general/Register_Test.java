package warstwa_Controller.general;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Register_Test {

    @Test
    public void getSHA256___TEST(){
        Register register = new Register();

        String result1 = register.getSHA256("1");
        assertEquals("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b",result1);
    }
}
