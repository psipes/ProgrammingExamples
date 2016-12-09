package robotDrawerGUI;

/**
 * Classes: RobotGUIDriver, RobotGUIFrame, RobotGUIPanels, PixelBlock
 *
 * Robot GUI Driver: starts the program
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class RobotGuiDriver {

    /**
     * Main function that displays the frame and starts the game
     * @param args starter
     */
    public static void main(String[] args) {
        RobotGUIFrame frame = new RobotGUIFrame();
        frame.display();
    }
}
