package warstwa_Controller.Strona_User;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ChatUser_Test {

    @Test
    public void loadChat___TEST(){
        ChatUser chatUser = new ChatUser();

        String result1 = chatUser.loadChat___TEST();
        assertEquals("/_User/ChatUser.xhtml",result1);
    }


}
