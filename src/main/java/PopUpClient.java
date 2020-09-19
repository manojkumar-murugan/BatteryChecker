import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PopUpClient extends JFrame implements ActionListener{
    // popup
    Popup p;
    public static String amount="83";

    // constructor
    PopUpClient()
    {
        // create a frame
        JFrame f = new JFrame("pop");
        // create a label
//        JLabel l = new JLabel("This is a popup");
        f.setSize(400, 400);
        PopupFactory pf = new PopupFactory();
        // create a panel
        JPanel p2 = new JPanel();
        // set Background of panel
        p2.setBackground(Color.red);
//        p2.add(l);
        // create a popup
        p = pf.getPopup(f, p2, 180, 100);
        // create a button
        JButton b = new JButton("Battery reached "+amount+"%");
        // add action listener
        b.addActionListener(this);
        // create a panel
        JPanel p1 = new JPanel();
        p1.add(b);
        f.add(p1);
        f.show();
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
    String name=this.getClass().getCanonicalName();
    public static void main(String args[]) throws InterruptedException {
        PopUpClient popUpClient=new PopUpClient();
        System.out.println(popUpClient.name);
        if(args.length>0 && args[0]!=null && args[0]!="")
            amount=args[0];
        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        String batteryPercent;
        while(true){
            Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
            batteryPercent=batteryStatus.getBatteryLifePercent().split("%")[0];
            if(batteryPercent.equals(args[0])){
                PopUpClient p = new PopUpClient();
                Thread.sleep(20000);
            }
            System.out.println(batteryPercent);
            Thread.sleep(2000);
        }
    }
}
