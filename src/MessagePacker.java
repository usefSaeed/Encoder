import Model.Item;
import Model.Menu;

import java.io.IOException;
import java.util.ArrayList;

public class MessagePacker implements Encoder{
    @Override
    public void encode(String path) throws Exception {
        JSONReader jr = new JSONReader();
        String oPath = "src\\files\\lol-mp";
        HexWriter hw = new HexWriter(oPath);
        Menu data =  jr.readMenu(path);
        exec(hw,data);
    }

    private void exec(HexWriter hw, Menu data) throws Exception {
        //Menu Object
        hw.appendHexDigit(8);
        //1 Entry
        hw.appendHexDigit(1);
        //#1:
        hw.appendHexDigit(10);
        hw.appendHexDigit(4);
        hw.appendWord("menu");
        //#1 Content
        //Inner Menu Object
        hw.appendHexDigit(8);
        //2 Entries
        hw.appendHexDigit(2);
        //#1:Header
        hw.appendHexDigit(10);
        hw.appendHexDigit(6);
        hw.appendWord("header");
        String header =  data.getHeader();
        //#1 Content
        hw.appendHexDigit(10);
        hw.appendHexDigit(header.length());
        hw.appendWord(header);
        //#2:Items
        ArrayList<Item> items = data.getItems();
        hw.appendHexDigit(9);
        hw.appendHexDigit(items.size());
        //#2 Content
        for (Item item : items) {
            //Item Object
            hw.appendHexDigit(8);
            if (item != null) {
                String id = item.getId();
                String label = item.getLabel();
                if (label != null) {
                    //2 Entries
                    hw.appendHexDigit(2);
                    //#1:ID
                    hw.appendHexDigit(10);
                    hw.appendHexDigit(id.length());
                    //#1 Content
                    hw.appendWord(id);
                    //#2:Label
                    hw.appendHexDigit(10);
                    hw.appendHexDigit(label.length());
                    //#2 Content
                    hw.appendWord(label);
                } else {
                    //1 Entry
                    hw.appendHexDigit(1);
                    //#1:ID
                    hw.appendHexDigit(10);
                    hw.appendHexDigit(id.length());
                    //#1 Content
                    hw.appendWord(id);
                }
            } else {
                //0 Entries
                hw.appendHexDigit(0);
            }
        }





    }

    public static void main(String[] args) throws Exception {
        MessagePacker mp = new MessagePacker();
        mp.encode("src\\files\\lol.json");
    }
}
