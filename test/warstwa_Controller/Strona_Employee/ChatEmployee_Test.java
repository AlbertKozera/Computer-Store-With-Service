package warstwa_Controller.Strona_Employee;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ChatEmployee_Test {

    @Test
    public void loadChat___TEST(){
        ChatEmployee chatEmployee = new ChatEmployee();

        String result1 = chatEmployee.loadChat___TEST();
        assertEquals("/_Employee/ChatEmployee.xhtml",result1);
    }

}
