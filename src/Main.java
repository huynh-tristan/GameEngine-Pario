import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new SettingUp("Test"));
        app.setTargetFrameRate(60);
        app.setDisplayMode(960,640,false);

        app.start();
    }
}